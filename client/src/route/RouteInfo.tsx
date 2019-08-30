import React from 'react';
import { CityModel } from '../models/CityModel';
import { RouteModel } from '../models/RouteModel';

interface Props {
  weightedRoute: RouteModel,
}

export class RouteInfo extends React.Component<Props> {
  render() {
    return (
      <div className={'weightedRoute'}>
        {this.renderWeight(this.props.weightedRoute.weight)}
        {this.renderRoute(this.props.weightedRoute.route)}
      </div>
    );
  }

  private renderWeight(weight: number) {
    return <div className={'weight'}>
      {weight}
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
}