import { Component, OnInit } from '@angular/core';
import { QuesionaryCtrl } from './msc-controllers';
import { Questionary } from './msc-model';
import { QuestionaryDto } from './model-dto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'msc-ui';
  questionaryList: Questionary[] = [];
  questionaryDto: QuestionaryDto[] = [];

  constructor(private quesionaryCtrl: QuesionaryCtrl) {
  }

  ngOnInit(): void {
  }

  start(): void {
    this.quesionaryCtrl.getQuestionaries(20).subscribe( (questionary: Questionary[]) => {
      this.questionaryList = questionary;
    });

    this.quesionaryCtrl.getQuestionariesDTO(20).subscribe( (questionaryDTO: QuestionaryDto[]) => {
      this.questionaryDto = questionaryDTO;
    });
  }
}
