import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule, routingComponents} from './app-routing/app-routing.module';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthServiceService} from './auth/service/auth-service.service';
import {AuthGuard} from './auth/guard/auth.guard';
import {TokenInterceptorService} from './auth/interceptor/token-interceptor.service';
import {MatMenuModule} from '@angular/material/menu';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NewsService} from './news-public/service/news.service';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import { NewsDialogComponent } from './news-dialog/news-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    NewsDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatIconModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatPaginatorModule,
    MatDialogModule,
    FormsModule,
  ],
  providers: [
    AuthServiceService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    NewsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
