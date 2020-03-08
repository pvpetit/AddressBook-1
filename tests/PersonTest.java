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
  Person test_Person = new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789");


  @Test
  void test1(){
    test_Person.getCity();
  }

  @Test
  void personPhoneNumberMustbe9Numbers(){
    assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789"));
  }

  @Test
  void personPhoneNumberMustbe9NumbersNegativeCase(){
    assertThrows(IllegalArgumentException.class, () -> new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","0123456789" ));
    //assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FLR","33901","123456789"));
  }

  @Test
  void personObjectZipCodeMustBe5NumbersToPass(){
  assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789"));
  }

  @Test
  void personObjectZipCodeMustBe5NumbersToPassNegativeCase(){
    assertThrows(IllegalArgumentException.class, () -> new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901K","123456789" ));
    //assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FLR","33901","123456789"));
  }

  @Test
  void statePassesRegexIf2AlphaCharacterAreEnteredNegativeTestCaseShouldThrowError(){
    assertThrows(IllegalArgumentException.class, () -> new Person("John","Doe","123 Fake Street","Fort Myers","Florida","33901","123456789" ));
    //assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FLR","33901","123456789"));
  }

  @Test
  void statePassesRegexIf2AlphaCharacterAreEntered(){
    //System.out.println("Run?");
    //new Person("John","Doe","123 Fake Street","Fort Myers","fl2","33901","123456789");
  assertNotNull(new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789"));
  }

  @Test
  void statePassesRegexIf2AlphaCharacterAreEnteredThrowsError(){
    assertThrows(IllegalArgumentException.class, () ->  new Person("Zeke","Doe","123 Fake Street","Fort Myers","FLR","33901","123456789")
    );
  }

  @ParameterizedTest(name = "#{index} - Person Test with args: {0}")
  @CsvSource({
      ", ,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
      "J, P,  931 DisneyStreet, Orlando,FL, 33123, 987654321",
      "2, P12,  931 DisneyStreet, Orlando,FL, 33123, 987654321",

  })
  void FirstNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowedThrowsError(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertThrows(IllegalArgumentException.class, () ->  new Person(firstName,lastName,address,city,state,zip,phone));

  }

  @ParameterizedTest(name = "#{index} - Person Test with arg: {0}")
  @CsvSource({
      "Joel, Masters,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
      "Mary, Poe,  931 DisneyStreet, Orlando,FL, 33123, 987654321",
      "MarcusReallyLongName, Poe,  931 DisneyStreet, Orlando,FL, 33123, 987654321"

  })
  void FirstNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowed(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertNotNull(new Person(firstName,lastName,address,city,state,zip,phone));
  }

  @ParameterizedTest(name = "#{index} - Person Test with args: {0}")
  @CsvSource({
      "John, ,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
      "Mary, P,  931 DisneyStreet, Orlando,FL, 33123, 987654321",
      "Lambda, P12,  931 DisneyStreet, Orlando,FL, 33123, 987654321",

  })
  void LastNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowedThrowsError(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertThrows(IllegalArgumentException.class, () ->  new Person(firstName,lastName,address,city,state,zip,phone));
  //assertNotNull(new Person(firstName,lastName,address,city,state,zip,phone));
  }

  @ParameterizedTest(name = "#{index} - Person Test with arg: {0}")
  @CsvSource({
      "Joel, Masters,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
      "Mary, Po,  931 DisneyStreet, Orlando,FL, 33123, 987654321",

  })
  void LastNamePassesRegexOfAtleast2CharactersSpecialCharacterAllowed(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    assertNotNull(new Person(firstName,lastName,address,city,state,zip,phone));
  }

//  @ParameterizedTest
//  @MethodSource("GetFromCSVStreamPersonArguemnts")
//  void TestCsvWithArgumentStreamThisRecievesrguments(Person test_Person) {
//    System.out.println(test_Person.toString());
////    assertTrue(str.length() > 0);
////    assertEquals(length, list.size());
//  }

//   You can get arguments from a csv source, however it isnt nessary
//  @ParameterizedTest
//  @CsvSource({
//      "John, Doe,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
//      "Maey, Poppins,  931 DisneyStreet, Orlando,FL, 33123, 9876543210",
//
//  })
//  static Stream<Arguments> GetFromCSVStreamPersonArguemnts(String firstName, String lastName, String address, String city, String state, String zip,
//      String phone) {
//    return Stream.of(
//        arguments(firstName,lastName,address,city,state,zip,phone)
//        //arguments("lemon", 2, Arrays.asList("x", "y"))
//    );
//  }



  @ParameterizedTest
  @CsvSource({
      "John, Doe,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
      "Maey, Poppins,  931 DisneyStreet, Orlando,FL, 33123, 987654321",

  })
  void test_csv(String firstName, String lastName, String address, String city, String state, String zip,
      String phone) {
    Person test_Person1 = new Person(firstName,lastName,address,city,state,zip,phone);
    //System.out.println(test_Person1.toString());
  }

//  // this need stati
  // You cannot stream a person object without making a factory for it. Not worth the effort
//  @ParameterizedTest
//  @CsvSource({
//      "John, Doe,  420 Yeehaw Avenue, LeeHigh Anchors,FL, 33901, 123456789",
//      "Maey, Poppins,  931 DisneyStreet, Orlando,FL, 33123, 9876543210",
//
//  })
//  static Stream<Person> personStream(String firstName, String lastName, String address, String city, String state, String zip,
//      String phone) {
//    Person test_Person1 = new Person(firstName,lastName,address,city,state,zip,phone);
//    return Stream.of(test_Person1);
//  }
//
//  @ParameterizedTest
//  @MethodSource("personStream")
//  public void getFirstNameParamterTest(Person test_Person) {
//    System.out.println(test_Person.toString());
//    //assertEquals("John",test_Person.getFirstName());
//  }


  /**
   * So even better you can test multiple arguemtsn at the same time, and they can be different types.
   * Also you can send not only int or string but arrays (Which i dont belive we are doing).
   *
   * So this stream arguments will realy work when we are testing different persons, and we can create
   * and test different arguemtns here
   *
   * What am I hopng to do is make a first batch of tests with good and bad inputs, and then the good inputs
   * will be created as persons we willuse in later test cases.
   * Bad inputs that dont create user should send an error to user to fix
   * @param str
   * @param length
   * @param list
   */
  @ParameterizedTest
  @MethodSource("stringIntAndListProvider")
  void testWithMultiArgMethodSource(String str, int length, List<String> list) {
    assertTrue(str.length() > 0);
    assertEquals(length, list.size());
  }

  static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        arguments("abc", 3, Arrays.asList("a", "b", "c")),
        arguments("lemon", 2, Arrays.asList("x", "y"))
    );
  }


  /**
   * Alright here real where the real juice is.
   * With methodsource you can have a method store string or ints you want ot test
   * so If we wanted to do muoltiple types of names we can have a methoed hold it all
   * Instead of having one name and tesitng it with all tests, could really save lines of code
   * Need to check if we have have multiple methods nne test so we can check all person's textfiles at the same times
   *
   * @param arg
   */

  @ParameterizedTest(name = "#{index} - Test with String : {0}")
  @MethodSource("stringProvider")
  void test_method_string(String arg) {
    assertNotNull(arg);
  }

  // this need static
  static Stream<String> stringProvider() {
    return Stream.of("java", "rust");
  }

  @ParameterizedTest(name = "#{index} - Test with Int : {0}")
  @MethodSource("rangeProvider")
  void test_method_int(int arg) {
    assertTrue(arg < 10);
  }

  // this need static
  static IntStream rangeProvider() {
    return IntStream.range(0, 10);
  }

  //Ok with Paramtertized test we can run multiple test without writnig so many damn test cases
  //With thisy ou can make an array of ints to test
  // This test will run 3 times with different arguments
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  void test_int_arrays(int arg) {
    assertTrue(arg > 0);
  }

  @ParameterizedTest(name = "#{index} - Run test with args={0}")
  @ValueSource(ints = {1, 2, 3})
  void test_int_arrays_custom_name(int arg) {
    assertTrue(arg > 0);
  }

  @ParameterizedTest(name = "#{index} - Run test with args={0}")
  @ValueSource(strings = {"apple", "banana", "orange"})
  void test_string_arrays_custom_name(String arg) {
    assertTrue(arg.length() > 1);
  }

  @Test
  public void getFirstName() {
    assertEquals("John",test_Person.getFirstName());
  }

  @Test
  public void getLastName() {
    assertEquals("Doe",test_Person.getLastName());
  }

  @Test
  public void getAddress() {
    assertEquals("123 Fake Street",test_Person.getAddress());
  }

  @Test
  public void getCity() {
    assertEquals("Fort Myers",test_Person.getCity());
  }

  @Test
  public void getState() {
    assertEquals("FL",test_Person.getState());
  }

  @Test
  public void getZip() {
    assertEquals("33901",test_Person.getZip());
  }

  @Test
  public void getPhone() {
    assertEquals("123456789",test_Person.getPhone());
  }

  @Test
  public void badName(){
    Exception exception = assertThrows(Exception.class, () -> test_Person.getField(3));

  }
  @Test
  public void containsString() {
    assertTrue(test_Person.containsString("John"));
    assertFalse(test_Person.containsString("Michael"));
    assertTrue(test_Person.containsString("Doe"));
    assertTrue(test_Person.containsString("123 Fake Street"));
    assertTrue(test_Person.containsString("Fort Myers"));
    assertTrue(test_Person.containsString("FL"));
    assertTrue(test_Person.containsString("33901"));
    assertTrue(test_Person.containsString("123456789"));
  }

  @Test
  public void getField() {
    assertEquals(test_Person.getField(0),"Doe");
    assertEquals(test_Person.getField(1),"John");
    assertEquals(test_Person.getField(2),"123 Fake Street");
    assertEquals(test_Person.getField(3),"Fort Myers");
    assertEquals(test_Person.getField(4),"FL");
    assertEquals(test_Person.getField(5),"33901");
    assertEquals(test_Person.getField(6),"123456789");
    Exception exception = assertThrows(Exception.class, () -> test_Person.getField(7));
    assertEquals("Field number out of bounds",exception.getMessage());
  }
  @Test
  public void toStringTest() {
     assertEquals("Doe, John",test_Person.toString());
  }

}