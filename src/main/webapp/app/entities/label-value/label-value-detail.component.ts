import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILabelValue } from 'app/shared/model/label-value.model';

@Component({
  selector: 'jhi-label-value-detail',
  templateUrl: './label-value-detail.component.html'
})
export class LabelValueDetailComponent implements OnInit {
  labelValue: ILabelValue | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ labelValue }) => (this.labelValue = labelValue));
  }

  previousState(): void {
    window.history.back();
  }
}
