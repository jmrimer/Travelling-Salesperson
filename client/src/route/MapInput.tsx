import React, { Component } from 'react';
import styled from 'styled-components';
import classNames from 'classnames';

interface Props {
  getNewRoute: (mapText: string) => void;
  mapText: string;
  className?: string;
}

export class MapInput extends Component<Props> {
  render() {
    return (
      <div className={classNames('map-input', this.props.className)}>
        {this.renderInstructions()}
        {this.renderMapInput()}
        {this.renderSubmitButton()}
      </div>
    );
  }

  private renderSubmitButton() {
    return (
      <button onClick={this.handleClick()}>
        Calculate Shortest Route to all Cities
      </button>
    );
  }

  private renderMapInput() {
    return (
      <textarea>
        {this.props.mapText}
      </textarea>
    );
  }

  private renderInstructions() {
    return (
      <div className={'instructions'}>
        Type your map coordinates into this box in the following format:
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        *no spaces per column, only between columns
      </div>
    );
  }

  private handleClick() {
    return () => this.props.getNewRoute(this.props.mapText);
  }
}

export const StyledMapInput = styled(MapInput)`
  display: flex;
  flex-direction: column;
  white-space: pre;
  font-size: 24px;
  
  .exampleList {
    font-size: 16px;
    font-style: italic;
  }
  
  textarea {
    font-size: 16px;
    resize: vertical;
    border: 1px solid blue;
    min-height: 200px;
  }
  
  button {
    flex: 1;
    background: purple;
    color: white;
    height: 48px;
  }
`;