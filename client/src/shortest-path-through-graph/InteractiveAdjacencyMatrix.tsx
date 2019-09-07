import React from 'react';
import classNames from 'classnames';
import InteractiveCell from './InteractiveCell';
import styled from 'styled-components';

type Props = {
  graphSize: number;
  className?: string;
}
const createMatrix = (graphSize: number) => {
  let gridSize = graphSize + 1;
  let table = [];
  for (let row = 0; row < gridSize; row++) {
    let cells = [];
    for (let column = 0; column < gridSize; column++) {
      cells.push(
        <InteractiveCell
          key={`${row + 1}-${column + 1}`}
        />
      );
    }
    table.push(<div
      className={classNames('row', `row--${row + 1}`)}
      key={row}>{cells}
    </div>);
  }
  return table;
};

const InteractiveAdjacencyMatrix: React.FC<Props> = props => {


  return (
    <div className={classNames('interactive-adjacency-matrix', props.className)}>
      {createMatrix(props.graphSize)}
    </div>
  );
};

export default styled(InteractiveAdjacencyMatrix)`
  display: flex;
  flex-direction: column;
  
  .row {
    display: flex;
    flex-direction: row;
  }
`;