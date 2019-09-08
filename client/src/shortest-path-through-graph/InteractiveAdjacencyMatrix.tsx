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

    function createHeaderOrCell(row: number, column: number) {
      if (row === -1 && column === -1) {
        return (
          <div
            className={classNames('empty--header', 'table-header')}
            key={`${row + 1}-${column + 1}`}
          >
            &nbsp;
          </div>
        );
      }

      if (row === -1) {
        return (
          <div
            className={classNames('column--header', 'table-header')}
            key={`${row + 1}-${column + 1}`}
          >
            {column + 1}
          </div>
        )
      }

      if (column === -1) {
        return (
          <div
            className={classNames('row--header', 'table-header')}
            key={`${row + 1}-${column + 1}`}
          >
            {row + 1}
          </div>
        )
      }

      return <InteractiveCell
        keyValuePair={{startId: row, endId: column}}
        connected={matrix[row][column]}
        callback={props.toggleMatrix}
        key={`${row + 1}-${column + 1}`}
      />;
    }

    for (let row = -1; row < gridSize; row++) {
      let cells = [];
      for (let column = -1; column < gridSize; column++) {
        cells.push(
          createHeaderOrCell(row, column)
        );
      }
      table.push(
        <div
          className={classNames('row', `row--${row + 1}`)}
          key={row}>{cells}
        </div>
      );
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
  background: ${(props) => props.theme.color.lavender};
  border-radius: 8px;
  
  .row {
    display: flex;
    flex-direction: row;
  }
  
  .empty--header {
    border-radius: 8px 0 0 0;
  }
    
  .table-header {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 48px;
    width: 48px;
    color: ${(props) => props.theme.color.fontWhite}
    font-size: 36px;
    background: ${(props) => props.theme.color.lavender};
    border: solid 1px ${(props) => props.theme.color.fontWhite};
  }
`;