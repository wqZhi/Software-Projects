// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * This class implements a hash table, which maps keys to values. Any non-null object can be used as
 * a key or as a value.
 *
 * @param <KeyType>   a generic type for key
 * @param <ValueType> a generic type for value
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private Bucket<KeyType, ValueType>[] table;// The bucket array where the nodes containing K-V
  // pairs are stored
  private int size; // numbers of pairs stored
  private int capacity;// size of the bucket array

  /**
   * Constructs a new, empty hashtable with a default initial capacity (10).
   */
  public HashTableMap() {// with default capacity = 10
    this.capacity = 10;
    table = new Bucket[this.capacity];
  }

  /**
   * Constructs a new, empty hashtable with the specified initial capacity.
   *
   * @param capacity the initial capacity of the hashtable
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    table = new Bucket[this.capacity];
  }

  /**
   * This method is to store new values in your hash table at the index corresponding to the (
   * absolute value of your key's hashCode() ) modulus the HashTableMap's current capacity.
   *
   * @param key   the hashtable key
   * @param value the value
   * @return false, if a key that is null or is already in hash table; true, after successfully
   *         storing a new key-value pair.
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (key == null || value == null) {
      return false;
    }
    int index = hashCode(key);

    Bucket<KeyType, ValueType> newNode = new Bucket<>(key, value);
    Bucket<KeyType, ValueType> head = table[index];

    // add newNode at front of the list
    newNode.next = head;
    table[index] = newNode;
    size++;

    if (1.0 * size() / this.capacity >= 0.85) {
      generateBiggerArray();
    }

    return true;
  }

  /**
   * This method is to grow by doubling capacity and rehashing, whenever its load capacity becomes
   * greater than or equal to 85%.
   *
   */
  public void generateBiggerArray() {
    Bucket<KeyType, ValueType>[] temp = table;
    this.capacity = table.length * 2;
    clear();

    // Rehashing
    for (int i = 0; i < temp.length; i++) {
      Bucket<KeyType, ValueType> head = temp[i];

      while (head != null) {
        put(head.key, head.val);
        head = head.next;
      }
    }

  }

  /**
   * This method is to check whether the table contains the specified key or not.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to the specified key if found in the table.
   * @throws NoSuchElementException if the specified key is null or not find in table
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) {
      throw new NoSuchElementException("Error with key");
    }

    int index = hashCode(key);
    Bucket<KeyType, ValueType> head = table[index];
    while (head != null) {
      if (head.key.equals(key)) {
        return head.val;
      }
      head = head.next;
    }

    return null;
  }

  /**
   * This method is to test if the specified object is a key in this hashtable.
   *
   * @param key possible key
   * @return true if and only if the specified object is a key in this hashtable, as determined by
   *         the equals method; false otherwise.
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    }

    int index = hashCode(key);
    Bucket<KeyType, ValueType> head = table[index];
    while (head != null) {
      if (head.key.equals(key)) {
        return true;
      }
      head = head.next;
    }

    return false;
  }

  /**
   * This method removes the mapping for a key from this map if it is present (optional operation).
   *
   * @param key the key that needs to be removed
   * @return a reference to the value associated with the key that is being removed; null,
   *         otherwise, when the key being removed does not exist
   */
  @Override
  public ValueType remove(KeyType key) {
    if (!containsKey(key)) {
      return null;
    }

    int index = hashCode(key);

    if (table[index].key.equals(key)) {
      ValueType retVal = table[index].val;
      table[index] = table[index].next;
      size--;
      return retVal;
    } else {
      Bucket<KeyType, ValueType> currVal = table[index];
      Bucket<KeyType, ValueType> nextVal = table[index].next;

      if (nextVal == null) {
        return null;
      } else {
        while (nextVal != null) {
          if (nextVal.key.equals(key)) {
            currVal.next = nextVal.next;
            size--;
            return nextVal.val;
          }

          currVal = nextVal;
          nextVal = nextVal.next;
        }
      }
    }

    return null;
  }

  /**
   * This method returns the number of key-value pairs stored in this collection
   *
   * @return the number of keys in this hashtable.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * This method include a clear method that removes all key-value pairs from this collection.
   */
  @Override
  public void clear() {
    this.size = 0;
    table = new Bucket[this.capacity];
  }

  /**
   * Returns the hash code value for this table.
   *
   * @param key possible key
   * @return a hash code value for this object.
   */
  private int hashCode(KeyType key) {
    int index = Math.abs(key.hashCode()) % (this.capacity);
    return index;
  }

  /**
   * This method will get a singly linked list bucket and its head.
   *
   * @param key possible key
   * @return a singly linked list bucket
   */
  public Bucket getHead(KeyType key) {
    return table[hashCode(key)];
  }

  /**
   * This method returns the capacity of this table
   *
   * @return the table's capacity value.
   */
  public int getCapacity() {
    return this.capacity;
  }

}
