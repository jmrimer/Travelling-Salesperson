import styled from 'styled-components';
import classNames from 'classnames';
import React from 'react';

interface Props {
  className?: string;
}

const DividingLine: React.FC<Props> = props => {
  return (
    <div
      className={classNames('dividing-line', props.className)}
    >
      &nbsp;
    </div>
  )
};

export default styled(DividingLine)`
  width: 2px;
  border: 2px solid ${(props) => props.theme.color.fontWhite};
  margin: 8px 16px;
`;