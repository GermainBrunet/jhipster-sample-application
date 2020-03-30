import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'label',
        loadChildren: () => import('./label/label.module').then(m => m.JhipsterSampleApplicationLabelModule)
      },
      {
        path: 'label-value',
        loadChildren: () => import('./label-value/label-value.module').then(m => m.JhipsterSampleApplicationLabelValueModule)
      },
      {
        path: 'keyword',
        loadChildren: () => import('./keyword/keyword.module').then(m => m.JhipsterSampleApplicationKeywordModule)
      },
      {
        path: 'level',
        loadChildren: () => import('./level/level.module').then(m => m.JhipsterSampleApplicationLevelModule)
      },
      {
        path: 'exercise-group',
        loadChildren: () => import('./exercise-group/exercise-group.module').then(m => m.JhipsterSampleApplicationExerciseGroupModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterSampleApplicationEntityModule {}
