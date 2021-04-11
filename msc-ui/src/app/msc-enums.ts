
export enum AccountStatus {
  REGISTERED,
  ACTIVE
}

export enum AnswerType {
  ENUM,
  YES_NO,
  PERCENTAGE
}

export enum MaturityEvaluationStatus {
  COLECTING_ANSWERS,
  REALISING_TASKS,
  FINISHED
}

export enum MaturityLevelTransitionStatus {
  IN_PROGRESS,
  FINISHED
}

export enum NotificationType {
  SUCCESS,
  ERROR,
  INFO
}

export enum QuestionType {
  SINGLE_SELECTION,
  MULTIPLE_SELECTION
}

export enum Role {
  ADMINISTRATOR,
  ORGANIZATION_ADMIN,
  STAFF,
  EXECUTOR
}

export enum TaskPriority {
  A,
  B,
  C
}

export enum TaskState {
  OPEN,
  IN_PROGRESS,
  DONE
}

