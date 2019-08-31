import { shallow, ShallowWrapper } from 'enzyme';
import { MapInput } from './MapInput';
import React from 'react';
import { MapModel } from '../actions/MapModel';
import { CityModel } from '../models/CityModel';

describe('MapInput', () => {
  let mapInput: ShallowWrapper;
  let newRouteSpy: any;

  beforeEach(() => {
    newRouteSpy = jest.fn();
    mapInput = shallow(<MapInput getNewRoute={newRouteSpy}/>);

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
    expect(mapInput.find('textarea').text()).toBe(
      '1 87.951292 2.658162\n' +
      '2 33.466597 66.682943\n' +
      '3 91.778314 53.807184\n' +
      '4 20.526749 47.633290'
    );
  });

  it('should provide a button to trigger a route calculation from the map input', () => {
    let newRouteButton = mapInput.find('button');
    expect(newRouteButton.exists()).toBeTruthy();

    let map = new MapModel([
      new CityModel('1', 87.951292, 2.658162),
      new CityModel('2', 33.466597, 66.682943),
      new CityModel('3', 91.778314, 53.807184),
      new CityModel('4', 20.526749, 47.633290),
    ]);
    newRouteButton.simulate('click');
    expect(newRouteSpy).toHaveBeenCalledWith(map);
  });
});

export default function () {

};