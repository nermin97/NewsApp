import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {baseUrl} from '../../../environments/environment';
import {News} from '../news-public.component';

export interface NewsData {
  title: string;
  description: string;
  createdBy: string;
}

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  searchNews(searchParam: string): Observable<any> {
    return this.http.get(`${baseUrl}news/public/${searchParam}`);
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${baseUrl}news/${id}`);
  }

  getAdmin(searchParam: string): Observable<any> {
    return this.http.get(`${baseUrl}news/admin/${searchParam}`);
  }

  save(news: News, user: string): Observable<any> {
     let data: NewsData = {
      title: news.title,
      description: news.description,
      createdBy: user
    }
    return this.http.post(`${baseUrl}news/`, data);
  }

  update (news: News): Observable<any> {
    let data: NewsData = {
      title: news.title,
      description: news.description,
      createdBy: news.createdBy
    }
    return this.http.put(`${baseUrl}news/${news.id}`, data);
  }
}
