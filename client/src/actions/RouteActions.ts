import { ActionTypes } from './ActionTypes';
import 'cross-fetch/polyfill';

function fetchWeightedRouteRequest() {
  return {
    type: ActionTypes.FETCH_WEIGHTED_ROUTE_REQUEST
  };
}


function fetchWeightedRouteSuccess(body: any) {
  return {
    type: ActionTypes.FETCH_WEIGHTED_ROUTE_SUCCESS,
    body
  }
}

function fetchWeightRouteFailure(exception: any) {
  return {
    type: ActionTypes.FETCH_WEIGHTED_ROUTE_FAILURE,
    exception
  }
}

export function fetchWeightedRoute() {
  return function (dispatch: any) {
    dispatch(fetchWeightedRouteRequest());
    return fetch('http://localhost:8080/api/weightedRoute')
      .then(response => response.json())
      .then(body => dispatch(fetchWeightedRouteSuccess(body)))
      .catch(exception => dispatch(fetchWeightRouteFailure(exception)));
  };
}