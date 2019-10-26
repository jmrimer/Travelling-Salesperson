import { MapModel } from '../shared-models/MapModel';

export class WisdomRequest {
  constructor(
    public map: MapModel,
    public startingPopulation: string,
    public populationCap: string,
    public totalGenerations: string,
    public maxMutationSize: string,
    public mutationRate: string,
    public regionCount: string,
    public crowdSize: string,
    public agreementThreshold: string
  ) {
  }
}