import React from 'react';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { connect } from 'react-redux';
import { fetchWeightedRoute } from '../actions/RouteActions';
import { StyledMapInput } from './MapInput';
import styled from 'styled-components';
import classNames from 'classnames';


interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  getRoute: () => void;
  className?: string;
}

export class RouteContainer extends React.Component<Props> {
  componentDidMount(): void {
    this.props.getRoute();
  }

  render() {
    return (
      <div className={classNames('routeContainer', this.props.className)}>
        {RouteContainer.renderMapInput()}
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

  private static renderMapInput() {
    return <StyledMapInput/>;
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading
});

const mapDispatchToProps = {
  getRoute: fetchWeightedRoute
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(RouteContainer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
`);