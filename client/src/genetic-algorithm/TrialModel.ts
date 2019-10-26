import { GenerationModel } from '../shared-models/GenerationModel';

export class TrialModel {
  constructor(
    public generations: GenerationModel[] = []
  ) {}
}