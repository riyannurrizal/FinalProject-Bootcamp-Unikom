import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListCategoryDeviceComponent } from './category-device/list-category-device/list-category-device.component';
import { NewCategoryDeviceComponent } from './category-device/new-category-device/new-category-device.component';
import { UpdateCategoryDeviceComponent } from './category-device/update-category-device/update-category-device.component';
import { NewCategoryColorComponent } from './category-color/new-category-color/new-category-color.component';
import { ListCategoryColorComponent } from './category-color/list-category-color/list-category-color.component';
import { UpdateCategoryColorComponent } from './category-color/update-category-color/update-category-color.component';
import { ListMerkComponent } from './category-merk/list-merk/list-merk.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Master'
    },
    children: [
      {
        path: 'category-device',
        component: ListCategoryDeviceComponent,
        data: {
          title: 'Category of Devices'
        }
      },
      {
        path: 'category-device/new',
        component: NewCategoryDeviceComponent,
        data: {
          title: 'New category of device'
        }
      },
      {
        path: 'category-device/:id',
        component: UpdateCategoryDeviceComponent,
        data: {
          title: 'Update a category of device'
        }
      },
      {
        path: 'category-color',
        component: ListCategoryColorComponent,
        data: {
          title: 'Category of Color'
        }
      },
      {
        path: 'category-color/new',
        component: NewCategoryColorComponent,
        data: {
          title: 'New a category of Color'
        }
      },
      {
        path: 'category-color/:id',
        component: UpdateCategoryColorComponent,
        data: {
          title: 'Update a category of color'
        }
      },
      {
        path: 'category-merk',
        component: ListMerkComponent,
        data: {
          title: 'Category of Merk'
        }
      },


    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterRouting {
}
