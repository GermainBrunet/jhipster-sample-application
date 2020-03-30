import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { LanguageCodeComponent } from './language-code.component';
import { LanguageCodeDetailComponent } from './language-code-detail.component';
import { LanguageCodeUpdateComponent } from './language-code-update.component';
import { LanguageCodeDeleteDialogComponent } from './language-code-delete-dialog.component';
import { languageCodeRoute } from './language-code.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(languageCodeRoute)],
  declarations: [LanguageCodeComponent, LanguageCodeDetailComponent, LanguageCodeUpdateComponent, LanguageCodeDeleteDialogComponent],
  entryComponents: [LanguageCodeDeleteDialogComponent]
})
export class JhipsterSampleApplicationLanguageCodeModule {}
