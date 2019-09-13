import { NodeModel } from '../shared-models/NodeModel';

export class GraphRequestModel {
  constructor(
    public adjacencyMatrix: boolean[][],
    public start: NodeModel,
    public end: NodeModel = new NodeModel(-1)
  ) {

  }
}
