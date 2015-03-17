# JBookShelf 0.4b2 #

[Download for Linux](http://jbookshelf.googlecode.com/files/jbookshelf-linux-0.4b2.zip)
[Download for Windows](http://jbookshelf.googlecode.com/files/jbookshelf-windows-0.4b2.zip)

## Changes ##

  * Fixed "Googling the multiline paragraphs may cause an error"
  * "Tempdir" setting removed as obsolete
  * Some code reorgainizing

## Known issues ##

  * Opening the file zipped with a nonsystem encoding may cause an error
  * Other [issues](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.4b1 #

[Download for Linux](http://jbookshelf.googlecode.com/files/jbookshelf-linux-0.4b1.zip)
[Download for Windows](http://jbookshelf.googlecode.com/files/jbookshelf-windows-0.4b1.zip)

## Changes ##

### Reader ###

  * Reader remembers last text position
  * Reader allows creation and view of citations
  * User is able to choose how to view each book: using internal or system deafult viewer. By default it is chosen automatically.
  * User is able to choose the book encoding in case it is detected wrong

### Collection ###

  * Category can be added as child to another category by dragging its node in the collection tree
  * Double click on the related unit node selects it in the collection tree
  * Book edition dialog tries to guess book attributes after book file changing (first import mask is used)
  * Collection tree sorting enabled
  * Collection tree allows to rename authors and categories
  * Book, author, category name and selected the text can be googled
  * Additional info is displayed in the collection tree


## Known issues ##

  * Opening the file zipped with a nonsystem encoding may cause an error
  * Googling the multiline paragraphs may cause an error
  * Other [issues](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.3b2 #

[Download for Linux](http://jbookshelf.googlecode.com/files/jbookshelf-linux-0.3b2.zip)
[Download for Windows](http://jbookshelf.googlecode.com/files/jbookshelf-windows-0.3b2.zip)

## Changes ##

  * Simple reader dialog introduced
  * Collection tree allows multiselection
  * Comment textarea allows "undo" action
  * Import visualization optimized
  * File path textfields remember last selected file location
  * Fixed bugs
    * (partly) http://code.google.com/p/jbookshelf/issues/detail?id=16
    * http://code.google.com/p/jbookshelf/issues/detail?id=50
    * http://code.google.com/p/jbookshelf/issues/detail?id=52

## Known issues ##

  * Opening the file zipped with a nonsystem encoding may cause an error
  * Other [issues](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.3b1 #

[Download for Linux](http://jbookshelf.googlecode.com/files/jbookshelf-linux-0.3b1.zip)
[Download for Windows](http://jbookshelf.googlecode.com/files/jbookshelf-windows-0.3b1.zip)

## Changes ##

  * Collection trees have context menus
  * Autocomplete in the fileds of Author and Category (based on existing collection)
  * Autocomplete in the Comment textarea (based on dictionary)
  * Import dialog displays the process of book import

## Known issues ##

  * [issues](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.2b3 #

## Changes ##

  * Translated into Russian
  * Fixed some bugs

## Known issues ##

  * [issues](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.2b2 #

## Changes ##

  * Reduced distro size
  * Fixed some bugs

## Known issues ##

  * Russian translation is temporary unavailable
  * [other](http://code.google.com/p/jbookshelf/issues/list)

---


# JBookShelf 0.2b1 #

## Changes ##

  * **Ported under [qt-jambi](http://en.wikipedia.org/wiki/Qt/Jambi)**

## Known issues ##

  * Russian translation is temporary unavailable
  * Some bugs with displaying tree views and dialogs
  * [other](http://code.google.com/p/jbookshelf/issues/list)


---


# JBookShelf 0.1b2 #

[Download](http://jbookshelf.googlecode.com/files/jbookshelf-0.1b2.zip)

[See screenshots](http://code.google.com/p/jbookshelf/wiki/Screenshots)

## Changes ##

  * fixed some bugs
    * [Incorrect unit edition](http://code.google.com/p/jbookshelf/issues/detail?id=15)
    * [Can't find resource for bundle](http://code.google.com/p/jbookshelf/issues/detail?id=14)
  * code and resourses reordered


---


# JBookShelf 0.1b1 #

## Supported Features ##

  1. work with book collection: add, remove, edit book
  1. search books, authors, categories
  1. import files using a simple mask
  1. view book with a system deafult browser
  1. backup/restore collection
  1. add comments to books, authors, categories
  1. link books, authors, categories as related

## Book Types ##

  1. single file
  1. single file folder
  1. index file folder
  1. zip-file

## Languages ##

  * Russian
  * English

## Known Issues ##

  1. ["view" crashes on some zipped books due to encoding problem](http://code.google.com/p/jbookshelf/issues/detail?id=16)
  1. "view" crashes sometimes under Windows XP due to path problem (needs testing)
  1. [import treats only filenames that strictly satisfy mask](http://code.google.com/p/jbookshelf/issues/detail?id=17)
  1. some ui messages weren't localized
  1. [most exceptions are not reported while working with ui](http://code.google.com/p/jbookshelf/issues/detail?id=18)