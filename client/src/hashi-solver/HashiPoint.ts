export enum Direction {
  UP,
  RIGHT,
  DOWN,
  LEFT
}

export class HashiPoint {
  constructor(
    public x: number = 0,
    public y: number = 0,
    public number: number = 0,
  ) {
  }
}