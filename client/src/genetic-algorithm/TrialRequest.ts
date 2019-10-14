import { MapModel } from '../shared-models/MapModel';

export class TrialRequest {
  constructor(
    public map: MapModel,
    public startingPopulation: string,
    public populationCap: string,
    public totalGenerations: string,
    public maxMutationSize: string,
    public mutationRate: string
  ) {
  }
}