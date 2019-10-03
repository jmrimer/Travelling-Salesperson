import { TourModel } from '../shared-models/TourModel';

export class GenerationModel {
  constructor(
    public generation: number,
    public children: TourModel[] = []
  ){}
}