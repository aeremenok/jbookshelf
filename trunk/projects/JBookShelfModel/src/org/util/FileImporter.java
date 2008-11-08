package org.util;

import java.io.File;
import java.io.FileFilter;

import org.jbookshelf.ArchiveFile;
import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Category;
import org.jbookshelf.IndexFileFolder;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.SingleFile;
import org.jbookshelf.SingleFileFolder;

public class FileImporter
{
    public static void importFiles(
        File[] files,
        String pattern,
        BookShelf bookShelf )
    {
        NameParser nameParser = new NameParser( pattern );
        for ( File file : files )
        {
            PhysicalUnit physicalUnit = createPhysicalUnit( file );
            nameParser.parse( file.getName() );

            Author author = bookShelf.addAuthor( nameParser.getAuthorName() );
            System.out.println( author );
            Category category = bookShelf.addCategory( nameParser.getCategoryName() );
            System.out.println( category );
            ReadingUnit book = bookShelf.addReadingUnit( nameParser.getBookName(), author, category, physicalUnit );
            System.out.println( book );
        }
    }

    public static void main(
        String[] args )
    {
        BookShelf bookShelf = JbookshelfFactory.eINSTANCE.createBookShelf();
        File root = new File( "/tmp/test/" );
        importFiles( root.listFiles(), "%a. %b", bookShelf );
    }

    private static PhysicalUnit createPhysicalUnit(
        File file )
    {
        String fileName = file.getName();
        if ( file.isDirectory() )
        {
            File[] files = file.listFiles( new FileFilter()
            {
                public boolean accept(
                    File pathname )
                {
                    String name = pathname.getName();
                    return name.equalsIgnoreCase( "index.html" ) || name.equalsIgnoreCase( "index.htm" );
                }
            } );

            if ( files.length > 1 )
            { // at least one index file
                IndexFileFolder indexFileFolder = JbookshelfFactory.eINSTANCE.createIndexFileFolder();
                indexFileFolder.setIndexFile( files[0] );
                indexFileFolder.setIndexFolder( file );
                return indexFileFolder;
            }

            // simply consider first file a book
            SingleFileFolder singleFileFolder = JbookshelfFactory.eINSTANCE.createSingleFileFolder();
            singleFileFolder.setFolder( file );
            singleFileFolder.setSingleFile( file.listFiles()[0] );
            return singleFileFolder;
        }

        // zip file
        if ( fileName.endsWith( ".zip" ) )
        {
            ArchiveFile archiveFile = JbookshelfFactory.eINSTANCE.createArchiveFile();
            archiveFile.setArchiveFile( file );
            return archiveFile;
        }

        // single file
        SingleFile singleFile = JbookshelfFactory.eINSTANCE.createSingleFile();
        singleFile.setFile( file );
        return singleFile;
    }
}
