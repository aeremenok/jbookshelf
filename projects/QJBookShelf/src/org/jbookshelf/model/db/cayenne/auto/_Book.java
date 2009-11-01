package org.jbookshelf.model.db.cayenne.auto;

import java.util.Set;

import org.apache.cayenne.CayenneDataObject;
import org.jbookshelf.model.db.cayenne.Author;
import org.jbookshelf.model.db.cayenne.Book;
import org.jbookshelf.model.db.cayenne.Category;
import org.jbookshelf.model.db.cayenne.Note;
import org.jbookshelf.model.db.cayenne.PhysicalBook;

/**
 * Class _Book was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Book extends CayenneDataObject {

    public static final String NAME_PROPERTY = "name";
    public static final String AUTHORS_PROPERTY = "authors";
    public static final String CATEGORIES_PROPERTY = "categories";
    public static final String LAST_READ_PROPERTY = "lastRead";
    public static final String NOTES_PROPERTY = "notes";
    public static final String PHYSICAL_BOOKS_PROPERTY = "physicalBooks";
    public static final String RELATED_BOOKS_PROPERTY = "relatedBooks";

    public static final String ID_PK_COLUMN = "ID";

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void addToAuthors(Author obj) {
        addToManyTarget("authors", obj, true);
    }
    public void removeFromAuthors(Author obj) {
        removeToManyTarget("authors", obj, true);
    }
    @SuppressWarnings("unchecked")
    public Set<Author> getAuthors() {
        return (Set<Author>)readProperty("authors");
    }


    public void addToCategories(Category obj) {
        addToManyTarget("categories", obj, true);
    }
    public void removeFromCategories(Category obj) {
        removeToManyTarget("categories", obj, true);
    }
    @SuppressWarnings("unchecked")
    public Set<Category> getCategories() {
        return (Set<Category>)readProperty("categories");
    }


    public void setLastRead(Note lastRead) {
        setToOneTarget("lastRead", lastRead, true);
    }

    public Note getLastRead() {
        return (Note)readProperty("lastRead");
    }


    public void addToNotes(Note obj) {
        addToManyTarget("notes", obj, true);
    }
    public void removeFromNotes(Note obj) {
        removeToManyTarget("notes", obj, true);
    }
    @SuppressWarnings("unchecked")
    public Set<Note> getNotes() {
        return (Set<Note>)readProperty("notes");
    }


    public void addToPhysicalBooks(PhysicalBook obj) {
        addToManyTarget("physicalBooks", obj, true);
    }
    public void removeFromPhysicalBooks(PhysicalBook obj) {
        removeToManyTarget("physicalBooks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public Set<PhysicalBook> getPhysicalBooks() {
        return (Set<PhysicalBook>)readProperty("physicalBooks");
    }


    public void addToRelatedBooks(Book obj) {
        addToManyTarget("relatedBooks", obj, true);
    }
    public void removeFromRelatedBooks(Book obj) {
        removeToManyTarget("relatedBooks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public Set<Book> getRelatedBooks() {
        return (Set<Book>)readProperty("relatedBooks");
    }


}
