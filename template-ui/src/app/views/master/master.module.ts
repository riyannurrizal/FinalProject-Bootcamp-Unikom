import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MasterRouting } from './master.routing';
import { ListCategoryDeviceComponent } from './category-device/list-category-device/list-category-device.component';
import { NewCategoryDeviceComponent } from './category-device/new-category-device/new-category-device.component';
import { UpdateCategoryDeviceComponent } from './category-device/update-category-device/update-category-device.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CategoryDeviceService } from './category-device/category-device.service';
import { AuthenticationInterceptor } from '../../_auth/auth.interceptor';
import { DataTablesModule } from 'angular-datatables';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { AlertModule, ModalModule } from 'ngx-bootstrap';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { NewCategoryColorComponent } from './category-color/new-category-color/new-category-color.component';
import { CategoryColorService } from './category-color/category-color.service';
import { ListCategoryColorComponent } from './category-color/list-category-color/list-category-color.component';
import { UpdateCategoryColorComponent } from './category-color/update-category-color/update-category-color.component';
import { ListMerkComponent } from './category-merk/list-merk/list-merk.component';
import { NewMerkComponent } from './category-merk/new-merk/new-merk.component';
import { UpdateMerkComponent } from './category-merk/update-merk/update-merk.component';
import { CategoryMerkService } from './category-merk/category-merk.service';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    DataTablesModule,
    MasterRouting,
    AlertModule.forRoot(),
    Ng4LoadingSpinnerModule.forRoot(),
    ModalModule.forRoot(),
    TranslateModule.forChild({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  declarations: [
    ListCategoryDeviceComponent,
    NewCategoryDeviceComponent,
    UpdateCategoryDeviceComponent,
    ListCategoryColorComponent,
    NewCategoryColorComponent,
    UpdateCategoryColorComponent,
    ListMerkComponent,
    NewMerkComponent,
    UpdateMerkComponent
  ], providers: [
    {
      provide: HTTP_INTERCEPTORS,
      multi: true,
      useClass: AuthenticationInterceptor
    },
    CategoryDeviceService,
    CategoryColorService,
    CategoryMerkService
  ]
})
export class MasterModule {
}
