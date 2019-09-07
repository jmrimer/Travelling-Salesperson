import { shallow, ShallowWrapper } from 'enzyme';
import InteractiveAdjacencyMatrix from './InteractiveAdjacencyMatrix';
import React from 'react';
import InteractiveCell from './InteractiveCell';

describe('InteractiveAdjacencyMatrix', () => {
  let subject: ShallowWrapper;

  beforeEach(() => {
    subject = shallow(
      <InteractiveAdjacencyMatrix
        graphSize={11}
      />
    )
  });
  it('should render an interactive grid with enough cells for each pair of the graph size', () => {
    expect(subject.find(InteractiveCell).length).toBe(144);
  });
});

export default function () {
};