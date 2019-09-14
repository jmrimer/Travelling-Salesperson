import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { Link } from 'react-router-dom';

const logo = require('./logo.svg');

interface Props {
  className?: string;
}

class Header extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('header', this.props.className)}>
        {Header.renderLogo()}
        {Header.renderNavigation()}
        {Header.renderSpacer()}
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

  private static renderNavigation() {
    return (
      <div className={'navigation-container'}>
        <Link to={'/'}>Brute Force</Link>
        <Link to={'/graph-search'}>Graph Search</Link>
        <Link to={'/heuristic-insertion'}>Heuristic Insertion</Link>
      </div>
    )
  }

  private static renderSpacer() {
    return (
      <div className={'spacer-container'}>
        &nbsp;
      </div>
    )
  }
}

export const StyledHeader = styled(Header)`
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
    
    > * {
      margin: 0 24px;
      font-family: Righteous, cursive;
      color: ${(props) => props.theme.color.wedgewood};
      font-size: 24px;
      text-decoration: none;
      border-bottom: 2px solid ${(props) => props.theme.color.lavender};
      
      :hover {
        border-bottom: 2px solid ${(props) => props.theme.color.fontWhite};
      }
    }
  }
`;