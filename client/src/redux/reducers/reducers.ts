import { ActionTypes } from '../actions/types';
import { Page } from '../../website-styling/Header';
import {
  createInitialMatrix,
  serializeJSONToPath,
  serializeJSONtoTour,
  serializeJSONToTrial,
  startingMap,
  textFromBody,
  toggleMatrix,
  translateCoordinateTextToGraphReadyPoints
} from './ReducerHelpers';
import { TrialModel } from '../../genetic-algorithm/TrialModel';

const initState = {
  weightedRoute: null,
  mapText: startingMap,
  shortestBFSPath: null,
  shortestDFSPath: null,
  adjacencyMatrix: createInitialMatrix(),
  points: translateCoordinateTextToGraphReadyPoints(startingMap),
  currentPage: Page.BRUTE_FORCE,
  currentGeneration: 0,
  trial: new TrialModel()
};

function incrementGeneration(current: number, max: number) {
  current++;
  console.log(current);
  console.log(max);
  return current === max ? 0 : current;
}

function decrementGeneration(current: number, max: number) {
  current--;
  console.log(current);
  console.log(max);
  return current === -1 ? max - 1 : current;
}

const reducer = (state = initState, action: any) => {
  switch (action.type) {
    case ActionTypes.FETCH_WEIGHTED_ROUTE_REQUEST:
      return {...state, loading: true};
    case ActionTypes.POST_NEW_ROUTE_REQUEST:
      return {...state, loading: true};
    case ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS:
      return {...state, weightedRoute: serializeJSONtoTour(action.body), loading: false};
    case ActionTypes.UPDATE_MAP_TEXT:
      return {
        ...state,
        mapText: textFromBody(action.event),
        points: translateCoordinateTextToGraphReadyPoints(textFromBody(action.event)),
        weightedRoute: null
      };
    case ActionTypes.POST_BFS_REQUEST:
      return {...state, loading: true};
    case ActionTypes.POST_BFS_SUCCESS:
      return {...state, shortestBFSPath: serializeJSONToPath(action.body), loading: false};
    case ActionTypes.POST_DFS_REQUEST:
      return {...state, loading: true};
    case ActionTypes.POST_DFS_SUCCESS:
      return {...state, shortestDFSPath: serializeJSONToPath(action.body), loading: false};
    case ActionTypes.TOGGLE_MATRIX:
      return {...state, adjacencyMatrix: toggleMatrix(state.adjacencyMatrix, action.body)};
    case ActionTypes.POST_NEW_TOUR_VIA_INSERTION_REQUEST:
      return {...state, loading: true};
    case ActionTypes.UPDATE_PAGE:
      return {...state, currentPage: action.page};
    case ActionTypes.POST_NEW_TRIAL_REQUEST:
      return {...state, loading: true};
    case ActionTypes.FETCH_TRIAL_SUCCESS:
      return {...state, trial: serializeJSONToTrial(action.body), loading: false};
    case ActionTypes.NEXT_GENERATION:
      return {...state, currentGeneration: incrementGeneration(state.currentGeneration, state.trial.generations.length)};
    case ActionTypes.PREVIOUS_GENERATION:
      return {...state, currentGeneration: decrementGeneration(state.currentGeneration, state.trial.generations.length)};
    default:
      return state;
  }
};

export default reducer;