import { shallow, ShallowWrapper } from 'enzyme';
import { InteractiveAdjacencyMatrix } from './InteractiveAdjacencyMatrix';
import React from 'react';
import InteractiveCell from './InteractiveCell';

describe('InteractiveAdjacencyMatrix', () => {
  let subject: ShallowWrapper;
  let adjacencyMatrix = [
    [false, true, true, true, false, false, false, false, false, false, false],
    [false, false, true, false, false, false, false, false, false, false, false],
    [false, false, false, true, true, false, false, false, false, false, false],
    [false, false, false, false, true, true, true, false, false, false, false],
    [false, false, false, false, false, false, true, true, false, false, false],
    [false, false, false, false, false, false, false, true, false, false, false],
    [false, false, false, false, false, false, false, false, true, true, false],
    [false, false, false, false, false, false, false, false, true, true, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, true],
    [false, false, false, false, false, false, false, false, false, false, false]
  ];

  beforeEach(() => {
    subject = shallow(
      <InteractiveAdjacencyMatrix
        adjacencyMatrix={adjacencyMatrix}
      />
    )
  });

  it('should render an interactive grid with enough cells for each pair of the graph size', () => {
    expect(subject.find(InteractiveCell).length).toBe(121);
  });
});

export default function () {
};