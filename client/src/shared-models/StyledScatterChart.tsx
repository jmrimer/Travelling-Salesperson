import styled from 'styled-components';
import { TrialModel } from '../genetic-algorithm/TrialModel';
import classNames from 'classnames';
import React from 'react';
import { CartesianGrid, Scatter, ScatterChart, XAxis, YAxis } from 'recharts';
import { theme } from '../website-styling/default';
import { GenerationModel } from './GenerationModel';
import { TourModel } from './TourModel';

interface Props {
  trial: TrialModel;
  className?: string;
}

export const StyledScatterChart: React.FC<Props> = props => {
  let {trial} = props;

  const data = () => {
    let points: any[] = [];
    trial.generations.map((generation: GenerationModel) => {
      return generation.children.map((child: TourModel, index) => {
        return points.push(
          {
            x: generation.generation,
            y: child.weight
          })
      });
    });

    return points;
    // return ([
    //   {x: 1.5, y: 1.5},
    //   {x: 2.5, y: 2.5},
    //   {x: 3.5, y: 3.5},
    //   {x: 4.5, y: 4.5},
    //   // {cx: 1.5, cy: 1.5, r: 10},
    //   // {cx: 2.5, cy: 2.5, r: 10},
    //   // {cx: 3.5, cy: 3.5, r: 10},
    //   // {cx: 4.5, cy: 4.5, r: 10},
    // ])
  };

  console.log(data);
  return (
    <div className={classNames('scatter-chart', props.className)}>
      <ScatterChart
        width={400}
        height={400}
      >
        <XAxis
          type={'number'}
          name={'Generation'}
          dataKey={'x'}
          // domain={[0,4]}
        />
        <YAxis
          type={'number'}
          name={'Path Weight'}
          // domain={[0,4]}
          dataKey={'y'}
        />
        <CartesianGrid/>
        <Scatter
          name={'Tours'}
          data={data()}
          fill={'#FFF'}
          shape={'circle'}
          color={'#FFF'}
          opacity={0.05}
        />
      </ScatterChart>
    </div>
  );

};

export default styled(StyledScatterChart)`

`;