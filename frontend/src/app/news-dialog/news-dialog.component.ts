import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {News} from '../news-public/news-public.component';
import {NewsService} from '../news-public/service/news.service';


export interface NewsDialogData {
  news: News;
  exists: boolean;
}

@Component({
  selector: 'app-news-dialog',
  templateUrl: './news-dialog.component.html',
  styleUrls: ['./news-dialog.component.css']
})
export class NewsDialogComponent implements OnInit {
  titleControl;
  descriptionControl

  formGroup: FormGroup;

  constructor(private newsService: NewsService,
              public dialogRef: MatDialogRef<NewsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: NewsDialogData) { }

  ngOnInit(): void {
    this.titleControl = new FormControl('', [
      Validators.required
    ]);
    this.descriptionControl = new FormControl('', []);
  }

  initForm() {
    this.formGroup = new FormGroup({
      title: this.titleControl,
      description: this.descriptionControl
    });
    this.newsService.getById(this.data.news.id)
      .subscribe(
        result => {
          this.data.news = result;
          console.log("Fetched news from server");
        },
        error => {
          alert('Failed to existing News from the server!');
          this.onCancel();
        }
      )
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
