import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LanguageValueUpdateComponent } from 'app/entities/language-value/language-value-update.component';
import { LanguageValueService } from 'app/entities/language-value/language-value.service';
import { LanguageValue } from 'app/shared/model/language-value.model';

describe('Component Tests', () => {
  describe('LanguageValue Management Update Component', () => {
    let comp: LanguageValueUpdateComponent;
    let fixture: ComponentFixture<LanguageValueUpdateComponent>;
    let service: LanguageValueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [LanguageValueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LanguageValueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LanguageValueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LanguageValueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LanguageValue(123);
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
        const entity = new LanguageValue();
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
