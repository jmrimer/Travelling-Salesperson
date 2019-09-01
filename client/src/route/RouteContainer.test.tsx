import { shallow, ShallowWrapper } from 'enzyme';
import React from 'react';
import { RouteContainer } from './RouteContainer';
import { StyledRouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { StyledMapInput } from './MapInput';

describe('RouteContainer', () => {
  let routeContainer: ShallowWrapper;
  let weightedRoute: RouteModel;
  let mapText = 'map text';
  let getNewRoute = () => {
    return null;
  };
  let updateMapText = () => {
    return null;

  };
  beforeEach(() => {

    weightedRoute = new RouteModel();
    routeContainer = shallow(
      <RouteContainer
        getStaticRoute={() => {
        }}
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
        weightedRoute={weightedRoute}
        loading={false}/>
    );

  });

  it('should have a div', () => {
    expect(routeContainer.find('div').exists()).toBeTruthy();
  });

  it('should setup and display weightedRoute info', () => {
    expect(routeContainer.find(StyledRouteInfo).props().weightedRoute).toBe(weightedRoute);
    expect(routeContainer.find(StyledRouteInfo).exists()).toBeTruthy();
  });

  it('should display loading info', () => {
    expect(routeContainer.find('.loading').exists()).toBeFalsy();
    routeContainer = shallow(
      <RouteContainer
        getStaticRoute={() => {
        }}
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
        weightedRoute={weightedRoute}
        loading={true}
      />
    );
    expect(routeContainer.find('.loading').exists()).toBeTruthy();
  });

  it('should display and facilitate map input', () => {
    expect(routeContainer.find(StyledMapInput).exists()).toBeTruthy();
    expect(routeContainer.find(StyledMapInput).prop('getNewRoute')).toBe(getNewRoute);
    expect(routeContainer.find(StyledMapInput).prop('updateMapText')).toBe(updateMapText);
    expect(routeContainer.find(StyledMapInput).prop('mapText')).toBe(mapText);
  });
});
