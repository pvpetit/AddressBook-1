
///////////////////////////////////////////////////////////////////////////////
//                      ADDRESS BOOK: FILE SYSTEM TEST                       //
//                            Author: ZGreening                              //
//                       https://github.com/ZGreening                        //
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileSystemTest {
    private static AddressBook addressBook = null; //An generic address book
    private static FileSystem fileSystem=null; //The file system for testing
    private static File file = null;           //A java file for testing

    @BeforeEach
    public void init() {
        //Create a new filesystem each time
        fileSystem = new FileSystem();

        //Create a new address book each time
        addressBook = new AddressBook();

        //Create a new file for testing each time
        file = new File("MyTestFileName");
    }

    @AfterEach
    public void clean() {
        //Delete file if it exists after each test
        if(file!=null && file.exists()) {
            file.delete();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //                                 TESTS                                 //
    ///////////////////////////////////////////////////////////////////////////

    @Test
    public void readNonExistingFile() {
        assertThrows(FileNotFoundException.class, () -> fileSystem.readFile(addressBook,file));
    }

    @Test
    public void readUnreadableFile() {
        //Create the unreadable file
        assertDoesNotThrow(()->file.createNewFile()); 
        file.setReadable(false);

        //Test that file not found exception is thrown. This is the expected result for unreadable files
        assertThrows(FileNotFoundException.class, () -> fileSystem.readFile(addressBook,file));
    }

    @Test
    public void readNonSQLFile() {
        //Write some bytes that are not SQLite formatted
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write("This is some basic non-SQLite text".getBytes());
        } catch(IOException exception) {
            System.out.println("This should not be printed.");
        }

        //Test that SQL exception is thrown when file is not proper format
        assertThrows(SQLException.class, () -> fileSystem.readFile(addressBook,file));
    }

    @Test
    public void saveBlankAddressBook() {
        //Test that file was saved correctly
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));
        assertTrue(file.exists()); //Check file exists on disk
    }

    @Test
    public void readBlankAddressBook() {
        //This should not throw an error, if this errors, so will TEST saveBlankAddressBook()
        //JUNIT says to not write tests with dependencies and that tests should be able to execute
        //in any order. Thus, this test will only succeed if the blank file can be saved AND read
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));

        //Check the persistent file can be read with no exception
        assertDoesNotThrow(() -> fileSystem.readFile(addressBook,file));

        //Check no one is in blank address book
        assertTrue(addressBook.getPersons().length==0); 
    }

    @Test
    public void saveNormalAddressBook() {
        //Add two people to address book
        addressBook.add(new Person("John","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        addressBook.add(new Person("Jane","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));

        //Test that file was saved correctly
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));
        assertTrue(file.exists()); //Check file exists on disk
    }

    @Test
    public void readNormalAddressBook() {
        //Add two people to address book and save
        addressBook.add(new Person("John","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        addressBook.add(new Person("Jane","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));

        //Get new address book
        addressBook = new AddressBook();

        //Check the persistent file can be read with no exception
        assertDoesNotThrow(() -> fileSystem.readFile(addressBook,file));
        assertTrue(addressBook.getPersons().length==2); //Check both people exist

        //Check they are the same
        assertEquals(addressBook.getPersons()[0].toString(),"Doe, John");
        assertEquals(addressBook.getPersons()[1].toString(),"Doe, Jane");
    }

    @Test
    public void saveOverFile() {
        //Add two people to address book and save
        addressBook.add(new Person("John","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        addressBook.add(new Person("Jane","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));

        //Get new address book
        addressBook=new AddressBook();

        //Create two additional people
        addressBook.add(new Person("Bob","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));
        addressBook.add(new Person("Joe","Doe","1234 NON EXIST ROAD","SomeCity","FL","12345","1234567890"));

        //Save over same file
        assertDoesNotThrow(() -> fileSystem.saveFile(addressBook,file));

        //Get new address book
        addressBook=new AddressBook();

        //Read file
        assertDoesNotThrow(() -> fileSystem.readFile(addressBook,file));

        //Check only two people exist
        assertTrue(addressBook.getPersons().length==2); 

        //Check they are second two people and not previous ones
        assertEquals(addressBook.getPersons()[0].toString(),"Doe, Bob");
        assertEquals(addressBook.getPersons()[1].toString(),"Doe, Joe");
    }
}