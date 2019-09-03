import fetchMock from 'fetch-mock';
import { ActionTypes } from '../actions/ActionTypes';
import { NodeModel } from './NodeModel';
import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import * as actions from '../shortest-path-through-map/actions';
import { GraphRequestModel } from './GraphRequestModel';

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
    let graphRequest = new GraphRequestModel(adjacencyMatrix, start);

    // @ts-ignore
    return store.dispatch(actions.fetchShortestPathUsingBFS(graphRequest))
      .then(() => {
        expect(store.getActions()).toEqual(expectedActions);
      })
  });

  // it('should change the state of a cell in the adjacency matrix', () => {
  //
  //   actions.toggleMatrixCell(keyValuePair);
  // });
});