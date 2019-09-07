import React from 'react';
import RouteContainer from './shortest-path-through-map/RouteContainer';
import styled, { ThemeProvider } from 'styled-components';
import classNames from 'classnames';
import { theme } from './website-styling/default';
import { StyledHeader } from './website-styling/Header';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import GraphContainer from './shortest-path-through-graph/GraphContainer';

interface Props {
  className?: string
}

class App extends React.Component<Props> {
  render() {
    return (
      <ThemeProvider theme={theme}>
        <Router>
          <div className={classNames('app', this.props.className)}>
            <div className={'header'}>
              <StyledHeader/>
            </div>
            <Route exact path={'/'} component={RouteContainer}/>
            <Route exact path={'/graphs'} component={GraphContainer}/>
          </div>
      </Router>
      </ThemeProvider>
    );
  }
}

export default styled(App)`
  height: 100%;

  background: linear-gradient(8deg,#08132D,#030D69);
  * {
    box-sizing: border-box;
  };
  `;
