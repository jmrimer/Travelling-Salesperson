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
  return (
    <div className={classNames('interactive-adjacency-matrix', props.className)}>
      {instructions()}
      {createMatrix(props.adjacencyMatrix, props.toggleMatrix)}
    </div>
  );
};

function instructions() {
  return (
    <div className={'matrix-instructions'}>
      <span>Click each cell to create paths in an 11-node graph.</span>
      <span>Rows are Start nodes & columns are End nodes.</span>
      <span>(e.g. [5, 8] goes from 5 to 8)</span>
    </div>
  )
}

const createMatrix = (matrix: boolean[][], toggleMatrix: (keyValuePair: any) => void) => {
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
      callback={toggleMatrix}
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
  return (
    <div className={'matrix-table'}>
      {table}
    </div>
  );
};

export default styled(InteractiveAdjacencyMatrix)`
  display: flex;
  flex-direction: column;
  border-radius: 8px;
  
  .matrix-instructions {
    display: flex;
    flex-direction: column;
    background: none ;
    font-size: 24px;
    font-family: Righteous, cursive;
    color: ${(props) => props.theme.color.fontWhite};
    margin-bottom: 16px;
    
    > span {
      text-align: center;
      margin-bottom: 4px;
    }
  }
  
  .matrix-table {
    background: ${(props) => props.theme.color.lavender};
  }
  
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