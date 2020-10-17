import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthServiceService} from '../auth/service/auth-service.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {News} from '../news-public/news-public.component';
import {NewsService} from '../news-public/service/news.service';
import {Observable} from 'rxjs';


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

  constructor(public dialogRef: MatDialogRef<NewsDialogComponent>,
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
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
