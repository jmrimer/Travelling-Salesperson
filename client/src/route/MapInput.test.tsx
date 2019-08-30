import { shallow, ShallowWrapper } from 'enzyme';
import { MapInput } from './MapInput';
import React from 'react';

describe('MapInput', () => {
  let mapInput: ShallowWrapper;

  beforeEach(() => {
    mapInput = shallow(<MapInput/>);
  });

  it('should pass a test', () => {
    expect(true).toBeTruthy();
  });

  it('should provide a place to input map coordinates', () => {
    expect(mapInput.find('textarea').exists()).toBeTruthy();
  });

  it('should provide instructions', () => {
    expect(mapInput.text()).toContain('Type your map coordinates ' +
      'into this box in the following format: \n' +
      'CityNumber Latitude Longitude\n' +
      'CityNumber Latitude Longitude\n' +
      'CityNumber Latitude Longitude\n' +
      '*no spaces per column, only between columns')
  });

  it('should provide an example as placeholder text', () => {
    expect(mapInput.find('textarea').text()).toBe(
      '1 87.951292 2.658162\n' +
      '2 33.466597 66.682943\n' +
      '3 91.778314 53.807184\n' +
      '4 20.526749 47.633290'
    );
  });

  it('should provide a button to trigger a route calculation from the map input', () => {
    expect(mapInput.find('button').exists()).toBeTruthy();
  });
});

export default function () {

};