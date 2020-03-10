import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AddressBookController {
    AddressBook addressBook;

  /**
   * Constructor
     * @param addressBook
   */
    public AddressBookController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * This method adds a new person to AddressBook
     *
     * @param p
     */
    public void add(Person p) {
        addressBook.add(p);
    }

    /**
     * Changes a person in the Address Book to a new person.
     *
     * @param index Row number of person to be replaced.
     * @param person The new person to be added.
     */
    public void set(int index, Person person) {
        addressBook.set(index, person);
    }

    /**
     * Removes Person from AddressBook
     *
     * @param index row number of person.
     */
    public void remove(int index) {
        addressBook.remove(index);
    }

  /**
     * Gets person object from AddressBook
     *
     * @param index the row number of the person
     * @return the person chosen by the index
     */
    public Person get(int index) {
        return addressBook.get(index);
    }

    /**
     * Clears AddressBook of all data
     */
    public void clear() {
        addressBook.clear();
    }

    /**
     * Opens an addressbook file on the user's directory.
     *
     * @param file file to be opened.
     * @throws FileNotFoundException file isn't found.
     * @throws SQLException SQL code was unable to work.
     */
    public void open(File file) throws FileNotFoundException, SQLException {
        new FileSystem().readFile(addressBook, file);
        addressBook.fireTableDataChanged();
    }

    /**
     * Saves current Address Book
     *
     * @param file file to be saved
     * @throws SQLException SQL code was unable to run
     */
    public void save(File file) throws SQLException {
        new FileSystem().saveFile(addressBook, file);
    }

    /**
     * This method returns the addressBook Object
     *
     * @return the addressBook Object
     */
    public AddressBook getModel() {
        return addressBook;
    }
}