import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { StyledMapInput } from '../shared-graphic-components/MapInput';
import { WisdomModel } from './WisdomModel';
import { Heading } from '../website-styling/default';
import StyledTextInput from '../website-styling/StyledTextInput';
import EdgesVisualGraph from '../shared-graphic-components/visual-grapher/EdgesVisualGraph';

interface Props {
  wisdom: WisdomModel;
  loading: boolean;
  getWisdom: () => void;
  updateMapText: (e: any) => void;
  mapText: string;
  points: any[];
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

export const WisdomDisplayer: React.FC<Props> = props => {
  let {
    loading,
    updateMapText,
    mapText,
    wisdom,
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
        getNewRoute={props.getWisdom}
        updateMapText={updateMapText}
        mapText={mapText}
      />
    </div>
  }

  function renderWisdomOutput() {
    return (
      <div className={'output'}>
        {
          loading ?
            <div>Loading...</div> :
            <div/>
        }
        <div className={'graph-box'}>
          <EdgesVisualGraph points={points} edges={wisdom.agreedEdges}/>
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
      {renderWisdomOutput()}
    </div>
  )
};

export default styled(WisdomDisplayer)`
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
