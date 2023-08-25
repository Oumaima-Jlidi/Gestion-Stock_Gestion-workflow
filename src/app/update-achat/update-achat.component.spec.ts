import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAchatComponent } from './update-achat.component';

describe('UpdateAchatComponent', () => {
  let component: UpdateAchatComponent;
  let fixture: ComponentFixture<UpdateAchatComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateAchatComponent]
    });
    fixture = TestBed.createComponent(UpdateAchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
