import React from 'react';
import classNames from 'classnames';
import { StyledRouteInfo } from './RouteInfo';
import { StyledMapInput } from './MapInput';
import VisualGraph from './visual-grapher/SinglePathVisualGraph';
import { TourModel } from '../shared-models/TourModel';
import styled from 'styled-components';
import { Heading } from '../website-styling/default';
import DividingLine from './DividingLine';

interface Props {
  weightedRoute: TourModel;
  loading: boolean;
  mapText: string;
  getNewRoute: () => void;
  updateMapText: (e: any) => void;
  points: any[];
  className?: string;
}

export const TourDisplayer: React.FC<Props> = props => {
  let {
    weightedRoute,
    loading,
    mapText,
    getNewRoute,
    updateMapText,
    points,
    className
  } = props;

  function renderRouteOutput() {
    return <div className={'output'}>
      <Heading className={'label--output'}>OUTPUT</Heading>
      <StyledRouteInfo
        weightedRoute={weightedRoute}
        loading={loading}
      />
      {renderMap()}
    </div>;
  }

  function renderMapInput() {
    return <div className={'input'}>
      <Heading className={'title'}>INPUT</Heading>
      <StyledMapInput
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
      />
    </div>
  }

  function renderDividingLine() {
    return <DividingLine/>;
  }

  function renderMap() {
    return (
      <VisualGraph
        points={points}
        tour={weightedRoute ? weightedRoute.cycle : null}
      />
    );
  }

  return (
    <div className={classNames('tour-displayer', className)}>
      {renderMapInput()}
      {renderDividingLine()}
      {renderRouteOutput()}
    </div>
  );
};

export default (styled(TourDisplayer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin-top: 24px;
  
  .label--output, .label--input {
    width: 100%;
  }
  
  .label--output {
    text-align: right;
  }
  
  
  .input, .output {
    display: flex;
    align-items: center;
    flex-direction: column;
  }
  
  .divide {
    width: 2px;
    border: 2px solid ${(props) => props.theme.color.fontWhite};
    margin: 8px 16px;
  }
`);

