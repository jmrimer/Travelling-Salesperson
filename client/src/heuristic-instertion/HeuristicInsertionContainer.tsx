import React from 'react';
import { RouteModel } from '../brute-force/models/RouteModel';
import classNames from 'classnames';
import TourDisplayer from '../shared-graphic-components/TourDisplayer';
import { fetchNewRouteFromText, updateMapText } from '../brute-force/actions';
import { connect } from 'react-redux';

interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  mapText: string;
  getNewRoute: (mapText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  className?: string;
}

export class HeuristicInsertionContainer extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('container--heuristic-insertion', this.props.className)}>
        <TourDisplayer
          weightedRoute={this.props.weightedRoute}
          loading={this.props.loading}
          mapText={this.props.mapText}
          getNewRoute={this.props.getNewRoute}
          updateMapText={this.props.updateMapText}
          points={this.props.points}
        />
      </div>
    );
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

export default connect(mapStateToProps, mapDispatchToProps)(HeuristicInsertionContainer);