import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Questionary } from './msc-model';
import { QuestionaryDto } from './model-dto';

@Injectable()
export class QuesionaryCtrl {
  httpService: HttpClient;

  public constructor(httpService: HttpClient) {
    this.httpService = httpService;
  }

  public getQuestionaries(rowNumbers: number | null): Observable<Questionary[]> {

    return this.httpService.get<Questionary[]>('/api/papa?rowNumbers=' + rowNumbers + '');
  }

  public getQuestionariesDTO(rowNumbers: number | null): Observable<QuestionaryDto[]> {

    return this.httpService.get<QuestionaryDto[]>('/api/papaDTO?rowNumbers=' + rowNumbers + '');
  }

}
