import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupsetupComponent } from './groupsetup.component';

describe('GroupsetupComponent', () => {
  let component: GroupsetupComponent;
  let fixture: ComponentFixture<GroupsetupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroupsetupComponent]
    });
    fixture = TestBed.createComponent(GroupsetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
