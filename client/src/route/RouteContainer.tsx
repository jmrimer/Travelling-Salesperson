import React from 'react';
import { StyledRouteInfo } from './RouteInfo';
import { RouteModel } from './RouteModel';
import { connect } from 'react-redux';
import { fetchNewRouteFromText, fetchWeightedRoute, updateMapText } from './RouteActions';
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
        {this.renderDividingLine()}
        {this.renderRouteOutput()}
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

  private renderDividingLine() {
    return <div className={'divide'}>&nbsp;</div>
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