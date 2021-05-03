import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QuesionaryCtrl } from './msc-controllers';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RequestTimestampService } from './requestTimestampService';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    QuesionaryCtrl,
    { provide: HTTP_INTERCEPTORS, useClass: RequestTimestampService, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
