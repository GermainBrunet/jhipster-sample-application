import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LanguageValueDetailComponent } from 'app/entities/language-value/language-value-detail.component';
import { LanguageValue } from 'app/shared/model/language-value.model';

describe('Component Tests', () => {
  describe('LanguageValue Management Detail Component', () => {
    let comp: LanguageValueDetailComponent;
    let fixture: ComponentFixture<LanguageValueDetailComponent>;
    const route = ({ data: of({ languageValue: new LanguageValue(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [LanguageValueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LanguageValueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LanguageValueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load languageValue on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.languageValue).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
