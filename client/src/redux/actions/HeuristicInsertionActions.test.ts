// @ts-ignore
import fetchMock from 'fetch-mock';
import { ActionTypes } from './types';
import thunk from 'redux-thunk';
import configureMockStore from 'redux-mock-store';
import { fetchNewTourViaInsertionFromText } from './HeuristicInsertionActions';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('HeuristicInsertionActions', () => {
  let tourJSON = {
    route: [
      {name: '1', latitude: 0, longitude: 0}
    ],
    weight: 0
  };
  afterEach(() => {
    fetchMock.restore();
  });
  it('should fetch a tour from heuristic insertion endpoint using requested coordinates', () => {
    fetchMock.post('http://localhost:8080/api/weighted-route-via-insertion',
      {
        body: tourJSON,
        headers: {'content-type': 'application/json'},
      }
    );

    const expectedActions = [
      {
        type: ActionTypes.POST_NEW_TOUR_VIA_INSERTION_REQUEST
      },
      {
        type: ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS,
        body: tourJSON
      }
    ];

    const store = mockStore({});

    // @ts-ignore
    return store.dispatch(fetchNewTourViaInsertionFromText('city1 1 1\ncity2 2 2'))
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });
});