import React, { createRef } from 'react';
import { StyledRouteInfo } from './RouteInfo';
import { RouteModel } from './models/RouteModel';
import { connect } from 'react-redux';
import { fetchNewRouteFromText, fetchWeightedRoute, updateMapText } from './actions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';
import CytoscapeComponent from 'react-cytoscapejs';
import {
  flipVerticallyAroundCenterOf,
  rotate180AroundCenterOf,
  translatePointsToNewCenter
} from '../visual-grapher/GraphTranslator';
import { theme } from '../website-styling/default';

interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  mapText: string;
  getStaticRoute: () => void;
  getNewRoute: (mapText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  className?: string;
}

export class RouteContainer extends React.Component<Props> {
  cyRef: any;

  componentDidMount(): void {
    this.cyRef = createRef();
    this.props.getStaticRoute();
  }

  render() {
    return (
      <div className={classNames('routeContainer', this.props.className)}>
        {this.renderMapInput()}
        {RouteContainer.renderDividingLine()}
        {this.renderRouteOutput()}
        {this.renderMap()}
      </div>
    );
  }

  private renderRouteOutput() {
    return <div className={'output'}>
      OUTPUT
      <StyledRouteInfo
        weightedRoute={this.props.weightedRoute}
        loading={this.props.loading}
      />
    </div>;
  }

  private renderMapInput() {
    return <div className={'input'}>
      INPUT
      <StyledMapInput
        getNewRoute={this.props.getNewRoute}
        updateMapText={this.props.updateMapText}
        mapText={this.props.mapText}
      />
    </div>
  }

  private static renderDividingLine() {
    return <div className={'divide'}>&nbsp;</div>
  }

  private renderMap() {
    let points = translatePointsToNewCenter(
      this.props.points,
      {x: 300, y: 300}
    );

    const elements = points.map((point) => {
      return {data: {id: point.name, label: point.name}, position: {x: point.x, y: point.y}}
    });

    return (
      <div>
        <CytoscapeComponent
          elements={elements}
          style={
            {
              width: '600px',
              height: '600px',
            }
          }
          stylesheet={[
            {
              selector: 'node',
              style: {
                width: 10,
                height: 10,
                shape: 'circle',
                'background-color': theme.color.fontWhite,
                color: theme.color.wedgewood,
                label: 'data(label)',
                'font-size': 10,
                'font-weight': 'bold',
                'min-zoomed-font-size': 10,
                'text-valign': 'center',
                'text-halign': 'center',
                'text-outline-color': theme.color.midnight,
                'text-outline-width': 0.4
                // label: {
                // }
              }
            },
            {
              select: 'label',
              style: {
                color: 'pink'
              }
            }
          ]}
          cy={(cy: any) => {
            cy.fit();
          }}
        />
      </div>
    );
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading,
  mapText: state.mapText,
  points: state.points
});

const mapDispatchToProps = {
  getStaticRoute: fetchWeightedRoute,
  getNewRoute: fetchNewRouteFromText,
  updateMapText: updateMapText
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(RouteContainer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin-top: 24px;
  
  .input, .output {
    font-family: Righteous, cursive;
    font-size: 36px;
    color: ${(props) => props.theme.color.fontWhite};
  }
  
  .divide {
    width: 2px;
    border: 2px solid ${(props) => props.theme.color.fontWhite};
    margin: 8px 16px;
  }
`);