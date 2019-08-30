import React, { Component } from 'react';
import styled from 'styled-components';
import classNames from 'classnames';

interface Props {
  className?: string;
}

export class MapInput extends Component<Props> {
  render() {
    return (
      <div className={classNames('map-input', this.props.className)}>
        <div>{MapInput.instructions()}</div>
        <textarea>
          {MapInput.example()}
        </textarea>
        <button>Calculate Shortest Route to all Cities</button>
      </div>
    );
  }

  private static instructions() {
    return (
      <span className={'instructions'}>
        Type your map coordinates into this box in the following format:
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        <div className={'exampleList'}>CityNumber Latitude Longitude</div>
        *no spaces per column, only between columns
      </span>
    );
  }

  private static example() {
    return (
      '1 87.951292 2.658162\n' +
      '2 33.466597 66.682943\n' +
      '3 91.778314 53.807184\n' +
      '4 20.526749 47.633290'
    );
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