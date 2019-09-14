import React from 'react';
import { CityModel } from '../shared-models/CityModel';
import { RouteModel } from '../shared-models/RouteModel';
import styled from 'styled-components';
import classNames from 'classnames';

interface Props {
  weightedRoute: RouteModel | null,
  loading: boolean,
  className?: string
}

export class RouteInfo extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('weightedRoute', this.props.className)}>
        {this.conditionallyRenderInfo()}
      </div>
    );
  }

  private renderLoading() {
    return (
      <div className={'loading'}>
        <span>Loading route, please wait...</span>
        <span className={'label__caution'}>(Caution: Brute forcing a 12-city map can take minutes)</span>
      </div>
    );
  }

  private renderWeight(weight: number) {
    return <div className={'weight'}>
      Weight: {weight}
    </div>;
  }

  private renderRoute(route: CityModel[]) {
    return (
      <div className={'route'}>
        Route: {this.cityList(route)}
      </div>
    );
  }

  private cityList(route: CityModel[]) {
    return route.map((city: CityModel, index: number) => {
      if (this.isLastCity(index, route)) {
        return this.cityEntry(index, city);
      }
      return this.lastCityEntry(index, city);
    });
  }

  private lastCityEntry(index: number, city: CityModel) {
    return <span key={index}>{city.name}</span>;
  }

  private cityEntry(index: number, city: CityModel) {
    return <span key={index}>{city.name}, </span>;
  }

  private isLastCity(index: number, route: CityModel[]) {
    return index < route.length - 1;
  }

  private conditionallyRenderInfo() {
    if (this.props.weightedRoute) {
      return <>
        {this.renderRoute(this.props.weightedRoute.route)}
        {this.renderWeight(this.props.weightedRoute.weight)}
      </>;
    }

    if (!this.props.weightedRoute && this.props.loading) {
      return this.renderLoading();

    }

    return (<div className={'spacer'}><span>&nbsp;</span><span>&nbsp;</span></div>)
  }
}

export const StyledRouteInfo = styled(RouteInfo)`
  font-family: Righteous, cursive;
  color: ${(props) => props.theme.color.fontWhite};
  font-size: 24px;
  margin-top: 24px;
  
  .spacer {
    display: flex;
    flex-direction: column;
  }
  
  .loading {
    display: flex;
    flex-direction: column;
  }
  
  .label__caution {
    color: ${(props) => props.theme.color.maroon5};
  }
`;