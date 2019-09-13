import { shallow, ShallowWrapper } from 'enzyme';
import { InteractiveCell } from './InteractiveCell';
// @ts-ignore
import React from 'react';

describe('InteractiveCell', () => {
  let subject: ShallowWrapper;
  let callbackSpy: any;

  beforeEach(() => {
    callbackSpy = jest.fn();
    subject = shallow(
      <InteractiveCell
        keyValuePair={{startId: 1, endId: 2}}
        connected={false}
        callback={callbackSpy}
      />
    )
  });

  it('should trigger the given function on click', () => {
    subject.simulate('click');
    expect(callbackSpy).toHaveBeenCalledWith({startId: 1, endId: 2});
  });

  it('should display an X if true and nothing if false', () => {
    expect(subject.text()).toBe(' ');
    subject = shallow(
      <InteractiveCell
        keyValuePair={{startId: 1, endId: 2}}
        connected={true}
        callback={callbackSpy}
      />
    );
    expect(subject.text()).toBe('X');
  });
});