import React, { Component } from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import CoordinateExtractor from './uploader/CoordinateExtractor';
import { StyledMapFileDropzone } from './uploader/MapFileDropzone';

interface Props {
  getNewRoute: (mapText: string) => void;
  mapText: string;
  updateMapText: (e: any) => void;
  className?: string;
}

export class MapInput extends Component<Props> {
  render() {
    return (
      <div className={classNames('map-input', this.props.className)}>
        {this.renderDropzone()}
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
    return <textarea
          onChange={(e: any) => this.props.updateMapText(e)}
          value={this.props.mapText}
        />;
  }

  private renderInstructions() {
    return (
      <div className={'instructions'}>
        <div className={'exampleList'}>
          <div>Format: city# latitude longitude</div>
        </div>
      </div>
    );
  }

  private handleClick() {
    return () => this.props.getNewRoute(this.props.mapText);
  }

  private fileHandler(fileText: string) {
    let event = {target: {value: CoordinateExtractor(fileText)}};
    this.props.updateMapText(event);
  }

  private renderDropzone() {
    return <StyledMapFileDropzone
      className={'dropzone'}
      fileHandler={(fileText: string) => this.fileHandler(fileText)}
    />
  }
}

export const StyledMapInput = styled(MapInput)`
  display: flex;
  flex-direction: column;
  white-space: pre;
  font-size: 24px;
  color: ${(props) => props.theme.color.fontWhite};
  font-family: 'Righteous', cursive;
  max-width: 600px;
  white-space: normal;
  margin-top: 24px;
  
  .instructions {
    text-align: justify;
    margin-top: 64px
  }
  
  .exampleList {
    padding-top: 8px;
    padding-left: 8px;
    font-family: Roboto,sans-serif;
    font-style: italic;
    font-size: 16px;
  }
  
  textarea {
    background: ${(props) => props.theme.color.foreground};
    padding-top: 8px;
    padding-left: 8px;
    margin-top: 8px;
    font-size: 16px;
    resize: vertical;
    border-radius: 4px;
    min-height: 200px;
  }
  
  button {
    display: inline-block;
    font-family: Righteous, cursive;
    font-size: 24px;
    margin-top: 16px;
    flex: 1;
    background: ${(props) => props.theme.color.wedgewood};
    border: 1px solid ${(props) => props.theme.color.foreground};
    border-radius: 8px;
    color: white;
    padding: 16px;
    cursor: pointer;
    
    :hover {
      background: ${(props) => props.theme.color.lavender};
    }
  }
  
  .dropzone {
  }
`;