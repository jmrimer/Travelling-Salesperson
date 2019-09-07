import { NodeModel } from './NodeModel';

export class GraphRequestModel {
  constructor(
    public adjacencyMatrix: boolean[][],
    public start: NodeModel
  ){}
}
