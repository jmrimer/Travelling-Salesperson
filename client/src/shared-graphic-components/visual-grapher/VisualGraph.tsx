import React from 'react';
import { translatePointsToNewCenter } from './GraphTranslator';
import CytoscapeComponent from "react-cytoscapejs";
import { theme } from '../../website-styling/default';

interface Props {
  points: any[];
  tour: any[] | null;
  className?: string;
}

export const VisualGraph: React.FC<Props> = props => {
  let points = translatePointsToNewCenter(
    props.points,
    {x: 300, y: 300}
  );
  let elements: any[];

  elements = points.map((point) => {
    return {data: {id: point.name, label: point.name}, position: {x: point.x, y: point.y}}
  });

  if (props.tour) {
    let route = props.tour;
    for (let i = 0; i < route.length - 1; i++) {
      console.log(route[i].name);
      elements.push({data: {source: route[i].name, target: route[i+1].name}});
    }
  }

  return (
    <div>
      <CytoscapeComponent
        elements={elements}
        style={
          {
            width: '600px',
            height: '600px',
          }
        }
        stylesheet={[
          {
            selector: 'node',
            style: {
              width: 4,
              height: 4,
              shape: 'circle',
              'background-color': theme.color.fontWhite,
              color: theme.color.wedgewood,
              label: 'data(label)',
              'font-size': 2,
              'font-weight': 'bold',
              'min-zoomed-font-size': 2,
              'text-valign': 'center',
              'text-halign': 'center',
              'text-outline-color': theme.color.plum,
              'text-outline-width': 0.15
            }
          },
          {
            selector: 'edge',
            style: {
              'line-color': theme.color.lavender,
              width: 0.5,
            }
          }
        ]}
        cy={(cy: any) => {
          cy.fit();
        }}
      />
    </div>
  );
};

export default VisualGraph;