import { CityModel } from './CityModel';

export class TourModel {
  constructor(
    public route: CityModel[] = [],
    public weight: number = 0
  ) {
  }
}