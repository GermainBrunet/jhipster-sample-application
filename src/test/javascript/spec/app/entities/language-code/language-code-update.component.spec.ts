import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LanguageCodeUpdateComponent } from 'app/entities/language-code/language-code-update.component';
import { LanguageCodeService } from 'app/entities/language-code/language-code.service';
import { LanguageCode } from 'app/shared/model/language-code.model';

describe('Component Tests', () => {
  describe('LanguageCode Management Update Component', () => {
    let comp: LanguageCodeUpdateComponent;
    let fixture: ComponentFixture<LanguageCodeUpdateComponent>;
    let service: LanguageCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [LanguageCodeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LanguageCodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LanguageCodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LanguageCodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LanguageCode(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new LanguageCode();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
