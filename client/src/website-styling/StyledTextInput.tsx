import React from 'react';
import classNames from 'classnames';
import styled from 'styled-components';
import { Input } from './default';
interface Props {
  label: string;
  callback: (e: any) => void;
  value: string;
  className?: string;
}
const TextInput: React.FC<Props> = props => {
  return(
    <div className={classNames('input--styled-text', props.className)}>
      <div className={'label'}>{props.label}</div>
      <Input
        onChange={(e: any) => props.callback(e)}
        value={props.value}
      />
    </div>
  )

};

export default styled(TextInput)`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  color: ${(props) => props.theme.color.fontWhite}
  
  .label {
    font-family: 'Righteous', cursive;
    font-size: 24px;
  }
  
  input {
    width: 96px;
  }
`;