import React from 'react';
import BruteForceContainer from './brute-force/BruteForceContainer';
import styled, { ThemeProvider } from 'styled-components';
import classNames from 'classnames';
import { theme } from './website-styling/default';
import Header from './website-styling/Header';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import GraphContainer from './graph-search/GraphContainer';
import HeuristicInsertionContainer from './heuristic-instertion/HeuristicInsertionContainer';
import GeneticAlgorithmContainer from './genetic-algorithm/GeneticAlgorithmContainer';
import CrowdWisdomContainer from './crowd-wisdom/CrowdWisdomContainer';
import HashiContainer from './hashi-solver/HashiSolverContainer';

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
              <Header/>
            </div>
            <Route exact path={'/'} component={BruteForceContainer}/>
            <Route exact path={'/graph-search'} component={GraphContainer}/>
            <Route exact path={'/heuristic-insertion'} component={HeuristicInsertionContainer}/>
            <Route exact path={'/genetic-algorithm'} component={GeneticAlgorithmContainer}/>
            <Route exact path={'/crowd-wisdom'} component={CrowdWisdomContainer}/>
            <Route exact path={'/hashi-solver'} component={HashiContainer}/>
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
