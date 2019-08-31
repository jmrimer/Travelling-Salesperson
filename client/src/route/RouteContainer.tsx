import React from 'react';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { connect } from 'react-redux';
import { fetchNewRouteFromText, fetchWeightedRoute } from '../actions/RouteActions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';


interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  getStaticRoute: () => void;
  getNewRoute: (mapText: string) => void;
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
      mapText={RouteContainer.exampleMap()}
    />;
  }

  private static exampleMap() {
    return (
      '1 87.951292 2.658162\n' +
      '2 33.466597 66.682943\n' +
      '3 91.778314 53.807184\n' +
      '4 20.526749 47.633290'
    );
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading
});

const mapDispatchToProps = {
  getStaticRoute: fetchWeightedRoute,
  getNewRoute: fetchNewRouteFromText
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(RouteContainer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
`);