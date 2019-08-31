import { ActionTypes } from './ActionTypes';
import { MapModel } from './MapModel';

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

function postNewRouteRequest() {
  return {
    type: ActionTypes.POST_NEW_ROUTE_REQUEST,
  };
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

export function fetchNewRoute(map: MapModel) {
  return function (dispatch: any) {
    dispatch(postNewRouteRequest());
    return fetch(
      'http://localhost:8080/api/weightedRoute',
      {
        method: 'post',
        body: JSON.stringify(map)
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchWeightedRouteSuccess(body)))
      .catch(exception => dispatch(fetchWeightRouteFailure(exception)));
  };
}
