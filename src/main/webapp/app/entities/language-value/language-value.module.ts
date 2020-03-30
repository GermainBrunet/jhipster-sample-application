import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { LanguageValueComponent } from './language-value.component';
import { LanguageValueDetailComponent } from './language-value-detail.component';
import { LanguageValueUpdateComponent } from './language-value-update.component';
import { LanguageValueDeleteDialogComponent } from './language-value-delete-dialog.component';
import { languageValueRoute } from './language-value.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(languageValueRoute)],
  declarations: [LanguageValueComponent, LanguageValueDetailComponent, LanguageValueUpdateComponent, LanguageValueDeleteDialogComponent],
  entryComponents: [LanguageValueDeleteDialogComponent]
})
export class JhipsterSampleApplicationLanguageValueModule {}
