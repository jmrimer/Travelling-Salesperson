import { MapModel } from '../shared-models/MapModel';
import { TourModel } from '../shared-models/TourModel';
import { EdgeModel } from './EdgeModel';

export class WisdomModel {
  constructor(
    public regions: MapModel[] = [],
    public crowds: any = {},
    public aggregatedTour: TourModel = new TourModel(),
    public agreedEdges: EdgeModel[] = []
  ) {

  }
}