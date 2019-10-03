import fetchMock from 'fetch-mock';
import { ActionTypes } from './types';
import thunk from 'redux-thunk';
import configureMockStore from 'redux-mock-store';
import { fetchNewRouteFromText } from './index';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('BruteForceActions', () => {
  let weightedRouteJSON = {
    route: [
      {name: '1', latitude: 0, longitude: 0}
    ],
    weight: 0
  };

  afterEach(() => {
    fetchMock.restore();
  });

  it('should fetch a weight cycle with requested coordinates', () => {
    fetchMock.post('http://localhost:8080/api/weighted-route', {
      body: weightedRouteJSON,
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
    return store.dispatch(fetchNewRouteFromText('city1 1 1\ncity2 2 2'))
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });
});