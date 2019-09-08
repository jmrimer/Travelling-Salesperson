import React from 'react';
import classNames from 'classnames';
import InteractiveCell from './InteractiveCell';
import styled from 'styled-components';

type Props = {
  adjacencyMatrix: boolean[][];
  toggleMatrix: (keyValuePair: any) => void;
  className?: string;
}


export const InteractiveAdjacencyMatrix: React.FC<Props> = props => {
  const createMatrix = (matrix: boolean[][]) => {
    let gridSize = matrix.length;
    let table = [];
    for (let row = 0; row < gridSize; row++) {
      let cells = [];
      for (let column = 0; column < gridSize; column++) {
        cells.push(
          <InteractiveCell
            keyValuePair={{startId: row, endId: column}}
            connected={matrix[row][column]}
            callback={props.toggleMatrix}
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

  return (
    <div className={classNames('interactive-adjacency-matrix', props.className)}>
      {createMatrix(props.adjacencyMatrix)}
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