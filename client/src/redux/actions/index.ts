import { ActionTypes } from './types';

export * from './BruteForceActions';
export * from './HeuristicInsertionActions';
export * from './GraphSearchActions';

export function fetchWeightedRouteSuccess(body: any) {
  return {
    type: ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS,
    body
  }
}

export function fetchWeightRouteFailure(exception: any) {
  return {
    type: ActionTypes.FETCH_WEIGHTED_ROUTE_FAILURE,
    exception
  }
}

export function updateMapText(event: any) {
  return {
    type: ActionTypes.UPDATE_MAP_TEXT,
    event
  }
}

export function toggleMatrix(body: any) {
  return {
    type: ActionTypes.TOGGLE_MATRIX,
    body
  }
}