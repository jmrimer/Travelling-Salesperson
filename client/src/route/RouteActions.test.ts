import fetchMock from 'fetch-mock';
import { ActionTypes } from '../actions/ActionTypes';
import thunk from 'redux-thunk';
import configureMockStore from 'redux-mock-store';
import * as actions from './RouteActions';
import { MapModel } from './models/MapModel';
import { RouteModel } from './RouteModel';
import { CityModel } from './models/CityModel';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('RouteActions', () => {
  let route: RouteModel;
  let weightedRouteJSON = {
    route: [
      {name: '1', latitude: 0, longitude: 0}
    ],
    weight: 0
  };

  beforeEach(() => {
    route = new RouteModel([new CityModel('1', 0, 0)], 0);

  });

  afterEach(() => {
    fetchMock.restore();
  });

  it('should fetch a weighted route and succeed on completion', () => {

    fetchMock.getOnce('http://localhost:8080/api/weighted-route', {
      body: JSON.stringify(route),
      headers: {'content-type': 'application/json'},
    });

    const expectedActions = [
      {
        type: ActionTypes.FETCH_WEIGHTED_ROUTE_REQUEST
      },
      {
        type: ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS,
        body: weightedRouteJSON
      }
    ];

    const store = mockStore({});

    // @ts-ignore
    return store.dispatch(actions.fetchWeightedRoute())
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });

  it('should fetch a weight route with requested coordinates', () => {
    let map = new MapModel(
      [
        new CityModel('1', 0, 0),
        new CityModel('2', 1, 1)
      ]
    );

    fetchMock.post('http://localhost:8080/api/weighted-route', {
      body: JSON.stringify(route),
      headers: {'content-type': 'application/json'},
    });

    const expectedActions = [
      {
        type: ActionTypes.POST_NEW_ROUTE_REQUEST
      },
      {
        type: ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS,
        body: weightedRouteJSON
      }
    ];

    const store = mockStore({});

    // @ts-ignore
    return store.dispatch(actions.fetchNewRouteFromText('city1 1 1\ncity2 2 2'))
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });

  it('should update the state for map text', () => {

  });
});