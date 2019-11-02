import React from 'react';
import { connect } from 'react-redux';
import { HashiPoint } from './HashiPoint';
import HashiVisualGraph from './HashiVisualGraph';

interface Props {
  hashiMap: HashiPoint[];
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
      {displayMap(this.props.hashiMap)}
    </div>;
  }
}

const mapStateToProps = (state: any) => ({
  hashiMap: state.hashiMap,
});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(HashiSolverContainer);