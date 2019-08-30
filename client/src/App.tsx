import React from 'react';
import RouteContainer from './route/RouteContainer';
import styled, { ThemeProvider } from 'styled-components';
import classNames from 'classnames';
import { theme } from './theme/default';
import { StyledHeader } from './Header';

interface Props {
  className?: string
}

class App extends React.Component<Props> {
  render() {
    return (
      <ThemeProvider theme={theme}>
        <div className={classNames('app', this.props.className)}>
          <div className={'header'}>
            <StyledHeader/>
          </div>
          <div className={'container'} style={{height: 'auto'}}>
          <RouteContainer/>
        </div>
      </div>
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
