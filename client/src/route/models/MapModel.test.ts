import { MapModel } from './MapModel';
import { CityModel } from './CityModel';

describe('MapModel', () => {
  it('should serialize text into a map', () => {
    let map = new MapModel();
    map.serialize(
      'city1 1 1\n' +
      'city2 2 2\n' +
      'city3 3 3'
    );
    expect(map).toEqual(new MapModel([
      new CityModel('city1', 1, 1),
      new CityModel('city2', 2, 2),
      new CityModel('city3', 3, 3),
    ]))
  });
});