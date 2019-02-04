import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryColorService } from '../category-color.service';
import { CategoryColor } from '../../../../entity/category-color.model';

@Component({
    selector: 'app-new-category-color',
    templateUrl: './new-category-color.component.html',
    styleUrls: ['./new-category-color.component.scss']
})
export class NewCategoryColorComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    constructor(
        private _router: Router,
        private _service: CategoryColorService,
        private _toastr: ToastrService,
        private _formBuilder: FormBuilder) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            'name': this._formBuilder.control(''),
            'code': this._formBuilder.control(''),
            'description': this._formBuilder.control('')
        });
    }

    send(data): void {
        this.submitted = true;
        if (this.form.invalid) {
            this._toastr.warning('Field Not Allowed Empty', 'Can\'t Save Change Type',
                {
                    timeOut: 5000
                });
            return;
        }

        const value: CategoryColor = this.form.value;
        this._service.save(value).subscribe(resp => {
            this._toastr.info('New Category of Color Saved', 'Save Success');
            this._router.navigate(['master', 'category-color']);
        }, error => {
            this._toastr.warning('There is something error', 'Oopss....');
        });
    }

    get f() {
        return this.form.controls;
    }
}