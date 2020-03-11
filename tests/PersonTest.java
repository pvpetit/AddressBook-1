import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PersonTest
{
  Person test_Person = new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","0123456789");

  /**
   * This test case will test that the inputted Phone Number exactly 10 digis.
   */
  @Test
  void personPhoneNumberMustbe10Numbers(){
    assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","0123456789"));
  }

  /**
   * This negative case will throw an error because 10 digits were not entered as the phonenumber
   */
  @Test
  void personPhoneNumberMustbe10NumbersNegativeCase(){
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","00123456789" ));
    assertEquals("Phone Number Must be 10 numbers",exception.getMessage());
   }

  /**
   * The Person Object must have 5 numbers for a valid input of zipcode
   */
  @Test
  void personObjectZipCodeMustBe5NumbersToPass(){
  assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","0123456789"));
  }

  /**
   * This is a negative test case where input is not 5 digits for the zipcode.
   */
  @Test
  void personObjectZipCodeMustBe5NumbersToPassNegativeCase(){

    Exception exception= assertThrows(IllegalArgumentException.class, () -> new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901K","0123456789" ));
    assertEquals("ZipCode Must be 5 numbers",exception.getMessage());
  }

  /**
   * This test case tests if the State is 2 Letters
   */
  @Test
  void statePassesRegexIf2AlphaCharacterAreEntered(){
    //System.out.println("Run?");
    //new Person("John","Doe","123 Fake Street","Fort Myers","fl2","33901","0123456789");
  assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","0123456789"));
  }

  /**
   * This negative test case will test if entering anything other than 2 letters
   * will throw an error.
   */
  @Test
  void statePassesRegexIf2AlphaCharacterAreEnteredThrowsError(){
    Exception exception= assertThrows(IllegalArgumentException.class, () ->  new Person("Zeke","Doe","123 Fake Street","Fort Myers","FLR","33901","0123456789")
    );
    assertEquals("State Must be 2 Characters",exception.getMessage());
  }

  /**
   *This is a paramterized test case which will test each line a seperate test case.
   * This test will check the first name, if it isn't atleast 2 characters, then ti will
   * fail.
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {0}")
  @CsvSource({
      ", ,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 0123456789",
      "J, P,  931 DisneyStreet, Orlando,FL, 33123, 0987654321",
      "2, P12,  931 DisneyStreet, Orlando,FL, 33123, 0987654321",

  })
  void FirstNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowedThrowsError(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->  new Person(firstName,lastName,address,city,state,zip,phone));
    if (firstName == null){
      assertEquals("First name cannot be empty",exception.getMessage());
    } else{
      assertEquals("First Name have 2 characters.",exception.getMessage());
    }

  }

  /**
   *This is a ParameterizedTest case which will test each line a seperate test case.
   * This test will check the first name, if it is atleast 2 character Letter, or special character
   * then it will pass.
   */
  @ParameterizedTest(name = "#{index} - Person Test with arg: {0}")
  @CsvSource({
      "J*, Masters,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 0123456789",
      "90, Poe,  931 DisneyStreet, Orlando,FL, 33123, 0987654321",
      "MarcusReallyLongName2*, Poe,  931 DisneyStreet, Orlando,FL, 33123, 0987654321"

  })
  void FirstNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowed(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertNotNull(new Person(firstName,lastName,address,city,state,zip,phone));
  }

  /**
   *This is a ParameterizedTest case which will test each line a seperate test case.
   * This negative test will check the last name, if it isn't 2 letters or special character
   * then it is invalid and will throw an error this test wrks.
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {1}")
  @CsvSource({
      "John, ,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 0123456789",
      "Mary, P,  931 DisneyStreet, Orlando,FL, 33123, 987654321",
      "Lambda, 1,  931 DisneyStreet, Orlando,FL, 33123, 987654321",

  })
  void LastNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowedThrowsError(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->  new Person(firstName,lastName,address,city,state,zip,phone));
    if (lastName == null){
      assertEquals("Last name cannot be empty",exception.getMessage());
    } else{
      assertEquals("Last Name have 2 characters.",exception.getMessage());
    }
  }

  /**
   *This is a ParameterizedTest case which will test each line a seperate test case.
   * This test will check the last name, if it is atleast 2 character Letter, or special character
   * then it will pass.
   */
  @ParameterizedTest(name = "#{index} - Person Test with arg: {0}")
  @CsvSource({
      "Joel, Masters,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 0123456789",
      "Mary, Po,  931 DisneyStreet, Orlando,FL, 33123, 0987654321",

  })
  void LastNamePassesRegexOfAtLeast2CharactersSpecialCharacterAllowed(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertNotNull(new Person(firstName,lastName,address,city,state,zip,phone));
  }

  /**
   * This test case will test if the correct First Name is returned.
   */
  @Test
  public void getFirstName() {
    assertEquals("John",test_Person.getFirstName());
  }

  /**
   * This test case will test if the correct Last Name of person is returned
   */
  @Test
  public void getLastName() {
    assertEquals("Doe",test_Person.getLastName());
  }

  /**
   * This test case will test if correct Address is returned
   */
  @Test
  public void getAddress() {
    assertEquals("123 Fake Street",test_Person.getAddress());
  }

  /**
   * This test case will test if correct city is returned
   */
  @Test
  public void getCity() {
    assertEquals("Fort Myers",test_Person.getCity());
  }

  /**
   * This test case will test if correct state is returned.
   */
  @Test
  public void getState() {
    assertEquals("FL",test_Person.getState());
  }

  /**
   * This test case will test if correct zip is returned
   */
  @Test
  public void getZip() {
    assertEquals("33901",test_Person.getZip());
  }

  /**
   * This test case will test if correct zip is returned
   */
  @Test
  public void getPhone() {
    assertEquals("0123456789",test_Person.getPhone());
  }

//  /**
//   * This test case tests the getField method
//   */
//  @Test
//  public void badName(){
//    assertEquals("Doe",test_Person.getField(0));
//  }

  /**
   * This test case will test the containString() method.
   * Each field is searched for the search string and will return
   * true is it is found.
   *
   */
  @Test
  public void containsString() {
    assertTrue(test_Person.containsString("John"));
    assertFalse(test_Person.containsString("Michael"));
    assertTrue(test_Person.containsString("Doe"));
    assertTrue(test_Person.containsString("123 Fake Street"));
    assertTrue(test_Person.containsString("Fort Myers"));
    assertTrue(test_Person.containsString("FL"));
    assertTrue(test_Person.containsString("33901"));
    assertTrue(test_Person.containsString("0123456789"));
  }

  /**
   * This test csae tests the containString function which searches each person field for a match.
   * If any consective string match a field it it will pass
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {0}")
  @CsvSource({
      "Joh",
      "Do",
      "Fake",
      "Fort ",
      "FL",
      "33901",
      "01234"
  })
  public void containsStringParameterizdTest(String searchString) {
    assertTrue(test_Person.containsString(searchString));
  }

  /**
   * This is negative test case will test queries that won't give a result.
   * @param searchString the search query
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {0}")
  @CsvSource({
      "Zac",
      "Marconi",
      "87",
      "NewYork",
      "MI",
      "32",
      "000"
  })
  public void containsStringParameterizdTestNegativeTests(String searchString) {
    assertFalse(test_Person.containsString(searchString));
  }


  /**
   * This Tests the getField method, which given index, return data of the person.
   * This is a boundary test as valid inputs are between 0-6. All Others fail
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {1}")
  @CsvSource({
      "Doe, 0",
      "John, 1",
      "123 Fake Street,2",
      "Fort Myers, 3,",
      "FL, 4,",
      "33901, 5,",
      "0123456789, 6,",
  })
  void checkGetFieldReturnsCorrectData(String fieldResult, int index) {
    //assertThrows(IllegalArgumentException.class, () ->  new Person(firstName,lastName,address,city,state,zip,phone));
    assertEquals(fieldResult,test_Person.getField(index));
  }
  /**
   * This is negative test case which will catch error when getField(index) parameter
   * isnt' between 0-6
   */
  @ParameterizedTest(name = "#{index} - Person Test with args: {1}")
  @CsvSource({
      "Doe, -1",
      "Doe, -2",
      "John, 7",
      "John, 8",
  })
  void checkGetFieldReturnsCorrectDataNegativeCaseThrowsError(String fieldResult, int index) {
    Exception exception = assertThrows(IllegalArgumentException.class,()-> test_Person.getField(index));
    assertEquals("Field number out of bounds",exception.getMessage());
  }
  @Test
  public void getField() {
    assertEquals(test_Person.getField(0),"Doe");
    assertEquals(test_Person.getField(1),"John");
    assertEquals(test_Person.getField(2),"123 Fake Street");
    assertEquals(test_Person.getField(3),"Fort Myers");
    assertEquals(test_Person.getField(4),"FL");
    assertEquals(test_Person.getField(5),"33901");
    assertEquals(test_Person.getField(6),"0123456789");
    Exception exception = assertThrows(Exception.class, () -> test_Person.getField(7));
    assertEquals("Field number out of bounds",exception.getMessage());
  }

  /**
   * This test will test to String method which returns "Lastname, First" in that format
   */
  @Test
  public void toStringTest() {
     assertEquals("Doe, John",test_Person.toString());
  }

  /**
   * This test will test to String method which returns "Lastname, First" in that format
   */
  @ParameterizedTest(name = "#{index} - Person Test with arg: {0} {1}")
  @CsvSource({
      "Joel, Masters,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 0123456789",
      "Mary, Poe,  931 DisneyStreet, Orlando,FL, 33123, 0987654321",
      "MarcusReallyLongName2*, Poe,  931 DisneyStreet, Orlando,FL, 33123, 0987654321"

  })
  void toString(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertNotNull(test_Person= new Person(firstName,lastName,address,city,state,zip,phone));
    assertEquals(lastName+", "+firstName,test_Person.toString());
  }

}