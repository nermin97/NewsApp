import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {defaultSearchParam} from '../../environments/environment';
import {NewsService} from '../news-public/service/news.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {NewsDialogData} from '../news-dialog/news-dialog.component';
import {MatSort} from '@angular/material/sort';
import {News} from '../news-public/news-public.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'title', 'description', 'creationDate', 'createdBy', 'editedBy'];
  public dataSource = null;
  private searchParam = `${defaultSearchParam}`;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private newsService: NewsService,
              public dialog: MatDialog) { }


  ngOnInit(): void {
    this.getNews()
    // this.data = [];
    // for (let i = 0; i < 10; i++) {
    //   this.data.push(
    //     {
    //       id: i,
    //       title: `Title ${i}`,
    //       description: 'Although comments in HTML markup usually don\'t play an important role (they are comments after all), they could have a meaning for parsers which post-process the HTML document. When I recently encountered such a requirement, it turned out that generating custom HTML comments with an Angular application is not as easy as one might expect',
    //       creationDate: `0${i}.10.1997`,
    //       createdBy: `administrator${i}@gmail.com`,
    //       editedBy: `administrator${i}@mail.com`,
    //     }
    //   );
    // }
    // this.dataSource = new MatTableDataSource(this.data);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getNews() {
    this.newsService.getAdmin(this.searchParam).subscribe(this.handleNewsSearchResult);
  }

  handleNewsSearchResult(data: News[]) {
    this.dataSource = new MatTableDataSource(data);
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  applyFilter(event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.searchParam = filterValue.trim().toLowerCase();
    this.getNews();
  }

  openDescriptionDialog(description) {
    this.dialog.open(DescriptionDialog, {
      width: '600px',
      height: '400px',
      data: description
    });
  }
}

@Component({
  selector: 'description-dialog',
  templateUrl: './description-dialog.html',
  styleUrls: ['./dashboard.component.css']
})
export class DescriptionDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: NewsDialogData) {}
}


