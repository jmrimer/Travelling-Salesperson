import { CityModel } from './CityModel';

export class TourModel {
  constructor(
    public cycle: CityModel[] = [],
    public weight: number = 0
  ) {
  }
}