import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';

interface Props {
  keyValuePair: any;
  callback: (keyValuePair: any) => void;
  connected: boolean;
  className?: string;
}

export const InteractiveCell: React.FC<Props> = props => {
  let {connected, keyValuePair} = props;

  function handleClick() {
    props.callback(props.keyValuePair);
  }

  return (
    <div
      className={classNames('interactive-cell', props.className)}
      key={`${keyValuePair.startId + 1}-${keyValuePair.endId + 1}`}
      onClick={handleClick}
    >
      {connected
        ? (<div>X</div>)
        : (<div>&nbsp;</div>
        )
      }
    </div>
  )
};

export default (styled(InteractiveCell)`
  color: ${(props) => props.theme.color.fontWhite};
  height: 48px;
  width: 48px;
  font-family: Righteous, cursive;
  font-size: 36px;
  cursor: pointer;
  border: solid 1px ${(props) => props.theme.color.fontWhite};
  display: flex;
  align-items: center;
  justify-content: center;
`);