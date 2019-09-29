import { ActionTypes } from './types';
import { MapModel } from '../../shared-models/MapModel';

export const postNewTrialRequest = () => {
  return {
    type: ActionTypes.POST_NEW_TRIAL_REQUEST
  }
};

export const nextGeneration = () => {
  return {
    type: ActionTypes.NEXT_GENERATION
  }
};

export function fetchNewTrial(mapText: string) {
  let map = new MapModel();
  map.serialize(mapText);

  return function (dispatch: any) {
    dispatch(postNewTrialRequest());
    return fetch(
      'http://localhost:8080/api/genetic-trial',
      {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(map)
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchTrialSuccess(body)))
      .catch(exception => dispatch(fetchTrialFailure(exception)))
  };
};

export const fetchTrialSuccess = (body: any) => {
  return {
    type: ActionTypes.FETCH_TRIAL_SUCCESS,
    body
  }
};

export const fetchTrialFailure = (exception: any) => {
  return {
    type: ActionTypes.FETCH_TRIAL_FAILURE,
    exception
  }
};