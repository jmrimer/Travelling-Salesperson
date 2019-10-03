import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { StyledMapInput } from '../shared-graphic-components/MapInput';
import { TrialModel } from './TrialModel';
import MultiPathVisualGraph from './MultiPathVisualGraph';
import StyledScatterChart from './StyledScatterChart';
import { Button } from '../website-styling/default';

interface Props {
  trial: TrialModel;
  currentGeneration: number;
  loading: boolean;
  getNewTrial: (mapText: string) => void;
  updateMapText: (e: any) => void;
  mapText: string;
  points: any[];
  nextGeneration: () => void;
  previousGeneration: () => void;
  className?: string;
}

export const TrialDisplayer: React.FC<Props> = props => {
  let {
    currentGeneration,
    loading,
    getNewTrial,
    updateMapText,
    mapText,
    trial,
    points,
    className
  } = props;

  function renderMapInput() {
    return <div className={'input'}>
      <div className={'title'}>INPUT</div>
      <StyledMapInput
        getNewRoute={getNewTrial}
        updateMapText={updateMapText}
        mapText={mapText}
      />
    </div>
  }

  function renderTrialOutput() {
    function filterBestChildRoutes() {
      let generation = trial.generations[currentGeneration];
      if (generation) {
        let children = generation.children;
        children.sort((a, b) => a.weight < b.weight ? -1 : a.weight > b.weight ? 1 : 0);
        children = children.filter((child, index) => {
          return index < 10;
        });
        return children;
      }
      return null;
    }

    return (
      <div className={'output'}>
        {
          loading ?
            <div>Loading...</div> :
            <div/>
        }
        {
          props.trial.generations[0] ?
          <div className={'weight'}>Weight: {props.trial.generations[0].children[0].weight}</div> :
            null
        }
        <div className={'graph-box'}>
          <MultiPathVisualGraph
            className={'children'}
            points={points}
            tours={filterBestChildRoutes()}
          />
        </div>
        <div className={'button-box__generations'}>
        <Button className={'previous-generation'} onClick={() => props.previousGeneration()}>{`< Previous Gen`}</Button>
        <Button className={'next-generation'} onClick={() => props.nextGeneration()}>{`Next Gen >`}</Button>
      </div>
      </div>
    )
  }

  return (
    <div className={classNames('trial-displayer', className)}>
      {renderMapInput()}
      {renderTrialOutput()}
    </div>
  )
};

export default styled(TrialDisplayer)`
  display: flex;
  flex-direction: row;
  justify-content: space-around;

  .output {
    display: flex;
    flex-direction: column;
  }
  
  .graph-box {
    display: flex;
    flex-direction: row;
  }
  
  .weight {
    font-family: Righteous;
    font-weight: 300;
    color: ${(props) => props.theme.color.fontWhite};
    font-size: 32px;
    
  }
  
  .button-box__generations {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    .previous-generation {
      margin-right: 16px;
    }
    .next-generation {
      margin-left: 16px;
    }
  }
`;