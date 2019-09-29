import * as React from 'react';
import { RouteInfo } from './RouteInfo';
import { TourModel } from '../shared-models/TourModel';
import { CityModel } from '../shared-models/CityModel';
import { shallow, ShallowWrapper } from 'enzyme';

describe('RouteInfo', () => {
  let homePage: ShallowWrapper;
  let cityList: CityModel[];

  beforeEach(() => {
    cityList = [
      new CityModel('city1', 0, 0),
      new CityModel('city2', 1, 1)
    ];

    homePage = shallow(
      <RouteInfo
        loading={false}
        weightedRoute={new TourModel(cityList, 10)}
      />
    );
  });

  it('should render the weight of the weightedRoute', () => {
    expect(homePage.find('.weight').text()).toContain('10');
  });

  it('should render the list of cities in the weightedRoute', () => {
    expect(homePage.text()).toContain('Route: city1, city2');
  });

  it('should display loading info', () => {
    expect(homePage.find('.loading').exists()).toBeFalsy();
    homePage = shallow(
      <RouteInfo
        loading={true}
        weightedRoute={null}
      />
    );
    expect(homePage.find('.loading').exists()).toBeTruthy();
  });
});