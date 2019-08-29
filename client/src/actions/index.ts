import { RouteModel } from '../models/RouteModel';

export const setRoute = (route: RouteModel) => {
  return {
    type: 'SET_ROUTE',
    route: route
  }
};
