import { CityModel } from './CityModel';

export class MapModel {
  constructor(public cities: CityModel[] = []) {

  }

  serialize(mapText: string) {
    let mapCities = mapText.split('\n');
    this.cities = mapCities.map((textLine: string) => {
      let textColumn = textLine.split(' ');
      return new CityModel(
        textColumn[0],
        parseInt(textColumn[1]),
        parseInt(textColumn[2])
      )
    })
  }
}