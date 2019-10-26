import { serializeJSONToTrial, translateCoordinateTextToGraphReadyPoints } from './ReducerHelpers';
import { TrialModel } from '../../genetic-algorithm/TrialModel';
import { GenerationModel } from '../../shared-models/GenerationModel';
import { TourModel } from '../../shared-models/TourModel';
import { CityModel } from '../../shared-models/CityModel';

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

  it('should serialize trial JSON to trial', () => {
    let trialJSON = {
      generations: [
        {
          generation: 0,
          parents: [
            {
              route:
                [
                  {name: '1', latitude: 1, longitude: 1},
                  {name: '2', latitude: 2, longitude: 2},
                  {name: '3', latitude: 3, longitude: 3},
                ]
              , weight: 0
            },
          ],
          children: []
        },
        {
          generation: 1,
          parents: [
            {route: [
                {name: '1', latitude: 1, longitude: 1},
                {name: '2', latitude: 2, longitude: 2},
                {name: '3', latitude: 3, longitude: 3},
              ], weight: 0},
          ],
          children: [{route: [
              {name: '1', latitude: 1, longitude: 1},
              {name: '3', latitude: 3, longitude: 3},
              {name: '2', latitude: 2, longitude: 2},
            ], weight: 0}]
        },
      ]
    };

    let city1 = new CityModel('1', 1, 1);
    let city2 = new CityModel('2', 2, 2);
    let city3 = new CityModel('3', 3, 3);

    let gen0 = new GenerationModel(
      0,
      [
        new TourModel([city1, city2, city3], 0)
      ],
      []
    );

    let gen1 = new GenerationModel(
      1,
      [
        new TourModel([city1, city2, city3], 0)
      ],
      [
        new TourModel([city1, city3, city2], 0)
      ]
    );
    let expectedTrial = new TrialModel([gen0, gen1]);

    expect(serializeJSONToTrial(trialJSON)).toEqual(expectedTrial);
  });
});