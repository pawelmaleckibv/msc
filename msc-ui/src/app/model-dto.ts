
export interface QuestionaryDto {
  questionaryName: string;
  businessUnitName: string[];
  questionDto: QuestionDto[];
}

export interface QuestionDto {
   questionName: string;
   answers: string[];
}
