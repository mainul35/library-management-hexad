import {BookModel} from "./bookModel";

export class LibraryStatus {
  borrowedBooks : BookModel[] = [];
  remainingBooks : BookModel[] = [];
}
