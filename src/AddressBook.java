import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AddressBook extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Person> persons = new ArrayList<>();

    public Person[] getPersons() {
        return persons.toArray(new Person[persons.size()]);
    }

    /**
     * THis method adds person to AddressBook and
     * automatically updates the table GUI.
     *
     * @param p New Person to be added
     */
    public void add(Person p) {
        int newIndex = persons.size();
        persons.add(p);
        fireTableRowsInserted(newIndex, newIndex);
    }

    /**
     * Sets the person at the given index to the Person specified.
     *
     * @param index  Index to update.
     * @param person Person to replace the item with.
     */
    public void set(int index, Person person) {
        persons.set(index, person);
        fireTableRowsUpdated(index, index);
    }

    /**
     * Remove Person from Address Book
     *
     * @param index the row number for person to be deleted.
     */
    public void remove(int index) {
        persons.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * This method returns person from the index
     *
     * @param index row number of Person
     * @return Person in the index
     */
    public Person get(int index) {
        return persons.get(index);
    }

    /**
     * Clears this address book.
     */
    public void clear() {
        if (persons == null || persons.size() == 0) {
            return;
        }

        //Clear persons first
        int lastRow=persons.size()-1;
        persons.clear();

        //Delete table rows
        fireTableRowsDeleted(0, lastRow);
    }

    /**
     *
     * @return total number of people in AddressBook
     */
    @Override
    public int getRowCount() {
        return persons.size();
    }

  /**
     *
     * @return total number of fields, Should be 7 always.
     */
    public int getColumnCount() {
        return Person.fields.length;
    }

    /**
     * Gets the table value from column and row.
     *
     * @param row The index of the person
     * @param column The index of person's fields
     * @return Object, but will be a String.
     */
    @Override
    public Object getValueAt(int row, int column) {
        return persons.get(row).getField(column);
    }

    /**
     * Gets column name.
     * @param column index of column name
     * @return column name
     */
    @Override
    public String getColumnName(int column) {
        return Person.fields[column];
    }
}