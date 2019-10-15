import { ActionTypes } from './types';
import { TrialRequest } from '../../genetic-algorithm/TrialRequest';

export const updateStartingPopulation = (e: any) => {
  return {
    type: ActionTypes.UPDATE_STARTING_POPULATION,
    body: e.target.value
  }
};
export const updatePopulationCap = (e: any) => {
  return {
    type: ActionTypes.UPDATE_POPULATION_CAP,
    body: e.target.value
  }
};
export const updateTotalGenerations = (e: any) => {
  return {
    type: ActionTypes.UPDATE_TOTAL_GENERATIONS,
    body: e.target.value
  }
};
export const updateMaxMutationSize = (e: any) => {
  return {
    type: ActionTypes.UPDATE_MAX_MUTATION_SIZE,
    body: e.target.value
  }
};
export const updateMutationRate = (e: any) => {
  return {
    type: ActionTypes.UPDATE_MUTATION_RATE,
    body: e.target.value
  }
};

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


export const previousGeneration = () => {
  return {
    type: ActionTypes.PREVIOUS_GENERATION
  }
};

export function fetchTrialFromModel(trialRequest: TrialRequest) {
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
        body: JSON.stringify(trialRequest)
      }
    )
      .then(response => response.json())
      .then(body => dispatch(fetchTrialSuccess(body)))
      .catch(exception => dispatch(fetchTrialFailure(exception)))
  };
}

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