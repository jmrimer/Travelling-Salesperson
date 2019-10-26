import { TourModel } from './TourModel';

export class GenerationModel {
  constructor(
    public generation: number,
    public children: TourModel[] = []
  ){}
}