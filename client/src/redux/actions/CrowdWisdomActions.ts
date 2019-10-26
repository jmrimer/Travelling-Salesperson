import { ActionTypes } from './types';
import { WisdomRequest } from '../../crowd-wisdom/WisdomRequest';

export const postNewWisdomRequest = () => {
  return {
    type: ActionTypes.POST_NEW_WISDOM_REQUEST
  }
};

export function fetchWisdomFromModel(wisdomRequest: WisdomRequest) {
  return function (dispatch: any) {
    dispatch(postNewWisdomRequest());
    return fetch(
      'http://localhost:8080/api/crowd-wisdom',
      {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(wisdomRequest)
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchTrialSuccess(body)))
      .catch(exception => dispatch(fetchTrialFailure(exception)))
  };
}

export const fetchTrialSuccess = (body: any) => {
  return {
    type: ActionTypes.FETCH_WISDOM_SUCCESS,
    body
  }
};

export const fetchTrialFailure = (exception: any) => {
  return {
    type: ActionTypes.FETCH_WISDOM_SUCCESS,
    exception
  }
};