import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ServiceService } from '../service/service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ResponseStatus } from '../response/respnse';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, HttpClientModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
  providers: [ServiceService],
})
export class FormComponent {

  constructor(private service: ServiceService, private snackBar: MatSnackBar, private router: Router) { }

  // @Input() isVisible = false;
  @Input() courseAmount = 0;  // Receive course amount from parent component
  @Output() formCancelled = new EventEmitter<void>();




  formdata: any = {
    email: '',
    transationid: '',
    number: '',
    amount: '',
    paymenttype: '',
    cardNumber:'',
  }


  ngOnInit() {
    const course=JSON.parse(sessionStorage.getItem('coursedata')!);
    this.formdata.amount=Number(course.pricing);
  }

  onPaymentCategoryChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.formdata.paymentCategory = selectElement.value;
  }

  submitPayment(form: NgForm) {
    const data = form.value;
    console.log(data)
    this.service.save(data, "payment").subscribe((data) => {
      this.snackBar.open(data.message, 'Close', {
        duration: 3000
      });
      if (data.status == ResponseStatus.SUCCESS) {
        this.router.navigate(['/dashboard']);
      }
    });


  }

  cancelPayment() {
    this.formCancelled.emit();
  }

}
