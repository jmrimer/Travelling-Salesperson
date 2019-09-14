import { MapModel } from '../../shared-models/MapModel';
import { fetchWeightedRouteSuccess, fetchWeightRouteFailure } from './index';
import { ActionTypes } from './types';

export const postNewTourViaInsertionRequest = () => {
  return {
    type: ActionTypes.POST_NEW_TOUR_VIA_INSERTION_REQUEST
  }
};

export const fetchNewTourViaInsertionFromText = (mapText: string) => {
  let map = new MapModel();
  map.serialize(mapText);
  return fetchNewTourViaInsertion(map);
};

function fetchNewTourViaInsertion(map: MapModel) {
  return function (dispatch: any) {
    dispatch(postNewTourViaInsertionRequest());
    return fetch(
      'http://localhost:8080/api/weighted-route-via-insertion',
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
