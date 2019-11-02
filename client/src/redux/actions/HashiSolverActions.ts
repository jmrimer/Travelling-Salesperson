import { ActionTypes } from './types';

export const getStaticHashiMap = () => {
  return {
    type: ActionTypes.FETCH_STATIC_HASHI_MAP_REQUEST
  }
};

export function fetchStaticMap() {
  return function (dispatch: any) {
    dispatch(getStaticHashiMap());
    return fetch(
      'http://localhost:8080/api/hashi-solver',
      {
        method: 'get',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchStaticHashiMapSuccess(body)))
      .catch(exception => dispatch(fetchStaticHashiMapFailure(exception)))
  };
}

export const fetchStaticHashiMapSuccess = (body: any) => {
  return {
    type: ActionTypes.FETCH_STATIC_HASHI_MAP_SUCCESS,
    body
  }
};

export const fetchStaticHashiMapFailure = (exception: any) => {
  return {
    type: ActionTypes.FETCH_STATIC_HASHI_MAP_FAILURE,
    exception
  }
};