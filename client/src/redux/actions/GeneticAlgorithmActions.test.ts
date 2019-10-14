import fetchMock from 'fetch-mock';
import { ActionTypes } from './types';
import thunk from 'redux-thunk';
import configureMockStore from 'redux-mock-store';
import { fetchNewRouteFromText, fetchNewTrial } from './index';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('GeneticAlgorithmActions', () => {
  let trialJSON = {
    generations: [
      {
        generation: 0,
        parents: [
          {route: [1, 2, 3], weight: 0}
        ],
        children: []
      },
    ]
  };

  afterEach(() => {
    fetchMock.restore();
  });

  // it('should fetch a weight cycle with requested coordinates', () => {
  //   fetchMock.post('http://localhost:8080/api/genetic-trial', {
  //     body: trialJSON,
  //     headers: {'content-type': 'application/json'},
  //   });
  //
  //   const expectedActions = [
  //     {
  //       type: ActionTypes.POST_NEW_TRIAL_REQUEST
  //     },
  //     {
  //       type: ActionTypes.FETCH_TRIAL_SUCCESS,
  //       body: trialJSON
  //     }
  //   ];
  //
  //   const store = mockStore({});
  //
  //   // @ts-ignore
  //   return store.dispatch(fetchNewTrial(
  //     'city1 1 1\ncity2 2 2',
  //     '4',
  //     '100'
  //   ))
  //     .then(() => {
  //       expect(store.getActions()).toEqual(expectedActions);
  //     })
  // });
});