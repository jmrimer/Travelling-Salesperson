import { shallow, ShallowWrapper } from 'enzyme';
import React from 'react';
import InteractiveAdjacencyMatrix from './InteractiveAdjacencyMatrix';
import { GraphContainer } from './GraphContainer';

describe('GraphContainer', () => {
  let subject: ShallowWrapper;
  let postBFS = () => {return null};

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

  // it('should display the route when breadth first search is clicked', () => {
  //   expect(subject.find('button').prop('onClick')).toBe(postBFS);
  // });

  it('should display the shortest route', () => {
    subject = shallow(
      <GraphContainer
        postBFS={postBFS}
        adjacencyMatrix={[]}
        shortestPath={[1, 3, 5, 8, 11]}
      />);
    expect(subject.find('.route').text()).toBe('Shortest path: 1, 3, 5, 8, 11')
  });
});