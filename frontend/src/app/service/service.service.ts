import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { baseApiUrl } from '../constant/constant';
import { Response } from '../response/respnse';
import { PageRequest } from '../constant/PageRequest';
import { Page } from '../constant/Page';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http: HttpClient) { }

  save(data: any, model: string): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${baseApiUrl}/${model}/save`, data);
  }

  delete(id: any, model: string): Observable<Response<any>> {
    return this.http.delete<Response<any>>(`${baseApiUrl}/${model}/delete/${id}`);
  }

  findById(id: any, model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${baseApiUrl}/${model}/findById/${id}`);
  }


  findAll(model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${baseApiUrl}/${model}/findAll`);
  }


  login(data: any): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${baseApiUrl}/user/login`, data);
  }

  findpage(model:string,pagerequest:PageRequest):Observable<Response<Page<any>>>{
    return this.http.get<Response<Page<any>>>(`${baseApiUrl}/${model}/${pagerequest.getRequestPath()}`)
  }
}
