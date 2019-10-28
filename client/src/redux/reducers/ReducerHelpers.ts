import {
  flipVerticallyAroundCenterOf,
  rotate180AroundCenterOf
} from '../../shared-graphic-components/visual-grapher/GraphTranslator';
import { TourModel } from '../../shared-models/TourModel';
import { NodeModel } from '../../shared-models/NodeModel';
import { TrialModel } from '../../genetic-algorithm/TrialModel';
import { GenerationModel } from '../../shared-models/GenerationModel';
import { WisdomModel } from '../../crowd-wisdom/WisdomModel';

export function createInitialMatrix() {
  return [
    [false, true, true, true, false, false, false, false, false, false, false],
    [false, false, true, false, false, false, false, false, false, false, false],
    [false, false, false, true, true, false, false, false, false, false, false],
    [false, false, false, false, true, true, true, false, false, false, false],
    [false, false, false, false, false, false, true, true, false, false, false],
    [false, false, false, false, false, false, false, true, false, false, false],
    [false, false, false, false, false, false, false, false, true, true, false],
    [false, false, false, false, false, false, false, false, true, true, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, false],
  ]
}

export let startingMap =
  '1 87.951292 2.658162\n' +
  '2 33.466597 66.682943\n' +
  '3 91.778314 53.807184\n' +
  '4 20.526749 47.633290';

export function translateCoordinateTextToGraphReadyPoints(mapText: string) {
  let lines = mapText.split('\n');
  let validCityAndCoordinates = lines.filter((line: string) => {
    return line.split(' ').length === 3;
  });
  let points = validCityAndCoordinates.map((textColumn: string) => {
    let cityAndCoordinates = textColumn.split(' ');
    return {
      name: cityAndCoordinates[0],
      x: parseFloat(cityAndCoordinates[1]),
      y: parseFloat(cityAndCoordinates[2])

    };
  });

  return flipVerticallyAroundCenterOf(
    rotate180AroundCenterOf(points)
  );
}

export function textFromBody(event: any) {
  return event.target.value;
}

export function serializeJSONtoTour(json: any) {
  return new TourModel(json.cycle, json.weight);
}

export function serializeJSONToPath(body: any) {
  let path: NodeModel[];
  path = [];
  body.map((node: NodeModel) => {
    return path.push(node);
  });
  return path;
}

export function toggleMatrix(matrix: any, keyValuePair: any) {
  let newMatrix = Object.assign([], matrix);
  let start = keyValuePair.startId;
  let end = keyValuePair.endId;
  newMatrix[start][end] = !newMatrix[start][end];
  return newMatrix;
}

export const serializeJSONToTrial = (body: any): TrialModel => {
  let trial = new TrialModel();
  body.generations.map((generationJSON: any) => {
    let children: TourModel[] = [];
    generationJSON.population.map((childJSON: any) => {
      return children.push(serializeJSONtoTour(childJSON));
    });

    return trial.generations.push(
      new GenerationModel(
        generationJSON.generation,
        children
      )
    )
  });

  return trial;
};

export const serializeJSONToWisdom = (body: any): WisdomModel => {
  console.log(body);
  let wisdom = new WisdomModel();
  wisdom.agreedEdges = body.agreedEdges;
  return wisdom;
};