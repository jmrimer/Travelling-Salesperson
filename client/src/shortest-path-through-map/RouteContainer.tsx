import React from 'react';
import { StyledRouteInfo } from './RouteInfo';
import { RouteModel } from './models/RouteModel';
import { connect } from 'react-redux';
import { fetchNewRouteFromText, fetchWeightedRoute, updateMapText } from './actions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';
import CytoscapeComponent from 'react-cytoscapejs';

interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  mapText: string;
  getStaticRoute: () => void;
  getNewRoute: (mapText: string) => void;
  updateMapText: (e: any) => void;
  className?: string;
}

export class RouteContainer extends React.Component<Props> {
  componentDidMount(): void {
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
    const elements = [
      {data: {id: 'one', label: 'Node 1'}, position: {x: 10, y: 10}},
      {data: {id: 'two', label: 'Node 2'}, position: {x: 590 , y: 590}},
      {data: {source: 'one', target: 'two', label: 'Edge from Node1 to Node2'}}
    ];

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
                shape: 'rectangle'
              }
            }
          ]}
        />
      </div>
    );
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading,
  mapText: state.mapText
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