import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ExerciseGroupDetailComponent } from 'app/entities/exercise-group/exercise-group-detail.component';
import { ExerciseGroup } from 'app/shared/model/exercise-group.model';

describe('Component Tests', () => {
  describe('ExerciseGroup Management Detail Component', () => {
    let comp: ExerciseGroupDetailComponent;
    let fixture: ComponentFixture<ExerciseGroupDetailComponent>;
    const route = ({ data: of({ exerciseGroup: new ExerciseGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ExerciseGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExerciseGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExerciseGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load exerciseGroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.exerciseGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
