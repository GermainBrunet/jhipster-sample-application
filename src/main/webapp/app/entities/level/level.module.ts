import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { LevelComponent } from './level.component';
import { LevelDetailComponent } from './level-detail.component';
import { LevelUpdateComponent } from './level-update.component';
import { LevelDeleteDialogComponent } from './level-delete-dialog.component';
import { levelRoute } from './level.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(levelRoute)],
  declarations: [LevelComponent, LevelDetailComponent, LevelUpdateComponent, LevelDeleteDialogComponent],
  entryComponents: [LevelDeleteDialogComponent]
})
export class JhipsterSampleApplicationLevelModule {}
