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
    // this.quesionaryCtrl.getQuestionaries(20).subscribe( (questionary: Questionary[]) => {
    //   this.questionary = questionary;
    // });

    // this.quesionaryCtrl.getQuestionariesDto(20).subscribe( (questionaryDto: QuestionaryDto[]) => {
    //   this.questionaryDto = questionaryDto;
    // });
  }

  start(): void {
    this.quesionaryCtrl.getQuestionaries(20).subscribe( (questionary: Questionary[]) => {
      this.questionaryList = questionary;
    });
  }
}
