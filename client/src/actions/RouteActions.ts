import { ActionTypes } from './ActionTypes';
import { MapModel } from '../route/MapModel';

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

export function updateMapText(event: any) {
  return {
    type: ActionTypes.UPDATE_MAP_TEXT,
    event
  }
}

export function fetchWeightedRoute() {
  return function (dispatch: any) {
    dispatch(fetchWeightedRouteRequest());
    return fetch('http://localhost:8080/api/weighted-route')
      .then(response => response.json())
      .then(body => dispatch(fetchWeightedRouteSuccess(body)))
      .catch(exception => dispatch(fetchWeightRouteFailure(exception)));
  };
}

export function fetchNewRouteFromText(mapText: string) {
  let map = new MapModel();
  map.serialize(mapText);
  return fetchNewRoute(map);
}

function fetchNewRoute(map: MapModel) {
  return function (dispatch: any) {
    dispatch(postNewRouteRequest());
    return fetch(
      'http://localhost:8080/api/weighted-route',
      {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(map),
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchWeightedRouteSuccess(body)))
      .catch(exception => dispatch(fetchWeightRouteFailure(exception)));
  };
}
