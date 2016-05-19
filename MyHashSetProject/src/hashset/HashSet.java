package hashset;

import java.util.Iterator;

public class HashSet<K> implements Iterable<K>
{
  Object[] elements;
  // threshold = loadFactor * maxSize;
  double loadFactor = 0.75;
  int size = 0;
  final Object DUMMY = new Object();

  public HashSet()
  {
    elements = new Object[16];
  }

  public boolean add(K key)
  {
    return put(key, DUMMY, elements);
  }

  @SuppressWarnings("unchecked")
  private <V> boolean put(K key, V value, Object[] backingArray)
  {
    // 1. hash it
    // 2. get an index
    // 3. put it into array

    int hashCode = key.hashCode();

    int index = Math.abs(hashCode % backingArray.length);

    Entry<K, V> entry = new Entry<>(hashCode, key, value);

    if (backingArray[index] == null)
    {
      backingArray[index] = entry;
    } else
    {
      Entry<K, V> node = (Entry<K, V>) backingArray[index];

      // since this is a HashSet we need to check for duplicates
      while (node.getNext() != null)
      {
        if (key.equals(node.getKey()))
        {
          return false;
        }

        node = node.getNext();
      }
      node.setNext(entry);
    }
    size++;

    if (shouldGrowHashMap(backingArray))
    {
      System.out.println("Before: Growing hashmap, max size is: " + elements.length);
      growHashMap();
      System.out.println("After:  Growing hashmap, max size is: " + elements.length);
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  private <V> void growHashMap()
  {
    // 1. create a new backing object array (with double the size of the old
    // one)
    // 2. populate the new backing object array with existing elements
    // 3. assign the old backing object array to point to the new one

    Object[] newElements = new Object[elements.length * 2];
    size = 0;

    for (int i = 0; i < elements.length; i++)
    {
      while (elements[i] != null)
      {
        Entry<K, V> entry = (Entry<K, V>) elements[i];
        put(entry.getKey(), entry.getValue(), newElements);
        elements[i] = entry.getNext();
      }
    }

    elements = newElements;
  }

  private boolean shouldGrowHashMap(Object[] backingArray)
  {
    if (size > (loadFactor * backingArray.length))
    {
      return true;
    } else
    {
      return false;
    }
  }

  // @SuppressWarnings("unchecked")
  // public <V> K get(K key)
  // {
  // // 1. hash it
  // // 2. get an index
  // // 3. search for correct hashcode/key in Entry LinkedList
  // int hashCode = key.hashCode();
  //
  // int index = Math.abs(hashCode % elements.length);
  //
  // if (elements[index] != null)
  // {
  // Entry<K, V> node = (Entry<K, V>) elements[index];
  // Entry<K, V> next = node.getNext();
  // while (node != null)
  // {
  // if (node.getHashCode() == hashCode)
  // {
  // if (key.equals(node.getKey()))
  // {
  // return node.getKey();
  // }
  // }
  // node = next;
  // if (next != null)
  // next = next.getNext();
  // }
  // }
  // return null;
  //
  // }

  @SuppressWarnings("unchecked")
  public <V> K remove(K key)
  {
    int hashCode = key.hashCode();

    int index = Math.abs(hashCode % elements.length);

    if (elements[index] != null)
    {
      Entry<K, V> node = (Entry<K, V>) elements[index];
      Entry<K, V> next = node.getNext();
      Entry<K, V> prev = node;

      while (node != null)
      {
        if (node.getHashCode() == hashCode)
        {
          if (key.equals(node.getKey()))
          {
            K valueToReturn = node.getKey();
            if (prev == node)
            {
              elements[index] = node.getNext();
            } else
            {
              prev.setNext(node.getNext());
            }
            size--;
            return valueToReturn;
          }
        }
        prev = node;
        node = next;
        if (next != null)
          next = next.getNext();
      }
    }
    return null;
  }

  @Override
  public Iterator<K> iterator()
  {
    Iterator<K> it = new Iterator<K>() {

      int index = 0;
      
      @Override
      public boolean hasNext()
      {
        for (int i = index; i<elements.length; i++)
        {
          Entry ret = (Entry) elements[i];
          
          if (ret != null)
          {
            return true;
          }
        }
        
        return false;
      }

      @Override
      public K next()
      {
        while (index < elements.length)
        {
          Entry ret = (Entry) elements[index++];
          
          if (ret != null)
          {
            return (K) ret.getKey();
          }
        }
        
        return null;
      }
      
    };
    
    return it;
  }

}
