import fetchMock from 'fetch-mock';
import { ActionTypes } from './types';
import { NodeModel } from '../../shared-models/NodeModel';
import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import { GraphRequestModel } from '../../graph-search/GraphRequestModel';
import * as actions from './index';

const middleware = [thunk];
const mockStore = configureMockStore(middleware);

describe('GraphTraversalActions', () => {
  afterEach(() => {
    fetchMock.restore();
  });

  it('should fetch the shortest path using breadth first search', () => {
    let shortestPathJSON = [{id: 1}, {id: 2}];

    fetchMock.post('http://localhost:8080/api/traverse-graph-with-bfs', {
      body: shortestPathJSON,
      headers: {'content-type': 'application/json'}
    });

    const expectedActions = [
      {
        type: ActionTypes.POST_BFS_REQUEST
      },
      {
        type: ActionTypes.POST_BFS_SUCCESS,
        body: shortestPathJSON
      }
    ];

    const store = mockStore({});

    let adjacencyMatrix = [[true, true], [true, true]];
    let start = new NodeModel(1);
    let end = new NodeModel(11);
    let graphRequest = new GraphRequestModel(adjacencyMatrix, start, end);

    // @ts-ignore
    return store.dispatch(actions.fetchShortestPathUsingBFS(graphRequest))
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });
});