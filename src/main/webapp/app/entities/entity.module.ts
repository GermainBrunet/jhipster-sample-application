import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'language-code',
        loadChildren: () => import('./language-code/language-code.module').then(m => m.JhipsterSampleApplicationLanguageCodeModule)
      },
      {
        path: 'language-value',
        loadChildren: () => import('./language-value/language-value.module').then(m => m.JhipsterSampleApplicationLanguageValueModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterSampleApplicationEntityModule {}
