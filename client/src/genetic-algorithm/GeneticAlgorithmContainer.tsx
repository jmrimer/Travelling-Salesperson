import React from 'react';
import { TourModel } from '../shared-models/TourModel';
import { connect } from 'react-redux';
import classNames from 'classnames';
import { fetchNewTrial, nextGeneration, updateCurrentPage, updateMapText } from '../redux/actions';
import  TrialDisplayer from './TrialDisplayer';
import { Page } from '../website-styling/Header';
import { TrialModel } from './TrialModel';

interface Props {
  weightedRoute: TourModel;
  loading: boolean;
  mapText: string;
  getNewTrial: (mapText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  updateCurrentPage: (page: Page) => void;
  trial: TrialModel;
  currentGeneration: number;
  nextGeneration: () => void;
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
          getNewTrial={this.props.getNewTrial}
          mapText={this.props.mapText}
          updateMapText={this.props.updateMapText}
          points={this.props.points}
          nextGeneration={this.props.nextGeneration}
        />
      </div>
    );
  }
}

const mapStateToProps = (state: any) => ({
  loading: state.loading,
  mapText: state.mapText,
  points: state.points,
  trial: state.trial,
  currentGeneration: state.currentGeneration,
});

const mapDispatchToProps = {
  getNewTrial: fetchNewTrial,
  updateMapText: updateMapText,
  updateCurrentPage: updateCurrentPage,
  nextGeneration: nextGeneration,
};

export default connect(mapStateToProps, mapDispatchToProps)(GeneticAlgorithmContainer);