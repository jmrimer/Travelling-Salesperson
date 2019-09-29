import { ActionTypes } from './types';
import { Page } from '../../website-styling/Header';

export * from './BruteForceActions';
export * from './HeuristicInsertionActions';
export * from './GraphSearchActions';
export * from './GeneticAlgorithmActions'

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

export const updateCurrentPage = (page: Page) => {
  return {
    type: ActionTypes.UPDATE_PAGE,
    page
  }
};