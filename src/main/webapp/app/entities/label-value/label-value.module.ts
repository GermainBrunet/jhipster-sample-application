import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { LabelValueComponent } from './label-value.component';
import { LabelValueDetailComponent } from './label-value-detail.component';
import { LabelValueUpdateComponent } from './label-value-update.component';
import { LabelValueDeleteDialogComponent } from './label-value-delete-dialog.component';
import { labelValueRoute } from './label-value.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(labelValueRoute)],
  declarations: [LabelValueComponent, LabelValueDetailComponent, LabelValueUpdateComponent, LabelValueDeleteDialogComponent],
  entryComponents: [LabelValueDeleteDialogComponent]
})
export class JhipsterSampleApplicationLabelValueModule {}
