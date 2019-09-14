import { ActionTypes } from './types';
import { GraphRequestModel } from '../../graph-search/GraphRequestModel';

function postBFSRequest() {
  return {
    type: ActionTypes.POST_BFS_REQUEST
  };
}

function postBFSSuccess(body: any) {
  return {
    type: ActionTypes.POST_BFS_SUCCESS,
    body
  }
}

function postBFSFailure(exception: any) {
  return {
    type: ActionTypes.POST_BFS_FAILURE,
    exception
  }
}

function postDFSRequest() {
  return {
    type: ActionTypes.POST_DFS_REQUEST
  };
}

function postDFSSuccess(body: any) {
  return {
    type: ActionTypes.POST_DFS_SUCCESS,
    body
  }
}

function postDFSFailure(exception: any) {
  return {
    type: ActionTypes.POST_DFS_FAILURE,
    exception
  }
}

export function fetchShortestPathUsingBFS(graphRequest: GraphRequestModel) {
  return function (dispatch: any) {
    dispatch(postBFSRequest());
    return fetch(
      'http://localhost:8080/api/traverse-graph-with-bfs',
      {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(graphRequest),
      }
    )
      .then(response => response.json())
      .then(body => dispatch(postBFSSuccess(body)))
      .catch(exception => dispatch(postBFSFailure(exception)));
  };
}

export function fetchShortestPathUsingDFS(graphRequest: GraphRequestModel) {
  return function (dispatch: any) {
    dispatch(postDFSRequest());
    return fetch(
      'http://localhost:8080/api/traverse-graph-with-dfs',
      {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(graphRequest),
      }
    )
      .then(response => response.json())
      .then(body => dispatch(postDFSSuccess(body)))
      .catch(exception => dispatch(postDFSFailure(exception)));
  };
}
