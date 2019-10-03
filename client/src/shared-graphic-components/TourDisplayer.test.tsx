import { shallow, ShallowWrapper } from 'enzyme';
import { TourModel } from '../shared-models/TourModel';
import { StyledRouteInfo } from './RouteInfo';
import { StyledMapInput } from './MapInput';
import { SinglePathVisualGraph } from './visual-grapher/SinglePathVisualGraph';
import React from 'react';
import { HeuristicInsertionContainer } from '../heuristic-instertion/HeuristicInsertionContainer';
import { TourDisplayer } from './TourDisplayer';

describe('TourDisplayer', () => {
  let subject: ShallowWrapper;
  let weightedRoute: TourModel;
  let mapText = 'map text';
  let getNewRoute = () => {
    return null;
  };
  let updateMapText = () => {
    return null;
  };

  beforeEach(() => {
    weightedRoute = new TourModel();
    subject = shallow(
      <TourDisplayer
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
        weightedRoute={weightedRoute}
        loading={false}
        points={['point1', 'point2']}
      />
    );

  });

  it('should setup and display weightedRoute info', () => {
    expect(subject.find(StyledRouteInfo).props().weightedRoute).toBe(weightedRoute);
    expect(subject.find(StyledRouteInfo).exists()).toBeTruthy();
  });

  it('should display and facilitate map input', () => {
    expect(subject.find(StyledMapInput).exists()).toBeTruthy();
    expect(subject.find(StyledMapInput).prop('getNewRoute')).toBe(getNewRoute);
    expect(subject.find(StyledMapInput).prop('updateMapText')).toBe(updateMapText);
    expect(subject.find(StyledMapInput).prop('mapText')).toBe(mapText);
  });

  it('should display a graph and provide points & maps to it', () => {
    expect(subject.find(SinglePathVisualGraph).exists()).toBeTruthy();
    expect(subject.find(SinglePathVisualGraph).prop('points')).toEqual(['point1', 'point2']);
    expect(subject.find(SinglePathVisualGraph).prop('tour')).toEqual(weightedRoute.cycle);
  });
});