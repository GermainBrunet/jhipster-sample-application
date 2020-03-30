import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LanguageCodeDetailComponent } from 'app/entities/language-code/language-code-detail.component';
import { LanguageCode } from 'app/shared/model/language-code.model';

describe('Component Tests', () => {
  describe('LanguageCode Management Detail Component', () => {
    let comp: LanguageCodeDetailComponent;
    let fixture: ComponentFixture<LanguageCodeDetailComponent>;
    const route = ({ data: of({ languageCode: new LanguageCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [LanguageCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LanguageCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LanguageCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load languageCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.languageCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
