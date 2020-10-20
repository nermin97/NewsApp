import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthServiceService} from '../service/auth-service.service';
import {baseUrl} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private injector: Injector) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // if (req.url === (`${baseUrl}auth/login`) || req.url === (`${baseUrl}news/public`)) {
    //   next.handle(req);
    // }
    let authService = this.injector.get(AuthServiceService);
    let tokenizedReq = req.clone({
      setHeaders:{
        Authorization: 'Bearer ' + authService.getToken()
      }
    });
    return next.handle(tokenizedReq);
  }
}
