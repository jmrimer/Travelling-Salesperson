import {
  flipVerticallyAroundCenterOf,
  rotate180AroundCenterOf
} from '../../shared-graphic-components/visual-grapher/GraphTranslator';
import { TourModel } from '../../shared-models/TourModel';
import { NodeModel } from '../../shared-models/NodeModel';
import { TrialModel } from '../../genetic-algorithm/TrialModel';
import { GenerationModel } from '../../shared-models/GenerationModel';
import { WisdomModel } from '../../crowd-wisdom/WisdomModel';
import { HashiPoint } from '../../hashi-solver/HashiPoint';

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

export let startingMap4 =
  '1 87.951292 2.658162\n' +
  '2 33.466597 66.682943\n' +
  '3 91.778314 53.807184\n' +
  '4 20.526749 47.633290';
export let startingMap100 =
  '1 87.951292 2.658162\n' +
  '2 33.466597 66.682943\n' +
  '3 91.778314 53.807184\n' +
  '4 20.526749 47.633290\n' +
  '5 9.006012 81.185339\n' +
  '6 20.032350 2.761925\n' +
  '7 77.181310 31.922361\n' +
  '8 41.059603 32.578509\n' +
  '9 18.692587 97.015290\n' +
  '10 51.658681 33.808405\n' +
  '11 44.563128 47.541734\n' +
  '12 37.806330 50.599689\n' +
  '13 9.961241 20.337535\n' +
  '14 28.186895 70.415357\n' +
  '15 62.129582 6.183050\n' +
  '16 50.376904 42.796106\n' +
  '17 71.285134 43.671987\n' +
  '18 34.156316 49.113437\n' +
  '19 85.201575 71.837519\n' +
  '20 27.466659 1.394696\n' +
  '21 97.985778 44.746239\n' +
  '22 40.730003 98.400830\n' +
  '23 73.799860 61.076693\n' +
  '24 85.076449 17.029328\n' +
  '25 16.052736 11.899167\n' +
  '26 20.160527 67.238380\n' +
  '27 22.730186 99.725333\n' +
  '28 77.196570 88.503677\n' +
  '29 18.494217 31.971191\n' +
  '30 72.743919 16.071047\n' +
  '31 4.153569 41.981262\n' +
  '32 79.027680 95.034639\n' +
  '33 14.145329 40.690329\n' +
  '34 66.258736 70.360424\n' +
  '35 22.656941 52.076785\n' +
  '36 82.680746 31.058687\n' +
  '37 88.995025 35.560167\n' +
  '38 87.939085 36.567278\n' +
  '39 82.845546 48.393200\n' +
  '40 5.371258 3.466903\n' +
  '41 80.028687 51.258889\n' +
  '42 8.908353 80.703146\n' +
  '43 69.411298 10.122990\n' +
  '44 10.129093 91.378521\n' +
  '45 61.546678 97.531053\n' +
  '46 61.156041 69.313639\n' +
  '47 39.719840 46.403394\n' +
  '48 38.999603 68.407239\n' +
  '49 43.992431 59.556871\n' +
  '50 26.963103 73.021638\n' +
  '51 28.879666 27.948851\n' +
  '52 58.751183 87.429426\n' +
  '53 85.290078 60.875271\n' +
  '54 40.879543 32.523576\n' +
  '55 67.326884 81.203650\n' +
  '56 19.064913 27.845088\n' +
  '57 14.648885 88.753929\n' +
  '58 4.153569 87.118137\n' +
  '59 10.895108 44.978179\n' +
  '60 23.258156 5.346843\n' +
  '61 68.926054 82.073428\n' +
  '62 11.713004 65.706351\n' +
  '63 83.404035 89.590136\n' +
  '64 11.471908 44.187750\n' +
  '65 41.422773 81.743828\n' +
  '66 91.595202 40.324107\n' +
  '67 31.730094 98.501541\n' +
  '68 56.382946 11.935789\n' +
  '69 43.232521 43.571276\n' +
  '70 56.904813 42.152165\n' +
  '71 93.386639 12.457656\n' +
  '72 71.395001 16.754662\n' +
  '73 77.065340 13.657033\n' +
  '74 70.278024 40.021973\n' +
  '75 76.604511 36.146123\n' +
  '76 31.351665 67.159032\n' +
  '77 23.563341 66.295358\n' +
  '78 20.822779 81.447798\n' +
  '79 52.903836 7.309183\n' +
  '80 5.746635 94.280831\n' +
  '81 40.147099 4.345836\n' +
  '82 13.583789 96.127201\n' +
  '83 62.181463 28.403577\n' +
  '84 4.409925 36.637471\n' +
  '85 72.331919 22.144230\n' +
  '86 71.483505 61.964782\n' +
  '87 30.283517 60.740989\n' +
  '88 35.721915 87.408063\n' +
  '89 77.556688 30.884732\n' +
  '90 49.781793 33.585620\n' +
  '91 99.078341 81.115146\n' +
  '92 77.309488 4.168828\n' +
  '93 61.522263 46.504105\n' +
  '94 63.026826 33.359783\n' +
  '95 69.045076 0.952177\n' +
  '96 59.254738 81.203650\n' +
  '97 27.005829 40.083010\n' +
  '98 24.509415 4.898221\n' +
  '99 54.347362 47.959838\n' +
  '100 59.797967 84.215827';

export const staticHashiMap = () => {
  let map: HashiPoint[] = [];
  // for (let x = 0; x < 8; x++) {
  //   for (let y = 0; y < 8; y++) {
  //     map.push(Hashinew Point(x,y), 1));
  //   }
  // }

  map.push(new HashiPoint(0, 0, 3));
  map.push(new HashiPoint(0, 3, 2));
  map.push(new HashiPoint(1, 1, 3));
  map.push(new HashiPoint(1, 6, 3));
  map.push(new HashiPoint(2, 0, 2));
  map.push(new HashiPoint(3, 2, 1));
  return map;
};

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
  let wisdom = new WisdomModel();
  wisdom.agreedEdges = body.agreedEdges;
  wisdom.aggregatedTour = body.aggregatedTour;
  return wisdom;
};

export const serializeJSONToHashiMap = (body: any): HashiPoint[] => {
  let hashiPoints: HashiPoint[] = [];
  body.islands.map((island: any) => {
    hashiPoints.push(new HashiPoint(
      island.coordinates.x,
      island.coordinates.y,
      island.population
    ))
  });
  return hashiPoints;
};