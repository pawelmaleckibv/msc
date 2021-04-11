import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable()
export class AddressCtrl {
  httpService: HttpClient;


  public constructor(httpService: HttpClient) {
    this.httpService = httpService;
  }
}
