import { MapModel } from '../../shared-models/MapModel';
import { postNewRouteRequest } from './BruteForceActions';
import { fetchWeightedRouteSuccess, fetchWeightRouteFailure } from './index';

export function fetchNewTourViaInsertionFromText(mapText: string) {
  let map = new MapModel();
  map.serialize(mapText);
  return fetchNewTourViaInsertion(map);
}

function fetchNewTourViaInsertion(map: MapModel) {
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
