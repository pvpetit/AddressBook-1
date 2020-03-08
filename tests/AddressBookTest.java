import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class AddressBookTest {

  DefaultTableModel model;
  List<Person> personListTest;
  Person test_Person;
  AddressBook test_AddressBook;


  @BeforeEach
  void setUp() {

    model = new DefaultTableModel();
    personListTest = new ArrayList<>();
    test_Person = new Person("Jane","Dorian",
        "987 Westbrook Blvd","Chincinnati","OH","43123","123456789");

    model.addColumn("Col1");
    model.addColumn("Col2");
    model.addColumn("Col3");
    model.addColumn("Col4");
    model.addColumn("Col5");
    model.addColumn("Col6");
    model.addColumn("Col7");
    test_AddressBook = new AddressBook();
  }

  @AfterEach
  void tearDown() {
    model = null;
    personListTest = null;
    test_Person=null;
  }

  @Test
  public void set(){
    //Given index, change personList with new Person
    Person test_Person2 = new Person("Juanito","Hernandez",
        "19964 Miami Beach","Miami,Ohio","OH","85012","232123210");
    test_AddressBook.add(test_Person);
    test_AddressBook.set(0,test_Person2);
    assertEquals(test_AddressBook.getPersonsList().get(0), test_Person2);

  }

  @Test
  public void addPerson(){
   // personListTest.add(test_Person);

    //Then
    test_AddressBook.add(test_Person);
   // test_AddressBook.fireTableRowsInserted(0,0);
    //testModel.fireTableRowsInserted(0,0);
//
//    doThrow(new IllegalStateException("failed"))
//        .when(testModel).fireTableRowsInserted(0,0);
    //test the add functionality

    assertFalse(test_AddressBook.getPersonsList().isEmpty());

  }

  @Test
  void getPersons() {
    //Unesssary test case
    //Attempt to convert ArrayList to an array

    //Given a Person array
    //Given
    test_AddressBook.add(test_Person);
    List<Person> personList = new ArrayList<>();
    personList.add(test_Person);
    Person[] test_PersonArray = new Person[personList.size()];
    personList.toArray(test_PersonArray);

    test_AddressBook.getPersons();

    //when
    //double result = calcService.add(20.0,10.0);
    assertEquals(test_AddressBook.getPersons().length,test_PersonArray.length);

    //Then valid
  }

  @Test
  void removePerson() {
    //Given an index delete from arrayList
    test_AddressBook.add(test_Person);
    test_AddressBook.remove(0);
    assertEquals(test_AddressBook.getPersonsList().isEmpty(),true);//Confirm delete
  }

  @Test
  void get() {
    //Given index, get from List of Person
    //Also assuming theres no duplicate first last names.
    test_AddressBook.add(test_Person);

    assertEquals(test_Person,test_AddressBook.get(0));
  }

  @Test
  void clearWithEmptyList() {
    test_AddressBook.clear();
    assertEquals(test_AddressBook.getPersonsList().isEmpty(),true);

  }

  @Test
  void ClearWithNonEmptyList(){
    test_AddressBook.add(test_Person);
    test_AddressBook.clear();
    assertEquals(test_AddressBook.getPersonsList().isEmpty(),true);
  }


  @Test
  void getRowCount() {
    //RowCount is Size of persoonList
    test_AddressBook.add(test_Person);
    test_AddressBook.add(test_Person);

    assertEquals(test_AddressBook.getRowCount(),2);
  }

  @Test
  void getColumnCount() {
    //Person Object fields
    assertEquals(test_AddressBook.getColumnCount(),7);


  }

  @Test
  void getValueAt() {

    //Given two vars, get the Person from person List and then 1 of the fields.
    test_AddressBook.add(test_Person);
    //test_AddressBook.getValueAt(0,3);
    //System.out.println( test_AddressBook.getValueAt(0,3));
    assertEquals("Chincinnati",  test_AddressBook.getValueAt(0,3));

  }

  @Test
  void getColumnName() {
//    int getColumnData=1;
//
//    Object[] attributes = new Object[Person.fields.length];
//    for (int i = 0; i < Person.fields.length; i++) {
//      attributes[i] = test_Person.getField(i);
//    }
//
//    model.addRow(attributes);
//
//
//    assertEquals(model.getValueAt(0,getColumnData),test_Person.getField(getColumnData));

    test_AddressBook.add(test_Person);
    System.out.println(test_AddressBook.getColumnName(1));
  }
}