export class ActivityLog {
  id: number;
  name: string;
  distance: number;
  details: string;

  constructor(
    id: number = 0,
    name: string = '',
    distance: number = 0,
    details: string = ''
  ) {
    this.id = id;
    this.name = name;
    this.distance = distance;
    this.details = details;
  }
}
