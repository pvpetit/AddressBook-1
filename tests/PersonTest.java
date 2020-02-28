import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest
{
  Person testPerson;

  @BeforeEach 
  void init() {
    testPerson = new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789");
  }

  @Test
  public void getFirstName() {
    assertEquals("John",testPerson.getFirstName());
  }

  @Test
  public void getLastName() {
    assertEquals("Doe",testPerson.getLastName());
  }

  @Test
  public void getAddress() {
    assertEquals("123 Fake Street",testPerson.getAddress());
  }

  @Test
  public void getCity() {
    assertEquals("Fort Myers",testPerson.getCity());
  }

  @Test
  public void getState() {
    assertEquals("FL",testPerson.getState());
  }

  @Test
  public void getZip() {
    assertEquals("33901",testPerson.getZip());
  }

  @Test
  public void getPhone() {
    assertEquals("123456789",testPerson.getPhone());
  }

  @Test
  public void containsString() {
    assertTrue(testPerson.containsString("John"));
    assertFalse(testPerson.containsString("Michael"));
    assertTrue(testPerson.containsString("Doe"));
    assertTrue(testPerson.containsString("123 Fake Street"));
    assertTrue(testPerson.containsString("Fort Myers"));
    assertTrue(testPerson.containsString("FL"));
    assertTrue(testPerson.containsString("33901"));
    assertTrue(testPerson.containsString("123456789"));
  }

  @Test
  public void getField() {
    assertEquals(testPerson.getField(0),"Doe");
    assertEquals(testPerson.getField(1),"John");
    assertEquals(testPerson.getField(2),"123 Fake Street");
    assertEquals(testPerson.getField(3),"Fort Myers");
    assertEquals(testPerson.getField(4),"FL");
    assertEquals(testPerson.getField(5),"33901");
    assertEquals(testPerson.getField(6),"123456789");
    Exception exception = assertThrows(Exception.class, () -> testPerson.getField(7));
    assertEquals("Field number out of bounds",exception.getMessage());
  }

}