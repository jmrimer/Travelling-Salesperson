import { shallow, ShallowWrapper } from 'enzyme';
import React from 'react';
import InteractiveAdjacencyMatrix from './InteractiveAdjacencyMatrix';
import { GraphContainer } from './GraphContainer';
import { NodeModel } from './NodeModel';

describe('GraphContainer', () => {
  let subject: ShallowWrapper;
  let postBFS = () => {
    return null
  };

  beforeEach(() => {
    subject = shallow(
      <GraphContainer
        adjacencyMatrix={[]}
        postBFS={postBFS}
        shortestPath={[]}
      />);
  });

  it('should render an interactive adjacency matrix', () => {
    expect(subject.find(InteractiveAdjacencyMatrix).exists()).toBeTruthy();
  });

  it('should render a BFS button', () => {
    expect(subject.find('button').exists()).toBeTruthy();
  });

  it('should display the shortest route', () => {
    expect(subject.find('.route').exists()).toBeFalsy();
    subject = shallow(
      <GraphContainer
        postBFS={postBFS}
        adjacencyMatrix={[]}
        shortestPath={[new NodeModel(1), new NodeModel(3), new NodeModel(5), new NodeModel(8), new NodeModel(11)]}
      />);
    expect(subject.find('.route').text()).toBe('Shortest path: 1, 3, 5, 8, 11')
  });
});