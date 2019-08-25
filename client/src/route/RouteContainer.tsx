import React from 'react';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { connect } from 'react-redux';
import { fetchWeightedRoute } from '../actions/RouteActions';


interface Props {
  weightedRoute: RouteModel,
  loading: boolean,
  getRoute: () => void
}

export class RouteContainer extends React.Component<Props> {
  componentDidMount(): void {
    this.props.getRoute();
  }

  render() {
    return (
      <div className={'routeContainer'}>
        {
          this.props.loading
            ? <div className={'loading'}>Loading route...</div>
            : <RouteInfo weightedRoute={this.props.weightedRoute}/>
        }
      </div>
    );
  }
}

const mapStateToProps = (state: any) => ({
  weightedRoute: state.weightedRoute,
  loading: state.loading
});

const mapDispatchToProps = {
  getRoute: fetchWeightedRoute
};

export default connect(mapStateToProps, mapDispatchToProps)(RouteContainer);