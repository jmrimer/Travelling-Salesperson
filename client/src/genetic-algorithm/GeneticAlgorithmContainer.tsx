import React from 'react';
import { TourModel } from '../shared-models/TourModel';
import { connect } from 'react-redux';
import classNames from 'classnames';
import {
  fetchTrialFromModel,
  nextGeneration,
  previousGeneration,
  updateCurrentPage,
  updateMapText,
  updateMaxMutationSize,
  updateMutationRate,
  updatePopulationCap,
  updateStartingPopulation,
  updateTotalGenerations
} from '../redux/actions';
import TrialDisplayer from './TrialDisplayer';
import { Page } from '../website-styling/Header';
import { TrialModel } from './TrialModel';
import { TrialRequest } from './TrialRequest';
import { MapModel } from '../shared-models/MapModel';

interface Props {
  weightedRoute: TourModel;
  loading: boolean;
  mapText: string;
  getNewTrial: (mapText: string) => void;
  getTrialFromModel: (trialRequest: TrialRequest) => void;
  updateMapText: (e: any) => void;
  points: any[];
  updateCurrentPage: (page: Page) => void;
  trial: TrialModel;
  currentGeneration: number;
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

export class GeneticAlgorithmContainer extends React.Component<Props> {
  componentDidMount(): void {
    this.props.updateCurrentPage(Page.GENETIC_ALGORITHM);
  }

  render() {
    return (
      <div className={classNames('container--genetic-algorithm', this.props.className)}>
        <TrialDisplayer
          trial={this.props.trial}
          currentGeneration={this.props.currentGeneration}
          loading={this.props.loading}
          getTrial={this.getTrial}
          mapText={this.props.mapText}
          updateMapText={this.props.updateMapText}
          points={this.props.points}
          nextGeneration={this.props.nextGeneration}
          previousGeneration={this.props.previousGeneration}
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
        />
      </div>
    );
  }

  getTrial = () => {
    let map = new MapModel();
    map.serialize(this.props.mapText);

    let trialRequest = new TrialRequest(
      map,
      this.props.startingPopulation,
      this.props.populationCap,
      this.props.totalGenerations,
      this.props.maxMutationSize,
      this.props.mutationRate
    );

    this.props.getTrialFromModel(trialRequest);
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
});

const mapDispatchToProps = {
  updateMapText: updateMapText,
  updateCurrentPage: updateCurrentPage,
  nextGeneration: nextGeneration,
  previousGeneration: previousGeneration,
  updateStartingPopulation: updateStartingPopulation,
  updatePopulationCap: updatePopulationCap,
  updateTotalGenerations: updateTotalGenerations,
  updateMaxMutationSize: updateMaxMutationSize,
  updateMutationRate: updateMutationRate,
  getTrialFromModel: fetchTrialFromModel
};

export default connect(mapStateToProps, mapDispatchToProps)(GeneticAlgorithmContainer);