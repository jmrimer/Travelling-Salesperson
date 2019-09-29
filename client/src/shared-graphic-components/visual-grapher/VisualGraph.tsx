import styled from 'styled-components';
import classNames from 'classnames';
import React from 'react';
import { theme } from '../../website-styling/default';
import CytoscapeComponent from "react-cytoscapejs";

interface Props {
  elements: any[],
  className?: string
}

export const VisualGraph: React.FC<Props> = props => {
  let {
    elements,
    className
  } = props;
  return (
    <div className={classNames('visual-graph', className)}>
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
              width: 0.1,
              'curve-style': 'haystack',
              'haystack-radius': 0.5
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
          cy.fit();
        }}
      />
    </div>
  )
};

export default styled(VisualGraph)``;
