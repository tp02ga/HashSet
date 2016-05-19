package hashset;

public class Entry <K, V>
{
  private int hashCode;
  private K key;
  private V value;
  private Entry<K, V> next;
  
  public Entry (int hashCode, K key, V value)
  {
    this.hashCode = hashCode;
    this.key = key;
    this.value = value;
  }
  
  public int getHashCode()
  {
    return hashCode;
  }
  public void setHashCode(int hashCode)
  {
    this.hashCode = hashCode;
  }
  public K getKey()
  {
    return key;
  }
  public void setKey(K key)
  {
    this.key = key;
  }
  public V getValue()
  {
    return value;
  }
  public void setValue(V value)
  {
    this.value = value;
  }
  public Entry<K, V> getNext()
  {
    return next;
  }
  public void setNext(Entry<K, V> next)
  {
    this.next = next;
  }
}
