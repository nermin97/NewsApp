import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {NewsService} from './service/news.service';
import {AuthServiceService} from '../auth/service/auth-service.service';
import {PageEvent} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {NewsDialogComponent} from '../news-dialog/news-dialog.component';
import {defaultSearchParam} from '../../environments/environment';
import {MatSnackBar} from '@angular/material/snack-bar';


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
  public startIndex = 0;
  public endIndex = 6;

  constructor(public newsService: NewsService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar,
              public authService: AuthServiceService,
              private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.onResize();
    this.getNews();
  }

  getNews() {
    return this.newsService.searchNews(this.searchParam).subscribe(
      data => {
        this.newsList = data;
        this.pageSlice = this.newsList.slice(0, (this.newsList.length < 6)? this.newsList.length : 6);
        this.changeDetector.detectChanges();
      }
    );
  }

  editNews(news) {
    const dialogRef = this.dialog.open(NewsDialogComponent, {
      width: '600px',
      height: '450px',
      data: {
        news: Object.assign({}, news),
        existing: true
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (!result) return;
      console.log('The dialog was closed');
      console.log(result);
      if (result.title === null || result.title === "") {
        openSnackBar(this.snackBar, 'Cannot be an empty field!', 'Title');
        return;
      }
      this.newsService.update(news.id, result).subscribe(result => {
        this.getNews();
      });
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
      if (!result) return;
      console.log('The dialog was closed');
      console.log(result);
      if (result.title === null || result.title === "") {
        openSnackBar(this.snackBar, 'Cannot be an empty field!', 'Title');
        return;
      }
      this.newsService.save(result).subscribe(result => {
        this.getNews();
      });
    });
  }

  searchClicked() {
    if (this.changingSearchParam == null || this.changingSearchParam == '') {
      this.searchParam = `${defaultSearchParam}`;
    } else {
      this.searchParam = this.changingSearchParam;
    }
    console.log(this.searchParam);
    this.startIndex = 0;
    this.endIndex = 6;
    this.getNews();
  }

  onPageChange(event: PageEvent) {
    console.log(event);
    const start = event.pageIndex * event.pageSize;
    let end = start + event.pageSize;
    if(end > this.newsList.length)  {
      end = this.newsList.length;
    }
    this.pageSlice = this.newsList.slice(start, end);
  }

  onResize() {
    this.breakpoint = (window.innerWidth <= 1120) ? 1 : (window.innerWidth >= 1650) ? 3 :  2;
    this.rowHeight = (this.breakpoint == 1) ? '1:0.6' : (this.breakpoint == 2) ? '1:0.7' : '1:0.65';
    console.log(`breakpoint: ${this.breakpoint}, rowHeight: ${this.rowHeight}`);
  }

}

export const openSnackBar = (snackBar: MatSnackBar, message, action) => {
  snackBar.open(message, action, {
    duration: 2000,
  });
}

