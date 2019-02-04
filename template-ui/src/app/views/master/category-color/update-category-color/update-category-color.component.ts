import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryColorService } from '../category-color.service';
import { CategoryColor } from '../../../../entity/category-color.model';

@Component({
    selector: 'app-update-category-color',
    templateUrl: './update-category-color.component.html',
    styleUrls: ['./update-category-color.component.scss']
})
export class UpdateCategoryColorComponent implements OnInit, OnDestroy {

    private subcribeTopic: Subscription;
    form: FormGroup;
    submitted = false;

    constructor(
        private _router: Router,
        private _service: CategoryColorService,
        private _activeRoute: ActivatedRoute,
        private _toastr: ToastrService,
        private _formBuilder: FormBuilder) {
    }

    ngOnInit() {
        this.form = new FormGroup({
            id: this._formBuilder.control('', Validators.required),
            name: this._formBuilder.control('', Validators.required),
            code: this._formBuilder.control('', Validators.required),
            description: this._formBuilder.control('')
        });

        this.subcribeTopic = this._activeRoute.params.subscribe((params: Params) => {
            const id = params['id'];
            this._service.getChangeTypes(id).subscribe((response: any) => {
                if (response.status === 204) {
                    this._router.navigate(['master', 'category-color']);
                } else {
                    const value: CategoryColor = response.body;
                    console.log(value);
                    this.form.setValue({
                        'id': value.id,
                        'name': value.name,
                        'code': value.code,
                        'description': value.description
                    }
                    );
                }
            });
        });
    }

    send(data): void {
        this.submitted = true;
        if (this.form.invalid) {
            this._toastr.warning('Field Not Allowed Empty', 'Can\'t Update Change Type',
                {
                    timeOut: 5000
                });
            return;
        }
        const value: CategoryColor = this.form.value;
        this._service.update(value).subscribe(resp => {
            this._toastr.info('Change a category of color', 'Update Success');
            this._router.navigate(['master', 'category-color']);
        }, error => {
            this._toastr.warning('Cant Update a category of color', 'Oppss...');
            console.error('Cant\'t be saved');
        });
    }

    get f() {
        return this.form.controls;
    }

    ngOnDestroy(): void {
        this.subcribeTopic.unsubscribe();
    }

}