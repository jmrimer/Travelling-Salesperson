import React from 'react';
import classNames from 'classnames';
import styled from 'styled-components';
import { translatePointsToNewCenter } from '../shared-graphic-components/visual-grapher/GraphTranslator';
import VisualGraph from '../shared-graphic-components/visual-grapher/VisualGraph';

interface Props {
  points: any[],
  routes: any[] | null,
  className?: string
}

export const MultiPathVisualGraph: React.FC<Props> = props => {
  let {
    routes,
    className,
  } = props;

  let elements: any[] = [];

  let points = translatePointsToNewCenter(
    props.points,
    {x: 300, y: 300}
  );

  if (points) {
    let nodes = points.map((point) => {
      return {data: {id: point.name, label: point.name}, position: {x: point.x, y: point.y}}
    });
    Object.assign(elements, nodes);

    if (routes) {
      for (let i = 0; i < routes.length; i++) {
        let route = routes[i].route;
        for (let j = 0; j < route.length - 1; j++) {
          try {
            elements.push({data: {source: route[j].name, target: route[j + 1].name}});
          } catch {
            console.log(route);
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
