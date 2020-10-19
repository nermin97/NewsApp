import {ChangeDetectorRef, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {defaultSearchParam} from '../../environments/environment';
import {NewsService} from '../news-public/service/news.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {NewsDialogData} from '../news-dialog/news-dialog.component';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  displayedColumns: string[] = ['id', 'title', 'description', 'creationDate', 'createdBy', 'editedBy'];
  public dataSource = null;
  private searchParam = `${defaultSearchParam}`;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private newsService: NewsService,
              public dialog: MatDialog,
              private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getNews()
  }

  getNews() {
    this.newsService.getAdmin(this.searchParam).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      if (this.dataSource.paginator) {
        this.dataSource.paginator.firstPage();
      }
      console.log(data);
      this.changeDetector.detectChanges();
    });
  }


  applyFilter(event) {
    const filterValue = (event.target as HTMLInputElement).value;
    if (!filterValue) {
      this.searchParam = `${defaultSearchParam}`;
    } else {
      this.searchParam = filterValue.trim().toLowerCase();
    }
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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


