export class Log {
  id: number;
  category: string;
  details: string;
  duration: number;
  activity: string;

  constructor(
    id: number = 0,
    category: string = '',
    details: string = '',
    duration: number = 0,
    activity: string = ''
  ) {
    this.id = id;
    this.category = category;
    this.details = details;
    this.duration = duration;
    this.activity = activity;
  }
}
