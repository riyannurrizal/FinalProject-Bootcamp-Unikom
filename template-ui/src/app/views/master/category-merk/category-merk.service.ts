import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MerkCategory } from '../../../entity/merk-category';
import { DatatablesModelResponse } from '../../../_model/datatables';
import { environment } from '../../../../environments/environment';

@Injectable()
export class CategoryMerkService {

  constructor(private _http: HttpClient) { }


  public datatables(value: MerkCategory, datatablesParameters: any) {
    let params = new HttpParams();
    params = params.append('start', datatablesParameters.start);
    params = params.append('length', datatablesParameters.length);
    params = params.append('draw', datatablesParameters.draw);
    params = params.append('order[0][column]', datatablesParameters.order[0]['column']);
    params = params.append('order[0][dir]', datatablesParameters.order[0]['dir']);

    return this._http
      .post<DatatablesModelResponse>(
        `${environment.supportDeviceApi}/master/category-merk/datatables`,
        value, { params: params }
      );
  }

  public save(value: MerkCategory) {
    return this._http.post(`${environment.supportDeviceApi}/master/category-merk/`, value);
  }

}
