import React from 'react';
import { translatePointsToNewCenter } from './GraphTranslator';
import CytoscapeComponent from "react-cytoscapejs";
import { theme } from '../../website-styling/default';
import styled from 'styled-components';
import { EdgeModel } from '../../crowd-wisdom/EdgeModel';

interface Props {
  points: any[];
  edges: any[] | null;
  className?: string;
}

export const EdgesVisualGraph: React.FC<Props> = props => {
    let cyRef: any;

    let points = translatePointsToNewCenter(
      props.points,
      {x: 300, y: 300}
    );

    let elements: any[] = [];
    if (points) {
      let nodes = points.map((point) => {
        return {data: {id: point.name, label: point.name}, position: {x: point.x, y: point.y}}
      });
      Object.assign(elements, nodes);

      if (props.edges) {
        console.log(props.edges);
        props.edges.map((edge: EdgeModel) => {
          elements.push({data: {source: edge.start.name, target: edge.end.name}});
        });
      }
    }

    return (
      <div className={props.className}>
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
            },
            {
              selector: '.highlighted',
              style: {
                'background-color': theme.color.maroon5,
                'transition-duration': '0.500s',
                'transition-timing-function': 'ease-in',
                'line-color': theme.color.flare,
              }
            }
          ]}
          cy={(cy: any) => {
            cyRef = cy;
            cy.fit();
          }}
        />
      </div>
    );
  }
;

export default styled(EdgesVisualGraph)`
  .button-box {
    display: flex;
    height: 80px;
    flex-direction: row;
    justify-content: space-around;
    
    button {
      margin: 0 16px;
    }
  }
  .button-box__spacer {
    height: 80px;
  }
`;