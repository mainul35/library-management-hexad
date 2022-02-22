import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-research-frequency',
  templateUrl: './research.component.html',
  styleUrls: ['./research.component.css']
})
export class ResearchComponent implements OnInit {
  givenWord ?: string;
  notebookEntries ?: string;
  frequencies: number = 0;
  similarWords = [];

  constructor() { }

  ngOnInit(): void {
  }
}
