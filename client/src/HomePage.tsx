import React from 'react';
import { CityModel } from './models/CityModel';
import { connect } from 'react-redux';
import { RouteModel } from './models/RouteModel';
import { setRoute } from './actions';

interface Props {
  route: RouteModel,
  setRoute: (data: any) => void
}

export class HomePage extends React.Component<Props> {
  componentDidMount(): void {
    fetch('http://localhost:8080/api/map')
      .then(response => response.json())
      .then(data => {
        this.props.setRoute(data);
      });
  }

  render() {
    const {route} = this.props;

    return (
      <div className={'route'}>
        <span>Weight: {this.props.route.weight}</span>
        {this.displayRoute(this.props.route)}
      </div>
    );
  }

  private displayRoute(route: RouteModel) {
    if (route) {
      return (
        <div>
          Route:
          {
            route.route.map((city: CityModel, index: number) => {
              return <div key={index}>{city.name}</div>;
            })
          }
        </div>
      );
    }
    return <div>Loading...</div>;
  }
}

const mapStateToProps = (state: any) => {
  return {
    route: state.route
  }
};

const mapDispatchToProps = (dispatch: any) => {
  return {
    setRoute: (route: RouteModel) => {
      dispatch(setRoute(route))
    }
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);