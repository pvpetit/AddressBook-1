package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import Person;

public class PersonTest
{
  //public src.Person(String firstName, String lastName, String address,
    // String city, String state, String zip, String phone)
  Person test_Person = new Person("John","Doe","123 fake street","Fort Myers","FL","33901","123456789");

  //testing successful conditon
  @Test
  public void getFirstNameSuccess() {
    Person test_Person = new Person("John","Doe","123 fake street","Fort Myers","FL","33901","123456789");
    assertEquals("John",test_Person.getFirstName());
    System.out.println(test_Person.getFirstName());
  }
  //This test should fail
  @Test
  public void getFirstNameFail() {
    assertEquals("Michael",test_Person.getFirstName());
  }

  @Test
  public void getLastName() {
  }

  @Test
  public void getAddress() {
  }

  @Test
  public void getCity() {
  }

  @Test
  public void getState() {
  }

  @Test
  public void getZip() {
  }

  @Test
  public void getPhone() {
  }

//  @Test
//  public void toString() {
//  }

  @Test
  public void containsString() {
  }

  @Test
  public void getField() {
  }
}