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
        <div className={'buttons'}>
          <button
            className={'bfs-button'}
            onClick={() => this.handleClick()}
          >
            Breadth First Search
          </button>
          <button
            className={'dfs-button'}
            onClick={() => this.handleClick()}
          >
            Depth First Search
          </button>
        </div>
        {this.renderShortestPaths()}
      </div>
    )
  }

  private renderShortestPaths() {
    const bfsShortestPath = () => {
      return (
        this.props.shortestPath ?
          <div
            className={classNames('shortest-path--bfs', 'shortest-path')}
          >
            Shortest path: {this.pathString(this.props.shortestPath)}
          </div>
          :
          <div
            className={classNames('shortest-path--bfs', 'shortest-path')}
          >
            &nbsp;
          </div>
      )
    };

    const dfsShortestPath = () => {
      return (
        this.props.shortestPath ?
          <div
            className={classNames('shortest-path--dfs', 'shortest-path')}
          >
            Shortest path: {this.pathString(this.props.shortestPath)}
          </div>
          :
          <div
            className={classNames('shortest-path--dfs', 'shortest-path')}
          >
            &nbsp;
          </div>
      )
    };

    return (
      <div className={'paths'}>
        {bfsShortestPath()}
        {dfsShortestPath()}
      </div>
    )
  }

  private pathString(shortestPath: NodeModel[]): string {
    let pathIDs: string[] = [];
    shortestPath.map((node) => {
        return pathIDs.push(node.id.toString());
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
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  height: 576px;
  
  .buttons {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
    margin: 0 16px;
    
    .bfs-button {
      //border-radius: 0 8px 0 0;
    }
    
    .dfs-button {
      border-radius: 0 0 8px 0;
    }
    
    button {
      height: 144px;
      background: ${(props) => props.theme.color.talcum};
      cursor: pointer;
      font-family: Righteous, cursive;
      color: ${(props) => props.theme.color.fontWhite};
      font-size: 36px;
      border: solid 1px ${(props) => props.theme.color.fontWhite};
      
    }
    
  }
  
  .paths {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
  }
  
  .shortest-path {
    font-family: Righteous, cursive;
    font-size: 36px;
    color: ${(props) => props.theme.color.fontWhite};
  }
`);