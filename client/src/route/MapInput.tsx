import React, { Component } from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { MapModel } from '../actions/MapModel';

interface Props {
  getNewRoute: (map: MapModel) => void;
  mapText: string;
  className?: string;
}

export class MapInput extends Component<Props> {
  render() {
    return (
      <div className={classNames('map-input', this.props.className)}>
        <div>{MapInput.instructions()}</div>
        <textarea>
          {this.props.mapText}
        </textarea>
        <button
          onClick={() => this.props.getNewRoute(this.buildMap())}
        >
          Calculate Shortest Route to all Cities
        </button>
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

  private buildMap(): MapModel {
    let map = new MapModel();
    map.serialize(this.props.mapText);
    return map;
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