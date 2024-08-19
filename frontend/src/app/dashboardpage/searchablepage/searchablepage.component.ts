import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Observable, map, startWith } from 'rxjs';
import { ServiceService } from '../../service/service.service';
import { IDropdownProps } from './searchpage.model';


@Component({
  selector: 'app-searchablepage',
  standalone: true,
  imports: [
    MatSelectModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    MatIconModule,
    MatNativeDateModule
  ],
  templateUrl: './searchablepage.component.html',
  styleUrls: ['./searchablepage.component.scss'],

})
export class SearchablepageComponent {

  @ViewChild('search') searchTextBox!: ElementRef;

  selectFormControl = new FormControl();
  searchTextboxControl = new FormControl();
  @Input()
  data: any[] = [];
  @Input()
  props: IDropdownProps = { key: '', text: '', value: '' };
  @Output() selectedValuesChange = new EventEmitter<any[]>();
  private _selectedValues: any[] = [];
  @Input()
  get selectedValues(): any[] {
    return this._selectedValues;
  }

  set selectedValues(val: any[]) {
    this._selectedValues = val;
  }


  filteredOptions: Observable<any[]> = new Observable<any[]>();
  ngAfterViewInit() {
    setTimeout(() => {
      this.filteredOptions = this.searchTextboxControl.valueChanges
        .pipe(
          startWith<string>(''),
          map(name => this._filter(name))
        );
    }, 1000);
  }



  private _filter(name: string): any[] {
    const filterValue = name.toLowerCase();

    let filteredList: any[] = [];
    if (this.data && this.data.length > 0) {
      filteredList = this.data.filter(item => {
        return item.question.toLowerCase().includes(filterValue) ||
          item.options.some((option: string) => option.toLowerCase().indexOf(filterValue) !== -1) ||
          item.answer.toLowerCase().includes(filterValue);
      });
    }
    return filteredList;
  }

  selectionChange(event: any) {
    if (event.isUserInput) {
      if (event.source.selected) {
        this.selectedValues.push(event.source.value);
      } else {
        this.selectedValues = this.selectedValues.filter(value => value !== event.source.value);
      }
    }
    this.selectedValuesChange.emit(this._selectedValues);
  }

  openedChange(e: any) {
    this.searchTextboxControl.patchValue('');
    if (e == true) {
      this.searchTextBox.nativeElement.focus();
    }
  }

  clearSearch(event: any) {
    event.stopPropagation();
    this.searchTextboxControl.patchValue('');
  }

  setSelectedValues() {
    if (this.selectFormControl.value && this.selectFormControl.value.length > 0) {
      this.selectedValues = this.selectFormControl.value;
    }
  }

  getSelectedQuestions(): string {
    return this.selectFormControl.value
      ? this.selectFormControl.value.map((v: any) => v.question).join(', ')
      : '';
  }
}
