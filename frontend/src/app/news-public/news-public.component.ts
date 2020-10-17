import { Component, OnInit } from '@angular/core';
import {NewsService} from './service/news.service';
import {AuthServiceService} from '../auth/service/auth-service.service';
import {PageEvent} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {NewsDialogComponent} from '../news-dialog/news-dialog.component';
import {defaultSearchParam} from '../../environments/environment';


export interface News {
  id: number;
  title: string;
  description: string;
  creationDate: string;
  createdBy: string;
  editedBy: string;
}

@Component({
  selector: 'app-news-public',
  templateUrl: './news-public.component.html',
  styleUrls: ['./news-public.component.css']
})
export class NewsPublicComponent implements OnInit {
  public newsList: News[] = [];
  public pageSlice: News[] = [];
  public breakpoint;
  public rowHeight;
  public searchParam: string = `${defaultSearchParam}`;
  public changingSearchParam: string = '';

  constructor(private newsService: NewsService,
              private authService: AuthServiceService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.onResize();
    // this.getNews(this.searchParam);
    for (let i = 0; i < 10; i++) {
      this.newsList.push(
        {
          id: i,
          title: `Title ${i}`,
          description: 'Although comments in HTML markup usually don\'t play an important role (they are comments after all), they could have a meaning for parsers which post-process the HTML document. When I recently encountered such a requirement, it turned out that generating custom HTML comments with an Angular application is not as easy as one might expect',
          creationDate: `${i}.10.1997`,
          createdBy: `administrator${i}@gmail.com`,
          editedBy: `administrator${i}@mail.com`,
        }
      );
    }
    this.pageSlice = this.newsList.slice(0, 6);
  }

  getNews() {
    this.newsService.searchNews(this.searchParam).subscribe(this.handleNewsSearchResult)
  }

  handleNewsSearchResult(data) {
    this.newsList = data;
  }

  editNews(news) {
    const dialogRef = this.dialog.open(NewsDialogComponent, {
      width: '600px',
      height: '450px',
      data: {
        news: news,
        existing: true
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      // this.newsService.update(result.news).subscribe(result => {
      //   this.getNews(this.searchParam);
      // });
    });
  }

  addNews() {
    const dialogRef = this.dialog.open(NewsDialogComponent, {
      width: '600px',
      height: '450px',
      data: {
        news: {
          title: '',
          description: ''
        },
        existing: false
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      // this.newsService.save(result.news, result.news.createdBy).subscribe(result => {
      //   this.getNews(this.searchParam);
      // });
    });
  }

  searchClicked() {
    if (this.changingSearchParam == null || this.changingSearchParam == '') {
      this.searchParam = `${defaultSearchParam}`;
    } else {
      this.searchParam = this.changingSearchParam;
    }
    console.log(this.searchParam);
    //this.getNews();
  }

  onPageChange(event: PageEvent) {
    console.log(event);
    // this.getNews();
    const startIndex = event.pageIndex * event.pageSize;
    let endIndex = startIndex + event.pageSize;
    if(endIndex > this.newsList.length)  {
      endIndex = this.newsList.length;
    }
    this.pageSlice = this.newsList.slice(startIndex, endIndex);
  }

  onResize() {
    this.breakpoint = (window.innerWidth <= 1120) ? 1 : (window.innerWidth >= 1650) ? 3 :  2;
    this.rowHeight = (this.breakpoint == 1) ? '1:0.5' : (this.breakpoint == 2) ? '1:0.7' : '1:0.65';
    console.log(`breakpoint: ${this.breakpoint}, rowHeight: ${this.rowHeight}`);
  }

}
