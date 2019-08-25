import { RouteModel } from '../models/RouteModel';
import { CityModel } from '../models/CityModel';
import { ActionTypes } from '../actions/ActionTypes';

const initState = {
  weightedRoute: new RouteModel(
    [new CityModel("no map added...", 0, 0)],
    0
  )
};

function serializeJSONtoRoute(json: any) {
  return new RouteModel(json.route, json.weight);
}

const reducer = (state = initState, action: any) => {
  switch (action.type) {
    case ActionTypes.FETCH_WEIGHTED_ROUTE_REQUEST:
      return {...state, loading: true};
    case ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS:
      return {...state, weightedRoute: serializeJSONtoRoute(action.body), loading: false};
    default:
      return state;
  }
};

export default reducer;