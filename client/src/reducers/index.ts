import { RouteModel } from '../models/RouteModel';
import { CityModel } from '../models/CityModel';

const initState = {
  route: new RouteModel(
    [new CityModel("no map added...", 0, 0)],
    0
  )
};

const reducer = (state = initState, action: any) => {
  if (action.type === 'SET_ROUTE') {
    return {
      ...state,
      route: action.route
    }
  }
  return state;
};

export default reducer;