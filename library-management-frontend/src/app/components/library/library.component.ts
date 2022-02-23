import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {
  givenWord ?: string;
  notebookEntries ?: string;
  frequencies: number = 0;
  similarWords = [];

  constructor() { }

  ngOnInit(): void {
  }
}
