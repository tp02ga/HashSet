package hashset;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.core.Is.*;

public class HashSetTests
{
  private static final int BIG_TEST_SIZE = 5000;

  @Test
  public void should_insert_value_into_hashset_and_retrieve_it()
  {
//    java.util.HashSet<String> asdf = new java.util.HashSet<>();
    
    HashSet<String> sut = new HashSet<>();

    sut.add("My First String");

    for (String aString : sut)
    {
      assertThat(aString, is("My First String"));
    }
    
  }

  @Test
  public void should_insert_multiple_values_into_hashset_and_retrieve_it()
  {
    HashSet<String> sut = new HashSet<>();

    sut.add("My First String");
    sut.add("My Second String");
    sut.add("My Third String");
    sut.add("My Seventh String");

    boolean found1 = false;
    boolean found2 = false;
    boolean found3 = false;
    boolean found7 = false;
    
    for (String aString : sut)
    {
      if ("My First String".equals(aString))
      {
        found1 = true;
      }
      else if ("My Second String".equals(aString))
      {
        found2 = true;
      }
      else if ("My Third String".equals(aString))
      {
        found3 = true;
      }
      else if ("My Seventh String".equals(aString))
      {
        found7 = true;
      }
    }
    assertTrue(found1);
    assertTrue(found2);
    assertTrue(found3);
    assertTrue(found7);
  }

  @Test
  public void should_remove_an_element_from_hashset()
  {
    HashSet<String> sut = new HashSet<>();

    sut.add("My First String");
    sut.add("My Second String");
    sut.add("My Third String");
    sut.add("My Seventh String");
    
    sut.remove("My First String");
    
    boolean found1 = false;
    boolean found2 = false;
    boolean found3 = false;
    boolean found7 = false;
    
    for (String aString : sut)
    {
      if ("My First String".equals(aString))
      {
        found1 = true;
      }
      else if ("My Second String".equals(aString))
      {
        found2 = true;
      }
      else if ("My Third String".equals(aString))
      {
        found3 = true;
      }
      else if ("My Seventh String".equals(aString))
      {
        found7 = true;
      }
    }
    
    assertFalse(found1);
    assertTrue(found2);
    assertTrue(found3);
    assertTrue(found7);
  }
  
  
  @Test
  public void should_insert_and_remove_lots_of_elements ()
  {
    HashSet<Integer> sut = new HashSet<>();
    
    for (int i=0; i<BIG_TEST_SIZE; i++)
    {
      sut.add(i);
    }
    
    java.util.Set<Integer> foundSet = new java.util.HashSet<>();
    
    for (int i=0; i<BIG_TEST_SIZE; i++)
    {
      foundSet.add(i);
    }
    
    for (Integer i : sut)
    {
      foundSet.contains(i);
    }
    
    
    for (int i=BIG_TEST_SIZE-1; i>=0; i--)
    {
      sut.remove(i);
    }
    
    assertFalse(sut.iterator().hasNext());
  }

}
