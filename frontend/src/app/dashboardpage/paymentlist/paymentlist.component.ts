import { Component, ViewChild, AfterViewInit, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ServiceService } from '../../service/service.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { Page, defaultPage } from '../../constant/Page';
import { PageRequest } from '../../constant/PageRequest';
import { Response } from '../../response/respnse';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-paymentlist',
  standalone: true,
  imports: [
    MatTableModule,
    MatButtonModule,
    MatToolbarModule,
    RouterModule,
    HttpClientModule,
    MatPaginatorModule,
    MatSortModule,
    FormsModule

  ],
  templateUrl: './paymentlist.component.html',
  styleUrls: ['./paymentlist.component.scss'],
  providers: [ServiceService],
})
export class PaymentlistComponent {
  filterText?: string;
  timer: any = null;


  page: Page<any> = defaultPage;
  displayedColumns: string[] = ['email', 'amount', 'paymenttype', 'cardNumber', 'number', 'transationid'];
  dataSource = new MatTableDataSource<any>([]);

  constructor(private service: ServiceService) { }



  ngOnInit(): void {
    const parms = new PageRequest(0, 10);
    this.service.findpage("payment", parms).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data
    })
  }

  pageChange($event: PageEvent) {
    const parms = new PageRequest($event.pageIndex, $event.pageSize);
    this.service.findpage("payment", parms).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data
    })
  }

  onSearch() {
    if (this.timer) {
      clearTimeout(this.timer);
    }
    this.timer = setTimeout(() => {
      const param = new PageRequest(0, 10);
      param.sortColumn = this.sort.active;
      param.order = this.sort.direction;
      param.search = this.filterText;
      this.service.findpage("student", param).subscribe((data: Response<Page<any>>) => {
        this.dataSource.data = data.data.content;
        this.page = data.data
      })
    }, 1000);
  }

  @ViewChild(MatSort) sort!: MatSort;
  onSortChange($event: any) {
      const params=new PageRequest($event.pageIndex, $event.pageSize);
      params.sortColumn=this.sort.active;
      params.order=$event.direction;
      this.service.findpage("payment", params).subscribe((data: Response<Page<any>>) => {
        this.dataSource.data = data.data.content;
        this.page = data.data
      })
  }

  


}
