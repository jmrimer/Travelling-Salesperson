import * as React from 'react';

interface Props {
  className?: string;
}

class CityModel {
  constructor(
    public name: string = '',
    public latitude: number = 0,
    public longitude: number = 0,
  ) {

  }
}

class RouteModel {
  constructor(
    public route: CityModel[] = [],
    public weight: number = 0
  ) {
  }
}

interface State {
  route: RouteModel;
}

export class HomePage extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      route: new RouteModel()
    }
  }

  componentDidMount(): void {
    fetch('http://localhost:8080/api/map')
      .then(response => response.json())
      .then(data => {
        this.setState({route: data});
      });
  }

  render() {
    return (
      <div className={this.props.className}>
        {`Hello\n${this.state.route.weight}`}
        {`City\n${this.state.route.route}`}
        {
          this.state.route.route
            ?
            this.state.route.route.map((city: CityModel, index: number) => {
              return <div key={index}>{city.name}</div>;
            })
            :
            <div>Loading...</div>
        }
      </div>
    );
  }
}