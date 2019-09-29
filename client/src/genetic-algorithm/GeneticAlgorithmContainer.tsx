import React from 'react';
import { TourModel } from '../shared-models/TourModel';
import { connect } from 'react-redux';
import classNames from 'classnames';
import { fetchNewTrial, updateCurrentPage, updateMapText } from '../redux/actions';
import { TrialDisplayer } from './TrialDisplayer';
import { Page } from '../website-styling/Header';

interface Props {
  weightedRoute: TourModel;
  loading: boolean;
  mapText: string;
  getNewTrial: (mapText: string, parentInputText: string, trialsInputText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  updateCurrentPage: (page: Page) => void;
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
          generation={0}
          loading={false}
          getNewTrial={this.props.getNewTrial}
          mapText={'hello'}
          updateMapText={this.props.updateMapText}
        />
      </div>
    );
  }
}

const mapStateToProps = (state: any) => ({
  loading: state.loading,
  mapText: state.mapText,
  points: state.points
});

const mapDispatchToProps = {
  getNewTrial: fetchNewTrial,
  updateMapText: updateMapText,
  updateCurrentPage: updateCurrentPage
};

export default connect(mapStateToProps, mapDispatchToProps)(GeneticAlgorithmContainer);