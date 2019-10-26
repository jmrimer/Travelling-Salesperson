import { CityModel } from '../shared-models/CityModel';

export class EdgeModel {

  constructor(
    public start: CityModel,
    public end: CityModel
  ) {
  }
}