import { Component, OnInit } from '@angular/core';
import { QuesionaryCtrl } from './msc-controllers';
import { Questionary } from './msc-model';
import { QuestionaryDto } from './model-dto';
import { repeat, tap } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'msc-ui';
  questionaryList: Questionary[] = [];
  questionaryListMulti: Questionary[] = [];
  questionaryDto: QuestionaryDto[] = [];
  questionaryDtoMulti: QuestionaryDto[] = [];

  constructor(private quesionaryCtrl: QuesionaryCtrl) {
  }

  ngOnInit(): void {
  }

  start(): void {
    this.quesionaryCtrl.getQuestionaries('0e6137de-9ea8-11eb-b70d-ffe43392c26c').subscribe( (questionary: Questionary[]) => {
      this.questionaryList = questionary;
    });
  }

  startMulti(): void {
    this.quesionaryCtrl.getQuestionaries('0e6137de-9ea8-11eb-b70d-ffe43392c26c').pipe(
      tap((questionary: Questionary[]) => {
        this.questionaryListMulti = questionary;
      }), repeat(100),
    ).subscribe();
  }

  startDto(): void {
    this.quesionaryCtrl.getQuestionariesDTO('0e6137de-9ea8-11eb-b70d-ffe43392c26c').subscribe( (questionaryDTO: QuestionaryDto[]) => {
      this.questionaryDto = questionaryDTO;
    });
  }

  startDtoMulti(): void {
    this.quesionaryCtrl.getQuestionariesDTO('01c10a9b-c601-4c2a-ae7e-d194055e3bf5').pipe(
      tap((questionaryDTO: QuestionaryDto[]) => {
        this.questionaryDtoMulti = questionaryDTO;
      }), repeat(10),
      tap(() => console.log("wDto"))
    ).subscribe();
  }


}
