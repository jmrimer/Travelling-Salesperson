import React from 'react';
import { TourModel } from '../shared-models/TourModel';
import { connect } from 'react-redux';
import classNames from 'classnames';
import TourDisplayer from '../shared-graphic-components/TourDisplayer';
import { fetchNewRouteFromText, updateMapText } from '../redux/actions';

interface Props {
  weightedRoute: TourModel;
  loading: boolean;
  mapText: string;
  getNewRoute: (mapText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  className?: string;
}

export class BruteForceContainer extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('container--brute-force', this.props.className)}>
        <TourDisplayer
          weightedRoute={this.props.weightedRoute}
          loading={this.props.loading}
          mapText={this.props.mapText}
          getNewRoute={this.getNewRoute}
          updateMapText={this.props.updateMapText}
          points={this.props.points}
        />
      </div>
    );
  }

  getNewRoute = () => {
    this.props.getNewRoute(this.props.mapText);
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading,
  mapText: state.mapText,
  points: state.points
});

const mapDispatchToProps = {
  getNewRoute: fetchNewRouteFromText,
  updateMapText: updateMapText
};

export default connect(mapStateToProps, mapDispatchToProps)(BruteForceContainer);