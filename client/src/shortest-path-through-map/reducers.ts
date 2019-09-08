import { RouteModel } from './RouteModel';
import { CityModel } from './models/CityModel';
import { ActionTypes } from '../actions/ActionTypes';
import { NodeModel } from '../shortest-path-through-graph/NodeModel';

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

const initState = {
  weightedRoute: new RouteModel(
    [new CityModel("no map added...", 0, 0)],
    0
  ),
  mapText: startingMap,
  shortestPath: null,
  adjacencyMatrix: createInitialMatrix()
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
      return {...state, mapText: textFromBody(action.event)};
    case ActionTypes.POST_BFS_REQUEST:
      return {...state, loading: true};
    case ActionTypes.POST_BFS_SUCCESS:
      return {...state, shortestPath: serializeJSONToPath(action.body), loading: false};
    case ActionTypes.TOGGLE_MATRIX:
      return {...state, adjacencyMatrix: toggleMatrix(state.adjacencyMatrix, action.body)};
    default:
      return state;
  }
};

export default reducer;