import { shallow, ShallowWrapper } from 'enzyme';
import { MapInput } from './MapInput';
import React from 'react';
import { MapModel } from './MapModel';
import { CityModel } from '../models/CityModel';

describe('MapInput', () => {
  let mapInput: ShallowWrapper;
  let newRouteSpy: any;

  beforeEach(() => {
    newRouteSpy = jest.fn();
    mapInput = shallow(
      <MapInput
        getNewRoute={newRouteSpy}
        mapText={'city1 2 3'}
      />);

  });

  it('should pass a test', () => {
    expect(true).toBeTruthy();
  });

  it('should provide a place to input map coordinates', () => {
    expect(mapInput.find('textarea').exists()).toBeTruthy();
  });

  it('should provide instructions', () => {
    expect(mapInput.text()).toContain('Type your map coordinates into this box in the following format:' +
      'CityNumber Latitude Longitude' + 'CityNumber Latitude Longitude' + 'CityNumber Latitude Longitude' +
      '*no spaces per column, only between columns')
  });

  it('should provide an example as placeholder text', () => {
    expect(mapInput.find('textarea').text()).toBe('city1 2 3');
  });

  it('should provide a button to trigger a route calculation from the map input', () => {
    let newRouteButton = mapInput.find('button');
    expect(newRouteButton.exists()).toBeTruthy();

    let map = new MapModel([
      new CityModel('city1', 2, 3),
    ]);
    newRouteButton.simulate('click');
    expect(newRouteSpy).toHaveBeenCalledWith(map);
  });

  it('should trigger a state change of map text when input field changes', () => {
    let input = mapInput.find('textarea');
    input.simulate('change', {target: {action: 'city1'}});

  });

});

export default function () {

};