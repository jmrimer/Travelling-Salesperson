import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { Link } from 'react-router-dom';
import { updateCurrentPage } from '../redux/actions';
import { connect } from 'react-redux';

const logo = require('./logo.svg');

export enum Page {
  HASHI_SOLVER,
  GENETIC_ALGORITHM,
  BRUTE_FORCE,
  GRAPH_SEARCH,
  HEURISTIC_INSERTION,
  CROWD_WISDOM
}

interface Props {
  currentPage: Page;
  updateCurrentPage: (page: Page) => void;
  className?: string;
}

class Header extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('header', this.props.className)}>
        {Header.renderLogo()}
        {this.renderNavigation()}
      </div>
    );
  }

  private static renderLogo() {
    return (
      <div className={'logo-container'}>
        <span className={'logo'}>
          <img src={logo} alt={'Logo not found'}/>
        </span>
        <span className={'title'}>
          Traveler
        </span>
      </div>
    )
  }

  private chooseClassName(page: Page) {
    if (this.props.currentPage === page) {
      return classNames('nav-link', 'selected');
    }
    return classNames('nav-link', 'not-selected');
  }

  private renderNavigation() {
    return (
      <div className={'navigation-container'}>
        {this.bruteForceLink()}
        {this.graphSearchLink()}
        {this.heuristicInsertionLink()}
        {this.geneticAlgorithmLink()}
        {this.crowdWisdomLink()}
        {this.hashiSolverLink()}
      </div>
    )
  }

  private bruteForceLink() {
    return <Link
      to={'/'}
      className={this.chooseClassName(Page.BRUTE_FORCE)}
      onClick={() => this.handleLinkClick(Page.BRUTE_FORCE)}
    >
      Brute Force
    </Link>;
  }

  private graphSearchLink() {
    return <Link
      to={'/graph-search'}
      className={this.chooseClassName(Page.GRAPH_SEARCH)}
      onClick={() => this.handleLinkClick(Page.GRAPH_SEARCH)}
    >
      Graph Search
    </Link>;
  }

  private heuristicInsertionLink() {
    return <Link
      to={'/heuristic-insertion'}
      className={this.chooseClassName(Page.HEURISTIC_INSERTION)}
      onClick={() => this.handleLinkClick(Page.HEURISTIC_INSERTION)}
    >
      Heuristic Insertion
    </Link>;
  }

  private geneticAlgorithmLink() {
    return <Link
      to={'/genetic-algorithm'}
      className={this.chooseClassName(Page.GENETIC_ALGORITHM)}
      onClick={() => this.handleLinkClick(Page.GENETIC_ALGORITHM)}
    >
      Genetic Algorithm
    </Link>;
  }

  private crowdWisdomLink() {
    return <Link
      to={'/crowd-wisdom'}
      className={this.chooseClassName(Page.CROWD_WISDOM)}
      onClick={() => this.handleLinkClick(Page.CROWD_WISDOM)}
    >
      Crowd Wisdom
    </Link>;
  }

  private hashiSolverLink() {
    return <Link
      to={'/hashi-solver'}
      className={this.chooseClassName(Page.HASHI_SOLVER)}
      onClick={() => this.handleLinkClick(Page.HASHI_SOLVER)}
    >
      Hashi Solver
    </Link>;
  }

  private handleLinkClick(page: Page) {
    this.props.updateCurrentPage(page);
  }
}

const mapStateToProps = (state: any) => ({
  currentPage: state.currentPage
});

const mapDispatchToProps = {
  updateCurrentPage: updateCurrentPage
};

export default connect(mapStateToProps, mapDispatchToProps)(styled(Header)`
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  padding: 16px;
  
  img {
    height: 80px;
  }
  
  .logo-container {
    display: flex;
    align-items: center;
    .title {
      color: white;
      font-family: 'Kaushan Script', cursive;
      margin-left: 16px;
      font-size: 64px;
      color: ${(props) => props.theme.color.wedgewood};
      font-weight: 600;
    }
  } 
  
  .navigation-container {
    display: flex;
    flex-direction: row;
    
    .selected {
      border-bottom: 2px solid ${(props) => props.theme.color.fontWhite} !important;
    }
    > * {
      margin: 0 24px;
      font-family: Righteous, cursive;
      color: ${(props) => props.theme.color.wedgewood};
      font-size: 24px;
      text-decoration: none;
      border-bottom: 2px solid ${(props) => props.theme.color.wedgewood};
      
      :hover {
        border-bottom: 2px solid ${(props) => props.theme.color.lavender};
      }
    }
  }
`);