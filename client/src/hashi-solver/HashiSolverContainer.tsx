import React from 'react';
import { connect } from 'react-redux';
import { HashiPoint } from './HashiPoint';
import HashiVisualGraph from './HashiVisualGraph';
import { fetchStaticMap } from '../redux/actions/HashiSolverActions';
import { Button } from '../website-styling/default';

interface Props {
  hashiMap: HashiPoint[];
  getStaticMap: () => void;
}

const displayMap = (hashiMap: HashiPoint[]) => {
    console.log(hashiMap);
    let points = hashiMap.map((point) => {
      return {x: point.x * 10, y: point.y * 10, name: point.number}
    });
    return <HashiVisualGraph points={points}/>
  }
;

class HashiSolverContainer extends React.Component<Props> {
  render() {
    return <div>
      <Button
        className={'button--hashi-map'}
        onClick={() => this.props.getStaticMap()}
      >
        New Map
      </Button>
      {displayMap(this.props.hashiMap)}
    </div>;
  }
}

const mapStateToProps = (state: any) => ({
  hashiMap: state.hashiMap,
});

const mapDispatchToProps = {
  getStaticMap: fetchStaticMap
};

export default connect(mapStateToProps, mapDispatchToProps)(HashiSolverContainer);