import { RouteModel } from './models/RouteModel';
import { ActionTypes } from '../actions/ActionTypes';
import { NodeModel } from '../shortest-path-through-graph/models/NodeModel';
import { flipVerticallyAroundCenterOf, rotate180AroundCenterOf } from '../visual-grapher/GraphTranslator';

function createInitialMatrix() {
  return [
    [false, true, true, true, false, false, false, false, false, false, false],
    [false, false, true, false, false, false, false, false, false, false, false],
    [false, false, false, true, true, false, false, false, false, false, false],
    [false, false, false, false, true, true, true, false, false, false, false],
    [false, false, false, false, false, false, true, true, false, false, false],
    [false, false, false, false, false, false, false, true, false, false, false],
    [false, false, false, false, false, false, false, false, true, true, false],
    [false, false, false, false, false, false, false, false, true, true, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, false],
  ]
}

let startingMap = '1 87.951292 2.658162\n' +
  '2 33.466597 66.682943\n' +
  '3 91.778314 53.807184\n' +
  '4 20.526749 47.633290';

export function translateCoordinateTextToGraphReadyPoints(mapText: string) {
  let lines = mapText.split('\n');
  let points = lines.map((line: string) => {
    let textColumn = line.split(' ');
    return {
      name: textColumn[0],
      x: parseFloat(textColumn[1]),
      y: parseFloat(textColumn[2])
    };
  });
  return flipVerticallyAroundCenterOf(
    rotate180AroundCenterOf(points)
  );
}

const initState = {
  weightedRoute: null,
  mapText: startingMap,
  shortestBFSPath: null,
  shortestDFSPath: null,
  adjacencyMatrix: createInitialMatrix(),
  points: translateCoordinateTextToGraphReadyPoints(startingMap)
};

function serializeJSONtoRoute(json: any) {
  return new RouteModel(json.route, json.weight);
}

function serializeJSONToPath(body: any) {
  let path: NodeModel[];
  path = [];
  body.map((node: NodeModel) => {
    return path.push(node);
  });
  return path;
}

function toggleMatrix(matrix: any, keyValuePair: any) {
  let newMatrix = Object.assign([], matrix);
  let start = keyValuePair.startId;
  let end = keyValuePair.endId;
  newMatrix[start][end] = !newMatrix[start][end];
  return newMatrix;
}

const reducer = (state = initState, action: any) => {
  function textFromBody(event: any) {
    return event.target.value;
  }

  switch (action.type) {
    case ActionTypes.FETCH_WEIGHTED_ROUTE_REQUEST:
      return {...state, loading: true};
    case ActionTypes.POST_NEW_ROUTE_REQUEST:
      return {...state, loading: true};
    case ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS:
      return {...state, weightedRoute: serializeJSONtoRoute(action.body), loading: false};
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
    default:
      return state;
  }
};

export default reducer;