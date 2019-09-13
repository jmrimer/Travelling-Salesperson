import { translateCoordinateTextToGraphReadyPoints } from './reducers';

describe('reducers', () => {
  it('should translate coordinate text to points', () => {
    let mapText = '1 87.951292 2.658162\n' +
      '2 33.466597 66.682943\n' +
      '3 91.778314 53.807184\n' +
      '4 20.526749 47.633290';
    expect(translateCoordinateTextToGraphReadyPoints(mapText)).toEqual(
      [
        {name: "1", x: 87.5, y: 83},
        {name: "2", x: 33.5, y: 19},
        {name: "3", x: 91.5, y: 32},
        {name: "4", x: 20.5, y: 38},
      ]
    );
  });
});