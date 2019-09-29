import React from 'react';
import { translatePointsToNewCenter } from './GraphTranslator';
import CytoscapeComponent from "react-cytoscapejs";
import { Button, theme } from '../../website-styling/default';
import styled from 'styled-components';

interface Props {
  points: any[];
  tour: any[] | null;
  className?: string;
}

export const SinglePathVisualGraph: React.FC<Props> = props => {
    let cyRef: any;
    let el = 0;

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

      if (props.tour) {
        let route = props.tour;
        for (let i = 0; i < route.length - 1; i++) {
          elements.push({data: {source: route[i].name, target: route[i + 1].name}});
        }
      }
    }

    function animate2x() {
      if (el < elements.length) {
        cyRef.elements()[el].addClass('highlighted');
        el++;
        return setTimeout(animate2x, 250);
      }
      setTimeout(fadeAway, 3000);
    }

    function animate1x() {
      if (el < elements.length) {
        cyRef.elements()[el].addClass('highlighted');
        el++;
        return setTimeout(animate1x, 500);
      }
      setTimeout(fadeAway, 3000);
    }

    const fadeAway = () => {
      for (let i = 0; i < elements.length; i++) {
        cyRef.elements()[i].removeClass('highlighted');
      }
    };

  function renderAnimationButtons() {
    return (
      <div className={'button-box'}>
        <Button onClick={() => {
          el = points.length;
          animate1x()
        }}
        >
          Animate
        </Button>
        <Button onClick={() => {
          el = points.length;
          animate2x()
        }}
        >
          Animate 2x
        </Button>
      </div>
    );
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
        {
          props.tour
            ? renderAnimationButtons()
            : <div className={'button-box__spacer'}>&nbsp;</div>
        }
      </div>
    );
  }
;

export default styled(SinglePathVisualGraph)`
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