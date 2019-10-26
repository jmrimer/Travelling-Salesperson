import { TourModel } from '../shared-models/TourModel';
import { EdgeModel } from './EdgeModel';

export class WisdomModel {
  constructor(
    public crowds: any = {},
    public aggregatedTour: TourModel = new TourModel(),
    public agreedEdges: EdgeModel[] = []
  ) {

  }
}