import {AccountStatus, AnswerType, MaturityEvaluationStatus,
  MaturityLevelTransitionStatus, QuestionType, Role, TaskPriority, TaskState} from './msc-enums';
import { MscBaseEntity } from './msc-base-entity';

export interface Answer extends  MscBaseEntity{
  answerContent: string;
  answerDependencies: AnswerDependency[];
  maturityLevel: number;
  question: Question;
  questionAnswers: QuestionAnswer[];
}

export interface AnswerDependency extends  MscBaseEntity{
  answer: Answer;
  dependentQuestion: Question;
  minimumMaturityLevel: number;
}

export interface Attachment extends  MscBaseEntity{
  attachmentName: string;
  attachmentType: string;
}

export interface BusinessUnit extends  MscBaseEntity{
  childUnits: BusinessUnit[];
  employments: Employment[];
  machineGroups: MachineGroup[];
  maturityEvaluations: MaturityEvaluation[];
  name: string;
  organization: Organization;
  parentUnit: BusinessUnit;
  questionaries: Questionary[];
}

export interface Comment extends  MscBaseEntity{
  commentChanges: TaskCommentChange[];
  commentator: Employment;
  content: string;
  task: Task;
}

export interface ConfigurationVariable extends  MscBaseEntity{
  name: string;
  value: string;
}

export interface Employment extends  MscBaseEntity{
  accountStatus: AccountStatus;
  departments: BusinessUnit[];
  email: string;
  expirationDate: number;
  functionalities: Functionality[];
  isDeleted: boolean;
  isTutorialWatched: boolean;
  organization: Organization;
  person: Person;
  role: Role;
  userQuestionaryAnswers: UserQuestionaryAnswers[];
}

export interface Functionality extends  MscBaseEntity{
  name: string;
}

export interface Language extends  MscBaseEntity{
  isMaster: boolean;
  locale: string;
  staticTranslations: StaticTranslation[];
}

export interface Machine extends  MscBaseEntity{
  machineGroup: MachineGroup;
  name: string;
}

export interface MachineGroup extends  MscBaseEntity{
  businessUnit: BusinessUnit;
  machines: Machine[];
  name: string;
}

export interface MaturityEvaluation extends  MscBaseEntity{
  businessUnit: BusinessUnit;
  maturityLevelTransition: MaturityLevelTransition;
  questionAnswers: QuestionAnswer[];
  status: MaturityEvaluationStatus;
  tasks: Task[];
}

export interface MaturityLevel extends  MscBaseEntity{
  description: string;
  level: number;
}

export interface MaturityLevelTransition extends  MscBaseEntity{
  currentMaturityLevel: MaturityLevel;
  maturityEvaluations: MaturityEvaluation[];
  status: MaturityLevelTransitionStatus;
  targetMaturityLevel: MaturityLevel;
}

export interface Organization extends  MscBaseEntity{
  businessLine: string;
  businessUnits: BusinessUnit[];
  currentMaturityLevel: MaturityLevel;
  historicalMaturityLevels: MaturityLevel[];
  isDeleted: boolean;
  mission: string;
  name: string;
  organizationAdmins: Employment[];
  organizationLogo: OrganizationLogo;
  strategy: string;
  targetMaturityLevel: MaturityLevel;
  vision: string;
  whiteLabelParam: WhiteLabelParam;
}

export interface OrganizationLogo extends  Attachment{
  organization: Organization;
}

export interface Person extends  MscBaseEntity{
  dateOfBirth: number;
  defaultLanguage: string;
  email: string;
  employments: Employment[];
  firstName: string;
  initials: string;
  isAdmin: boolean;
  isDeleted: boolean;
  lastName: string;
  password: string;
  salutation: string;
  userAvatar: UserAvatar;
}

export interface Question extends  MscBaseEntity{
  answerType: AnswerType;
  answers: Answer[];
  comment: string;
  contentOfTheQuestion: string;
  innerNr: number;
  isMandatory: boolean;
  name: string;
  questionImage: QuestionImage;
  questionType: QuestionType;
  tasks: Task[];
}

export interface QuestionAnswer extends  MscBaseEntity{
  answer: Answer;
  comment: string;
  maturityEvaluation: MaturityEvaluation;
  userQuestionaryAnswers: UserQuestionaryAnswers;
}

export interface QuestionImage extends  Attachment{
  question: Question;
}

export interface QuestionMaturityLevel extends  MscBaseEntity{
  answer: Answer;
  comment: string;
  maturityEvaluation: MaturityEvaluation;
  questionaryResultEvaluation: QuestionaryResultEvaluation;
}

export interface QuestionTarget extends  MscBaseEntity{
  answer: Answer;
  comment: string;
  maturityEvaluation: MaturityEvaluation;
  questionaryTarget: QuestionaryTarget;
}

export interface Questionary extends  MscBaseEntity{
  businessUnits: BusinessUnit[];
  isDeleted: boolean;
  name: string;
  questions: Question[];
  userQuestionaryAnswers: UserQuestionaryAnswers[];
}

export interface QuestionaryResultEvaluation extends  MscBaseEntity{
  employment: Employment;
  questionMaturityLevel: QuestionMaturityLevel[];
  questionary: Questionary;
}

export interface QuestionaryTarget extends  MscBaseEntity{
  employment: Employment;
  questionTargets: QuestionTarget[];
  questionary: Questionary;
}

export interface StaticTranslation extends  MscBaseEntity{
  language: Language;
  name: string;
  status: string;
  translationModule: TranslationModule;
  uiKey: string;
}

export interface SystemConfig extends  MscBaseEntity{
  name: string;
  value: string;
}

export interface Task extends  MscBaseEntity{
  comments: Comment[];
  deadline: number;
  description: string;
  explanation: string;
  maturityEvaluation: MaturityEvaluation;
  question: Question;
  taskChanges: TaskChange[];
  taskCost: number;
  taskExecutor: Employment[];
  taskName: string;
  taskNumber: number;
  taskPriority: TaskPriority;
  taskState: TaskState;
}

export interface TaskChange extends  MscBaseEntity{
  author: Employment;
}

export interface TaskCommentChange extends  TaskChange{
  content: string;
}

export interface TaskStateChange extends  TaskChange{
  state: TaskState;
}

export interface TermDefinition extends  MscBaseEntity{
  definition: string;
  languageID: string;
  term: string;
}

export interface TranslationModule extends  MscBaseEntity{
  name: string;
  staticTranslations: StaticTranslation[];
}

export interface UserAvatar extends  Attachment{
  person: Person;
}

export interface UserQuestionaryAnswers extends  MscBaseEntity{
  employment: Employment;
  isCompleted: boolean;
  questionAnswers: QuestionAnswer[];
  questionary: Questionary;
}

export interface WhiteLabelParam{
  primaryColor: string;
  secondaryColor: string;
}

