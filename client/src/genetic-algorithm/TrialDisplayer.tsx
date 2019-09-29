import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { StyledMapInput } from '../shared-graphic-components/MapInput';

interface Props {
  generation: number;
  loading: boolean;
  getNewTrial:
    (
      mapText: string,
      parentInputText: string,
      trialsInputText: string
    ) => void;
  updateMapText: (e: any) => void;
  mapText: string;
  className?: string;
}

export const TrialDisplayer: React.FC<Props> = props => {
  let {
    generation,
    loading,
    getNewTrial,
    updateMapText,
    mapText,
    className
  } = props;

  function renderMapInput() {
    return <div className={'input'}>
      <div className={'title'}>INPUT</div>
      <div className={'input-container--trial'}>
        <div className={'parents'}>
          <div className={'label--parents'}>New Parents per Generation</div>
          <input type={'text'} className={'input--parents'}/>
        </div>
        <div className={'trials'}>
          <div className={'label--trials'}>Number of Trials to Run</div>
          <input type={'text'} className={'input--trials'}/>
        </div>
      </div>
      {/*<StyledMapInput*/}
      {/*  getNewRoute={getNewTrial}*/}
      {/*  updateMapText={updateMapText}*/}
      {/*  mapText={mapText}*/}
      {/*/>*/}
    </div>
  }

  return (
    <div className={classNames('trial-displayer', className)}>
      {renderMapInput()}
    </div>
  )
};

export default (styled(TrialDisplayer)``);