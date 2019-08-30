import { RouteInfo } from './RouteInfo';
import { shallow, ShallowWrapper } from 'enzyme';
import { RouteContainer } from './RouteContainer';

describe('RouteContainer', () => {
  let routeContainer: ShallowWrapper;

  beforeEach(() => {
    routeContainer = shallow(
      <RouteContainer / >
    );
  });
  it('should pass props and display route info', () => {
    expect(routeContainer.find(RouteInfo).exists).toBeTruthy();
  });
});