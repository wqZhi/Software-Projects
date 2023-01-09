// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class implements a singly linked list bucket.
 *
 * @param <KeyType>   a generic type for key
 * @param <ValueType> a generic type for value
 */
class Bucket<KeyType, ValueType> {
  public KeyType key;
  public ValueType val;
  public Bucket<KeyType, ValueType> next;

  /**
   * Constructs a new Bucket with with key and value.
   *
   * @param key a key from an object
   * @param val a value from an object
   */
  public Bucket(KeyType key, ValueType val) {
    this.key = key;
    this.val = val;
  }
}
