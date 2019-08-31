import React from 'react';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { connect } from 'react-redux';
import { fetchNewRouteFromText, fetchWeightedRoute, updateMapText } from '../actions/RouteActions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';


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
        {this.renderRouteOutput()}
      </div>
    );
  }

  private renderRouteOutput() {
    return <>
      {
        this.props.loading
          ? <div className={'loading'}>Loading route...</div>
          : <RouteInfo weightedRoute={this.props.weightedRoute}/>
      }
    </>;
  }

  private renderMapInput() {
    return <StyledMapInput
      getNewRoute={this.props.getNewRoute}
      updateMapText={this.props.updateMapText}
      mapText={this.props.mapText}
    />;
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
`);