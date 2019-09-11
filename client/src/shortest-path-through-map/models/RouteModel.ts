import { CityModel } from './CityModel';

export class RouteModel {
  constructor(
    public route: CityModel[] = [],
    public weight: number = 0
  ) {
  }
}