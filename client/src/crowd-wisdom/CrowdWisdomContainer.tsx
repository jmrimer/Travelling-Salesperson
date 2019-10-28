import React from 'react';
import { connect } from 'react-redux';
import classNames from 'classnames';
import {
  updateCurrentPage,
  updateMapText,
  updateMaxMutationSize,
  updateMutationRate,
  updatePopulationCap,
  updateStartingPopulation,
  updateTotalGenerations
} from '../redux/actions';
import { Page } from '../website-styling/Header';
import { WisdomModel } from './WisdomModel';
import { WisdomRequest } from './WisdomRequest';
import { MapModel } from '../shared-models/MapModel';
import WisdomDisplayer from './WisdomDisplayer';
import { fetchWisdomFromModel } from '../redux/actions/CrowdWisdomActions';

interface Props {
  loading: boolean;
  mapText: string;
  getWisdomFromModel: (trialRequest: WisdomRequest) => void;
  updateMapText: (e: any) => void;
  points: any[];
  updateCurrentPage: (page: Page) => void;
  wisdom: WisdomModel;
  currentGeneration: number;
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
  regionCount: string;
  crowdSize: string;
  agreementThreshold: string;
  className?: string;
}

export class CrowdWisdomContainer extends React.Component<Props> {
  componentDidMount(): void {
    this.props.updateCurrentPage(Page.CROWD_WISDOM);
  }

  render() {
    return (
      <div className={classNames('container--genetic-algorithm', this.props.className)}>
        <WisdomDisplayer
          loading={this.props.loading}
          mapText={this.props.mapText}
          updateMapText={this.props.updateMapText}
          points={this.props.points}
          updateStartingPopulation={this.props.updateStartingPopulation}
          updatePopulationCap={this.props.updatePopulationCap}
          updateTotalGenerations={this.props.updateTotalGenerations}
          updateMaxMutationSize={this.props.updateMaxMutationSize}
          updateMutationRate={this.props.updateMutationRate}
          startingPopulation={this.props.startingPopulation}
          populationCap={this.props.populationCap}
          totalGenerations={this.props.totalGenerations}
          maxMutationSize={this.props.maxMutationSize}
          mutationRate={this.props.mutationRate}
          getWisdom={this.getWisdom} wisdom={this.props.wisdom}/>
      </div>
    );
  }

  getWisdom = () => {
    let map = new MapModel();
    map.serialize(this.props.mapText);

    let wisdomRequest = new WisdomRequest(
      map,
      this.props.startingPopulation,
      this.props.populationCap,
      this.props.totalGenerations,
      this.props.maxMutationSize,
      this.props.mutationRate,
      this.props.regionCount,
      this.props.crowdSize,
      this.props.agreementThreshold,
    );

    this.props.getWisdomFromModel(wisdomRequest);
  };
}

const mapStateToProps = (state: any) => ({
  loading: state.loading,
  mapText: state.mapText,
  points: state.points,
  trial: state.trial,
  startingPopulation: state.startingPopulation,
  populationCap: state.populationCap,
  totalGenerations: state.totalGenerations,
  maxMutationSize: state.maxMutationSize,
  mutationRate: state.mutationRate,
  currentGeneration: state.currentGeneration,
  regionCount: state.regionCount,
  crowdSize: state.crowdSize,
  agreementThreshold: state.agreementThreshold,
  wisdom: state.wisdom
});

const mapDispatchToProps = {
  updateMapText: updateMapText,
  updateCurrentPage: updateCurrentPage,
  updateStartingPopulation: updateStartingPopulation,
  updatePopulationCap: updatePopulationCap,
  updateTotalGenerations: updateTotalGenerations,
  updateMaxMutationSize: updateMaxMutationSize,
  updateMutationRate: updateMutationRate,
  getWisdomFromModel: fetchWisdomFromModel
};

export default connect(mapStateToProps, mapDispatchToProps)(CrowdWisdomContainer);