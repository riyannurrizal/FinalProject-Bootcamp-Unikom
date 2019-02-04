import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { CategoryMerkService } from '../category-merk.service';
import { MerkCategory } from '../../../../entity/merk-category';

@Component({
  selector: 'app-list-merk',
  templateUrl: './list-merk.component.html',
  styleUrls: ['./list-merk.component.scss']
})
export class ListMerkComponent implements OnInit, AfterViewInit {

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
    private _service: CategoryMerkService) {
  }

  ngOnInit() {
    const that = this;
    this.searchBox = new FormGroup(
      {
        'name_merk': this._formBuilder.control('')
      }
    );

    this.dtOptions = {
      pagingType: 'full_numbers',
      serverSide: true,
      searching: false,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        const value: MerkCategory = this.searchBox.value;
        that._service.datatables(value, dataTablesParameters).subscribe(resp => {
          callback({
            recordsTotal: resp.recordsTotal,
            recordsFiltered: resp.recordsFiltered,
            data: resp.data
          });
        }, error => {
          this._toastr.error('Can\' recived the data', 'List category of merk');
          callback({
            recordsTotal: 0,
            recordsFiltered: 0,
            data: []
          });
        });
      },
      columns: [
        { data: 'id', title: 'ID' },
        { data: 'name_merk', title: 'Name' },
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
      rowCallback: (row: Node, data: MerkCategory, index: number) => {
        $('button#action-update', row).click(() => {
          this._router.navigate(['master', 'category-merk', data.id]);
        });
      }


    }

  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

}
