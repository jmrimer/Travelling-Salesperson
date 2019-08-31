import React from 'react';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { connect } from 'react-redux';
import { fetchNewRoute, fetchWeightedRoute } from '../actions/RouteActions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';
import { MapModel } from '../actions/MapModel';


interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  getStaticRoute: () => void;
  getNewRoute: (map: MapModel) => void;
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
    return <StyledMapInput getNewRoute={this.props.getNewRoute}/>;
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading
});

const mapDispatchToProps = {
  getStaticRoute: fetchWeightedRoute,
  getNewRoute: fetchNewRoute
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(RouteContainer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
`);