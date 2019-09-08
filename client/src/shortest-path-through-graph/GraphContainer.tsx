import React from 'react';
import classNames from 'classnames';
import InteractiveAdjacencyMatrix from './InteractiveAdjacencyMatrix';
import { connect } from 'react-redux';
import styled from 'styled-components';
import { GraphRequestModel } from './GraphRequestModel';
import { NodeModel } from './NodeModel';
import { fetchShortestPathUsingBFS, toggleMatrix } from '../shortest-path-through-map/actions';

interface Props {
  postBFS: (graphRequest: GraphRequestModel) => void;
  shortestPath: NodeModel[];
  adjacencyMatrix: boolean[][];
  toggleMatrix: (keyValuePair: any) => void;
  className?: string;
}

export class GraphContainer extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('graph-container', this.props.className)}>
        <InteractiveAdjacencyMatrix
          adjacencyMatrix={this.props.adjacencyMatrix}
          toggleMatrix={this.props.toggleMatrix}
        />
        <button
          className={'bfs-button'}
          onClick={() => this.handleClick()}
        >
          Breadth First Search
        </button>
        {this.renderShortestPath()}
      </div>
    )
  }

  private renderShortestPath() {
    return this.props.shortestPath ?
      <div
        className={'route'}
      >
        Shortest path: {this.pathString(this.props.shortestPath)}
      </div>
      : null;
  }

  private pathString(shortestPath: NodeModel[]): string {
    let pathIDs: string[] = [];
    shortestPath.map((node) => {
        pathIDs.push(node.id.toString());
      }
    );
    return pathIDs.join(', ');
  }

  private handleClick() {
    let graphRequest: GraphRequestModel = new GraphRequestModel(
      this.props.adjacencyMatrix,
      new NodeModel(1)
    );
    this.props.postBFS(graphRequest);
  }
}

const mapStateToProps = (state: any) => ({
  shortestPath: state.shortestPath,
  adjacencyMatrix: state.adjacencyMatrix
});

const mapDispatchToProps = {
  postBFS: fetchShortestPathUsingBFS,
  toggleMatrix: toggleMatrix
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(GraphContainer)`
  button {
    hieght: 100px;
    background: pink;
    cursor: pointer;
    
  }
`);