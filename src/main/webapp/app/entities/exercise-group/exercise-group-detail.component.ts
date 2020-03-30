import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExerciseGroup } from 'app/shared/model/exercise-group.model';

@Component({
  selector: 'jhi-exercise-group-detail',
  templateUrl: './exercise-group-detail.component.html'
})
export class ExerciseGroupDetailComponent implements OnInit {
  exerciseGroup: IExerciseGroup | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ exerciseGroup }) => (this.exerciseGroup = exerciseGroup));
  }

  previousState(): void {
    window.history.back();
  }
}
