import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { StyledMapInput } from '../shared-graphic-components/MapInput';
import { TrialModel } from './TrialModel';
import MultiPathVisualGraph from './MultiPathVisualGraph';
import { Button, Heading } from '../website-styling/default';
import StyledTextInput from '../website-styling/StyledTextInput';

interface Props {
  trial: TrialModel;
  currentGeneration: number;
  loading: boolean;
  getTrial: () => void;
  updateMapText: (e: any) => void;
  mapText: string;
  points: any[];
  nextGeneration: () => void;
  previousGeneration: () => void;
  updateStartingPopulation: (e: any) => void;
  updatePopulationCap: (e: any) => void;
  updateTotalGenerations: (e: any) => void;
  updateMaxMutationSize: (e: any) => void;
  updateMutationRate: (e: any) => void;
  startingPopulation: string;
  populationCap: string;
  totalGenerations: string;
  maxMutationSize: string;
  mutationRate: string;
  className?: string;
}

export const TrialDisplayer: React.FC<Props> = props => {
  let {
    currentGeneration,
    loading,
    updateMapText,
    mapText,
    trial,
    points,
    className
  } = props;

  function renderMapInput() {
    return <div className={'input'}>
      <Heading>INPUT</Heading>
      <div className={'trial-config'}>
        <div className={'row'}>
          <StyledTextInput
            label={'Starting population'}
            callback={props.updateStartingPopulation}
            value={props.startingPopulation}
          />
          <StyledTextInput
            label={'Population cap'}
            callback={props.updatePopulationCap}
            value={props.populationCap}
          />
        </div>
        <div className={'row'}>
          <StyledTextInput
            label={'Total generations'}
            callback={props.updateTotalGenerations}
            value={props.totalGenerations}
          />
          <StyledTextInput
            label={'Max mutation size'}
            callback={props.updateMaxMutationSize}
            value={props.maxMutationSize}
          />
        </div>
        <div className={'row'}>
          <StyledTextInput
            label={'Mutation rate'}
            callback={props.updateMutationRate}
            value={props.mutationRate}
          />
        </div>
      </div>
      <StyledMapInput
        getNewRoute={props.getTrial}
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
        <div className={'generation-info'}>
          {
            props.trial.generations[currentGeneration] ?
              <span className={'generation'}>Generation: {currentGeneration}</span> :
              null
          }
          {
            props.trial.generations[currentGeneration] ?
              <span
                className={'weight'}>Weight: {props.trial.generations[currentGeneration].children[0].weight.toFixed(0)}</span> :
              null
          }
        </div>
        <div className={'graph-box'}>
          <MultiPathVisualGraph
            className={'children'}
            points={points}
            tours={filterBestChildRoutes()}
          />
        </div>
        <div className={'button-box__generations'}>
          <Button className={'previous-generation'}
                  onClick={() => props.previousGeneration()}>{`< Previous Gen`}</Button>
          <Button className={'next-generation'} onClick={() => props.nextGeneration()}>{`Next Gen >`}</Button>
        </div>
      </div>
    )
  }

  function renderDividingLine() {
    return <div className={'divide'}>&nbsp;</div>
  }

  return (
    <div className={classNames('trial-displayer', className)}>
      {renderMapInput()}
      {renderDividingLine()}
      {renderTrialOutput()}
    </div>
  )
};

export default styled(TrialDisplayer)`
  display: flex;
  flex-direction: row;
  justify-content: space-around;

  .divide {
    width: 2px;
    border: 2px solid ${(props) => props.theme.color.fontWhite};
    margin: 8px 16px;
  } 
   
  .row {
    display: flex;
    flex-direction: row;
    
    .input--styled-text {
      margin-left: auto;
    }
 }

  .output {
    display: flex;
    flex-direction: column;
  }
  
  .graph-box {
    display: flex;
    flex-direction: row;
  }
  
  .weight, .generation {
    font-family: Righteous, cursive;
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
  
  .generation-info {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
  }
`;
