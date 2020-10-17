import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsPublicComponent } from './news-public.component';

describe('NewsPublicComponent', () => {
  let component: NewsPublicComponent;
  let fixture: ComponentFixture<NewsPublicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsPublicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsPublicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
