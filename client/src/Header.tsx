import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';

const logo = require('./theme/logo.svg');

interface Props {
  className?: string;
}
class Header extends React.Component<Props> {
  render() {
    return (
      <div className={classNames('header', this.props.className)}>
        <span className={'logo'}>
          <img src={logo}/>
        </span>
        <span className={'title'}>
          Traveler
        </span>
      </div>
    );
  }
}

export const StyledHeader = styled(Header)`
  display: flex;
  align-items: center;
  padding: 16px;
  
  img {
    height: 80px;
  }
  
  .title {
    color: white;
    font-family: 'Kaushan Script', cursive;
    margin-left: 16px;
    font-size: 64px;
    color: ${(props) => props.theme.color.talcum};
    font-weight: 600;
  }
`;