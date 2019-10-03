import { shallow, ShallowWrapper } from 'enzyme';
import { MapInput } from './MapInput';
import React from 'react';

describe('MapInput', () => {
  let mapInput: ShallowWrapper;
  let mapText = 'city1 2 3';
  let newRouteSpy: any;
  let mapTextUpdateSpy: any;
  let fileHandler = () => {
    return null;
  };

  beforeEach(() => {
    newRouteSpy = jest.fn();
    mapTextUpdateSpy = jest.fn();

    mapInput = shallow(
      <MapInput
        getNewRoute={newRouteSpy}
        mapText={mapText}
        updateMapText={mapTextUpdateSpy}
      />);

  });

  it('should pass a test', () => {
    expect(true).toBeTruthy();
  });

  it('should provide a place to input map coordinates', () => {
    expect(mapInput.find('textarea').exists()).toBeTruthy();
  });

  it('should provide instructions', () => {
    expect(mapInput.text()).toContain('Format: city# latitude longitudeCalculate Shortest Route to all Cities');
  });

  it('should provide an example as placeholder text', () => {
    expect(mapInput.find('textarea').prop('value')).toBe('city1 2 3');
  });

  it('should provide a button to trigger a cycle calculation from the map input', () => {
    let newRouteButton = mapInput.find('button');
    expect(newRouteButton.exists()).toBeTruthy();

    newRouteButton.simulate('click');
    expect(newRouteSpy).toHaveBeenCalledWith(mapText);
  });

  it('should trigger a state change of map text when input field changes', () => {
    let input = mapInput.find('textarea');
    input.simulate('change', {target: {action: 'city1'}});
    expect(mapTextUpdateSpy).toHaveBeenCalledWith({target: {action: 'city1'}});
  });

  it('should render a dropzone', () => {
    expect(mapInput.find('.dropzone').exists()).toBeTruthy();
  });
});