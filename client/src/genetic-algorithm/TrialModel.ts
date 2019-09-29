import { GenerationModel } from './GenerationModel';

export class TrialModel {
  constructor(
    public generations: GenerationModel[] = []
  ) {}
}