import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PersonTest
{
  Person test_Person = new Person("John","Doe","123 Fake Street","Fort Myers","FL","33901","123456789");

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

}