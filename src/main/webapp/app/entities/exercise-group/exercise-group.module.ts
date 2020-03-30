import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ExerciseGroupComponent } from './exercise-group.component';
import { ExerciseGroupDetailComponent } from './exercise-group-detail.component';
import { ExerciseGroupUpdateComponent } from './exercise-group-update.component';
import { ExerciseGroupDeleteDialogComponent } from './exercise-group-delete-dialog.component';
import { exerciseGroupRoute } from './exercise-group.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(exerciseGroupRoute)],
  declarations: [ExerciseGroupComponent, ExerciseGroupDetailComponent, ExerciseGroupUpdateComponent, ExerciseGroupDeleteDialogComponent],
  entryComponents: [ExerciseGroupDeleteDialogComponent]
})
export class JhipsterSampleApplicationExerciseGroupModule {}
