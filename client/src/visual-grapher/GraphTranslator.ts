export const centerOf: any = (points: any[]) => {
  let sumX: number = 0;
  let sumY: number = 0;
  points.map((point) => {
    sumX += point.x;
    sumY += point.y;
  });
  return {x: sumX / points.length, y: sumY / points.length};
};

export const translatePointsToNewCenter = (points: any[], newCenter: any) => {
  let center = centerOf(points);
  points.map((point) => {
    point.x = point.x - center.x + newCenter.x;
    point.y = point.y - center.y + newCenter.y;
  });
  return points;
};

export const rotate180AroundCenterOf: any = (points: any[]) => {
  const center = centerOf(points);
  let radians = Math.PI;
  let cos = Math.cos(radians);
  let sin = Math.sin(radians);
  points.map((point) => {
    point.x = Math.round(
      (cos * (point.x - center.x))
      + (sin * (point.y - center.y))
      + center.x
    );
    point.y = Math.round(
      (cos * (point.y - center.y))
      + (sin * (point.x - center.x))
      + center.y
    );
  });
  return points;
};