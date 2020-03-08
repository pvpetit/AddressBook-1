import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressBookControllerTest {

  //DefaultTableModel model;
  List<Person> personListTest;
  Person test_Person;
  AddressBook test_AddressBook;
  AddressBook addressBookMock;

  AddressBookController controllerTest;

  @BeforeEach
  void setUp() {

    //model = new DefaultTableModel();
    personListTest = new ArrayList<>();
    test_Person = new Person("Jane","Dorian",
        "987 Westbrook Blvd","Chincinnati","OH","43123","123456789");

//    model.addColumn("Col1");
//    model.addColumn("Col2");
//    model.addColumn("Col3");
//    model.addColumn("Col4");
//    model.addColumn("Col5");
//    model.addColumn("Col6");
//    model.addColumn("Col7");
    test_AddressBook = new AddressBook();
    controllerTest = new AddressBookController(test_AddressBook);
  }

  @AfterEach
  void tearDown() {
    //model = null;
    personListTest = null;
    test_Person=null;
    test_AddressBook = null;
    controllerTest = null;
  }

  @Test
  void addPersonToList() {

    controllerTest.add(test_Person);

    System.out.println(controllerTest.get(0).toString());
    assertEquals(controllerTest.get(0),test_Person);
  }

  //You cannot set at 0, so something much be in arralyist
  @Test
  void setWith1PersonInList() {
    //given index, and person chnage person in address book
    //doNothing().when(addressBookMock).set(0,test_Person);
    controllerTest.add(test_Person);
    controllerTest.set(0,test_Person);

    assertEquals(controllerTest.get(0),test_Person);
  }


  @Test
  void setWithEmptyListShouldThrowError() {
    //given index, and person chnage person in address book
    //doNothing().when(addressBookMock).set(0,test_Person);
    //excetionRule.expect(IndexOutOfBoundsException.class);


    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
      controllerTest.set(0,test_Person);
    });

    //assertEquals(controllerTest.get(0),test_Person);
  }

  @Test
  void removePersonFromListAndListWillNotBeEmpty() {
    //doNothing().when(addressBookMock).remove(0);
    controllerTest.add(test_Person);
    int originalsize = controllerTest.addressBook.getPersonsList().size();


    controllerTest.remove(0);
    int newsize = controllerTest.addressBook.getPersonsList().size();

    assertEquals(newsize+1, originalsize);
  }

  @Test
  void removeOnEmptyListShouldThrowError() {
    //doNothing().when(addressBookMock).remove(0);

    //controllerTest.add(test_Person);



    //controllerTest.remove(0);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
      controllerTest.remove(0);
    });
    //assertEquals(controllerTest.get(0),null);
  }

  @Test
  void getPersonOnNONEmpyList() {

    controllerTest.add(test_Person);

    System.out.println(controllerTest.get(0).toString());
    assertEquals(controllerTest.get(0),test_Person);
  }

  @Test
  void getOnEmpyListShouldThrowError() {

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
      controllerTest.get(0);
    });

  }

  @Test
  void clearList() {


      controllerTest.clear();
      assertEquals(test_AddressBook.getPersonsList().isEmpty(),true );

      //test_AddressBook
//    doNothing().when(addressBookMock).clear();
//    controllerTest.clear();
//
//    verify(addressBookMock).clear();

  }

  //Figure this out later
  public void openFile(File file)  {
//    new FileSystem().readFile(addressBook, file);
//    addressBook.fireTableDataChanged();
  }

  //Figure this out later
  public void save(File file)  {
    //new FileSystem().saveFile(addressBook, file);
  }


  @Test
  void getAddressBook() {
    //Gets the address book object from addressbookController
    //never used

    assertEquals(test_AddressBook, controllerTest.getModel());

     }
}