import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { CategoryColorService } from '../category-color.service';
import { CategoryColor } from '../../../../entity/category-color.model';

@Component({
    selector: 'app-list-category-color',
    templateUrl: './list-category-color.component.html',
    styleUrls: ['./list-category-color.component.scss']
})
export class ListCategoryColorComponent implements OnInit, AfterViewInit {

    idDelete: number;
    searchBox: FormGroup;
    @ViewChild(DataTableDirective)
    dtElement: DataTableDirective;
    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<any> = new Subject();

    constructor(
        private _toastr: ToastrService,
        private _router: Router,
        private _formBuilder: FormBuilder,
        private _service: CategoryColorService) {
    }

    ngOnInit() {
        const that = this;
        this.searchBox = new FormGroup(
            {
                'name': this._formBuilder.control('')
            }
        );

        this.dtOptions = {
            pagingType: 'full_numbers',
            serverSide: true,
            searching: false,
            processing: true,
            ajax: (dataTablesParameters: any, callback) => {
                const value: CategoryColor = this.searchBox.value;
                that._service.datatables(value, dataTablesParameters).subscribe(resp => {
                    callback({
                        recordsTotal: resp.recordsTotal,
                        recordsFiltered: resp.recordsFiltered,
                        data: resp.data
                    });
                }, error => {
                    this._toastr.error('Can\' recived the data', 'List category of color');
                    callback({
                        recordsTotal: 0,
                        recordsFiltered: 0,
                        data: []
                    });
                });
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'name', title: 'Name' },
                { data: 'code', title: 'Code' },
                { data: 'description', title: 'Description' },
                {
                    data: 'id',
                    title: 'Action',
                    orderable: false,
                    render: (data: any, type: any, row: any, meta) => {
                        return `<button id="action-update" title="Edit Type" class="btn btn-link">
                        <span class="fa actionMaster fa-edit"/></button>
                                <button id="action-remove" title="Delete Type" class="btn btn-link">
                                <span class="fa actionMaster fa-trash"/></button>
                                `;
                    }
                }
            ],
            rowCallback: (row: Node, data: CategoryColor, index: number) => {
                $('button#anction-update', row).click(() => {
                    this._router.navigate(['master', 'category-color', data.id]);
                });
                $('button#action-remove', row).click(() => {
                    this.idDelete = data.id;
                    document.getElementById('openModal').click();
                });
                return row;
            }
        };
    }
    refresh(data): void {
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
            this.dtTrigger.next();
        });
    }

    removed(): void {
        this._service.remove(this.idDelete).subscribe(data => {
            if (data.status === 200) {
                this._toastr.warning('Category of Color Removed', 'Remove Success',
                    {
                        timeOut: 4000
                    });
                this.refresh(null);
            }
        }, error => {
            this._toastr.warning('Cant Delete Category of color', 'Oppss...',
                {
                    timeOut: 4000
                });
            console.error(error);
        });
    }

    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }


}

