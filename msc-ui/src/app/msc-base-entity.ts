import { BaseEntity } from './core-model';

export interface MscBaseEntity extends BaseEntity {
  timestamp: any;
  version: number;
}
