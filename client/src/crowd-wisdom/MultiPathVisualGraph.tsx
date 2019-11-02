import React from 'react';
import classNames from 'classnames';
import styled from 'styled-components';
import { translatePointsToNewCenter } from '../shared-graphic-components/visual-grapher/GraphTranslator';
import VisualGraph from '../shared-graphic-components/visual-grapher/VisualGraph';
import { TourModel } from '../shared-models/TourModel';

interface Props {
  points: any[],
  tours: TourModel[] | null,
  className?: string
}

export const MultiPathVisualGraph: React.FC<Props> = props => {
  let {
    tours,
    className,
  } = props;

  let elements: any[] = [];

  let points = translatePointsToNewCenter(
    props.points,
    {x: 300, y: 300}
  );

  if (points) {
    let nodes = points.map((point) => {
      return {data: {id: `${point.x}${point.y}${point.name}`, label: point.name}, position: {x: point.x, y: point.y}}
    });
    Object.assign(elements, nodes);

    if (tours) {
      for (let i = 0; i < tours.length; i++) {
      let cycle = tours[i].cycle;
        for (let j = 0; j < cycle.length - 1; j++) {
          try {
            elements.push({data: {source: cycle[j].name, target: cycle[j + 1].name}});
          } catch {
            console.log(cycle);
          }
        }
      }
    }
  }

  return (
    <div className={classNames('multi-path-visual-graph', className)}>
      <VisualGraph elements={elements}/>
    </div>
  )
};

export default styled(MultiPathVisualGraph)``;
