import React from 'react';
import classNames from 'classnames';
import styled from 'styled-components';
import {
  flipVerticallyAroundCenterOf, rotate180AroundCenterOf,
  translatePointsToNewCenter
} from '../shared-graphic-components/visual-grapher/GraphTranslator';
import { VisualGraphWithGrid } from '../shared-graphic-components/visual-grapher/VisualGraphWithGrid';


interface Props {
  points: any[],
  className?: string
}

export const HashiVisualGraph: React.FC<Props> = props => {
  let {
    className,
    points
  } = props;

  let elements: any[] = [];

  let newCenter = {x: 300, y: 300};

  let translatedPoints = translatePointsToNewCenter(
    points,
    newCenter
  );

  flipVerticallyAroundCenterOf(translatedPoints);
  rotate180AroundCenterOf(translatedPoints);

  if (translatedPoints) {
    let nodes = translatedPoints.map((point) => {
      return {
        data: {
          id: `${point.x}${point.y}${point.name}`,
          label: point.name
        },
        position: {
          x: point.x,
          y: point.y
        }
      }
    });
    Object.assign(elements, nodes);
  }

  return (
    <div className={classNames('multi-path-visual-graph', className)}>
      <VisualGraphWithGrid elements={elements}/>
    </div>
  )
};

export default styled(HashiVisualGraph)``;
