import React from 'react';
import classNames from 'classnames';
import InteractiveAdjacencyMatrix from './InteractiveAdjacencyMatrix';
import { connect } from 'react-redux';
import styled from 'styled-components';
import { GraphRequestModel } from './GraphRequestModel';
import { NodeModel } from '../shared-models/NodeModel';
import { fetchShortestPathUsingBFS, fetchShortestPathUsingDFS, toggleMatrix } from '../redux/actions';

interface Props {
  postBFS: (graphRequest: GraphRequestModel) => void;
  postDFS: (graphRequest: GraphRequestModel) => void;
  shortestBFSPath: NodeModel[];
  shortestDFSPath: NodeModel[];
  adjacencyMatrix: boolean[][];
  toggleMatrix: (keyValuePair: any) => void;
  className?: string;
}

export class GraphContainer extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('graph-container', this.props.className)}>
        {this.renderMatrix()}
        {this.renderButtons()}
        {this.renderShortestPaths()}
      </div>
    )
  }

  private renderMatrix() {
    return <InteractiveAdjacencyMatrix
      adjacencyMatrix={this.props.adjacencyMatrix}
      toggleMatrix={this.props.toggleMatrix}
    />;
  }

  private renderButtons() {
    return <div className={'buttons'}>
      <button
        className={'bfs-button'}
        onClick={() => this.handleBFSClick()}
      >
        Breadth First Search
      </button>
      <button
        className={'dfs-button'}
        onClick={() => this.handleDFSClick()}
      >
        Depth First Search
      </button>
    </div>;
  }

  private renderShortestPaths() {
    const bfsShortestPath = () => {
      return (
        this.props.shortestBFSPath ?
          <div
            className={classNames('shortest-path--bfs', 'shortest-path')}
          >
            <span>Shortest path:</span>
            <span>{this.pathString(this.props.shortestBFSPath)}</span>
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
        this.props.shortestDFSPath ?
          <div
            className={classNames('shortest-path--dfs', 'shortest-path')}
          >
            <span>Shortest path:</span>
            <span>{this.pathString(this.props.shortestDFSPath)}</span>
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

  private handleBFSClick() {
    let graphRequest: GraphRequestModel = new GraphRequestModel(
      this.props.adjacencyMatrix,
      new NodeModel(1),
      new NodeModel(11)
    );
    this.props.postBFS(graphRequest);
  }

  private handleDFSClick() {
    let graphRequest: GraphRequestModel = new GraphRequestModel(
      this.props.adjacencyMatrix,
      new NodeModel(1),
      new NodeModel(11)
    );
    this.props.postDFS(graphRequest);
  }
}

const mapStateToProps = (state: any) => ({
  shortestBFSPath: state.shortestBFSPath,
  shortestDFSPath: state.shortestDFSPath,
  adjacencyMatrix: state.adjacencyMatrix
});

const mapDispatchToProps = {
  postBFS: fetchShortestPathUsingBFS,
  postDFS: fetchShortestPathUsingDFS,
  toggleMatrix: toggleMatrix
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(GraphContainer)`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin-top: 24px;
  
  .buttons {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 0 16px;
    height: 694px;
    padding-top: 119px;
    
    .bfs-button {
      border-radius: 0 8px 0 0;
    }
    
    .dfs-button {
      border-radius: 0 0 8px 0;
    }
    
    button {
      height: 144px;
      background: ${(props) => props.theme.color.wedgewood};
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
    height: 694px;
    padding-top: 119px;
  }
  
  .shortest-path {
    font-family: Righteous, cursive;
    font-size: 36px;
    color: ${(props) => props.theme.color.fontWhite};
    min-width: 264px;
    display: flex;
    flex-direction: column;
  }
`);