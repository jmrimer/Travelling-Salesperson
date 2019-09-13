import { MapModel } from '../../shared-models/MapModel';
import { ActionTypes } from './types';
import { fetchWeightedRouteSuccess, fetchWeightRouteFailure } from './index';

export function fetchNewTourViaBruteForce(map: MapModel) {
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

export function postNewRouteRequest() {
  return {
    type: ActionTypes.POST_NEW_ROUTE_REQUEST,
  };
}

export function fetchNewRouteFromText(mapText: string) {
  let map = new MapModel();
  map.serialize(mapText);
  return fetchNewTourViaBruteForce(map);
}