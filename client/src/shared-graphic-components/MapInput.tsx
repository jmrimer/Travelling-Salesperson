import React, { Component } from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import CoordinateExtractor from './uploader/CoordinateExtractor';
import { StyledMapFileDropzone } from './uploader/MapFileDropzone';
import { Button } from '../website-styling/default';

interface Props {
  mapText: string;
  getNewRoute: () => void;
  updateMapText: (e: any) => void;
  className?: string;
}

export class MapInput extends Component<Props> {
  render() {
    return (
      <div className={classNames('map-input', this.props.className)}>
        {this.renderDropzone()}
        {this.renderMapInput()}
        {this.renderSubmitButton()}
      </div>
    );
  }

  private renderSubmitButton() {
    return (
      <div className={'box--submit-button'}>
        <Button onClick={() => this.props.getNewRoute()}>
          Calculate Shortest Route to all Cities
        </Button>
      </div>
    );
  }

  private renderMapInput() {
    return (
      <div className={'input--text-and-instructions'}>
        {this.renderInstructions()}
        <textarea
          onChange={(e: any) => this.props.updateMapText(e)}
          value={this.props.mapText}
        />
      </div>
    );
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
  flex: 1;
  white-space: pre;
  font-size: 24px;
  color: ${(props) => props.theme.color.fontWhite};
  font-family: 'Righteous', cursive;
  max-width: 600px;
  white-space: normal;
  margin-top: 24px;
  justify-content: space-between;
  
  
  
  .input--text-and-instructions {
    display: flex;
    flex: 1;
    flex-direction: column;
   
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
      display: flex;
      flex: 1;
    };
  }
  
    
  .box--submit-button {
    display: flex;
    margin-top: 16px;
    height: 80px;
  }
`;