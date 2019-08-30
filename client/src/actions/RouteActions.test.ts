import fetchMock from 'fetch-mock';
import { ActionTypes } from './ActionTypes';
import thunk from 'redux-thunk';
import configureMockStore from 'redux-mock-store';
import * as actions from './RouteActions';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('RouteActions', () => {
  afterEach(() => {
    fetchMock.restore();
  });

  let weightedRouteJSON = {
      route: [
        {name: '1', latitude: 0, longitude: 0}
      ],
      weight: 0
    };

  it('should fetch a weighted route and succeed on completion', () => {
    fetchMock.getOnce('http://localhost:8080/api/weightedRoute', {
      body: {...weightedRouteJSON},
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
});