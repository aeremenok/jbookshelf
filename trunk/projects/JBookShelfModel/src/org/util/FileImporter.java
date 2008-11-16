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

/**
 * imports filesystem into {@link BookShelf}
 * 
 * @author eav
 */
public class FileImporter
{
    static class HtmlFolderFileFilter
        implements
            FileFilter
    {
        private String mainFolderName;

        public boolean accept(
            File pathname )
        {
            boolean b = pathname.isDirectory() && pathname.getName().endsWith( "_files" );
            if ( b )
            {
                mainFolderName = pathname.getName();
            }
            return b;
        }

        public String getMainFolderName()
        {
            return mainFolderName;
        }
    }

    public static String cutExtension(
        File file )
    {
        if ( file.isDirectory() )
        {
            return file.getName();
        }

        String name = file.getName();
        int lastIndexOf = name.lastIndexOf( '.' );
        if ( name.length() - lastIndexOf > 5 )
        { // it is probably a part of the name todo which files could have a longer extension?
            return name;
        }

        return name.substring( 0, lastIndexOf );
    }

    public static void main(
        String[] args )
    {
        BookShelf bookShelf = JbookshelfFactory.eINSTANCE.createBookShelf();
        File root = new File( "/tmp/test" );
        new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause:" + e.getMessage() );
            }

            @Override
            protected void onImportSuccess(
                ReadingUnit book )
            {
                System.out.println( "+imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
            }
        }.importFiles( root.listFiles(), "%a. %b", bookShelf );
    }

    public static PhysicalUnit createPhysicalUnit(
        File file )
    {
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

            File[] listFiles = file.listFiles();
            if ( listFiles.length == 1 )
            { // simply single file
                SingleFileFolder singleFileFolder = JbookshelfFactory.eINSTANCE.createSingleFileFolder();
                singleFileFolder.setFolder( file );
                singleFileFolder.setSingleFile( listFiles[0] );
                return singleFileFolder;
            }

            File mainFile = getHtmlFile( file );
            if ( mainFile != null )
            { // file.html with file_files/
                SingleFileFolder singleFileFolder = JbookshelfFactory.eINSTANCE.createSingleFileFolder();
                singleFileFolder.setFolder( file );
                singleFileFolder.setSingleFile( mainFile );
                return singleFileFolder;
            }
            return null;
        }

        // zip file
        if ( file.getName().endsWith( ".zip" ) )
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

    /**
     * try to find if folder holds "file" with "file_files/"
     * 
     * @param folder folder
     * @return main file ("file") or null in case failure
     */
    private static File getHtmlFile(
        File folder )
    {
        HtmlFolderFileFilter filter = new HtmlFolderFileFilter();
        File[] listFiles = folder.listFiles( filter );
        if ( listFiles.length == 1 )
        {
            String mainFolderName = filter.getMainFolderName();
            File mainFile = new File( mainFolderName.substring( 0, mainFolderName.indexOf( "_files" ) ) );
            if ( mainFile.exists() )
            {
                return mainFile;
            }
        }
        return null;
    }

    public void importFiles(
        File[] files,
        String pattern,
        BookShelf bookShelf )
    {
        NameParser nameParser = new NameParser( pattern );
        for ( File file : files )
        {
            PhysicalUnit physicalUnit = createPhysicalUnit( file );
            if ( physicalUnit != null )
            {
                try
                {
                    nameParser.parse( cutExtension( file ) );

                    Author author = bookShelf.addAuthor( nameParser.getAuthorName() );
                    Category category = bookShelf.addCategory( nameParser.getCategoryName() );
                    ReadingUnit book =
                        bookShelf.addReadingUnit( nameParser.getBookName(), author, category, physicalUnit );

                    onImportSuccess( book );
                }
                catch ( Exception e )
                {
                    onImportFailure( file, e );
                }
            }
            else if ( file.isDirectory() )
            {
                importFiles( file.listFiles(), pattern, bookShelf );
            }
            else
            {
                onImportFailure( file, new Exception( "unparseable directory" ) // todo specify more info
                );
            }
        }
    }

    protected void onImportFailure(
        @SuppressWarnings( "unused" ) File file,
        @SuppressWarnings( "unused" ) Exception e )
    {
        // override if needed
    }

    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) ReadingUnit book )
    {
        // override if needed
    }
}
