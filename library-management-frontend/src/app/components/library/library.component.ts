import {Component, Input, OnInit} from '@angular/core';
import {LibraryService} from "../../services/library.service";
// @ts-ignore
import {v4 as uuidv4} from 'uuid';
import {BookModel} from "../../models/bookModel";

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {

  remainingBooks: BookModel[] | null = [];
  borrowedBooks: BookModel[] | null = [];

  constructor(private libraryService: LibraryService) {
  }

  ngOnInit(): void {

  }
}
