package main;

import java.util.*;

/*A hashMap 
 * @param <K> type parameter for key.
 * @param <V> type parameter for value.
 */

public class MyHashMap<K, V> {

    private List<LinkedList<MyEntry<K, V>>> buckets;
    /*
     * Initial size
     */
    private static int defaultCapacity = 16;
    /*
     * initial load factor
     */
    private final double LOAD_FACTOR = 0.75;
    /*
     * Count the number of pairs added
     */
    private int size = 0;

    /*
     * Constructs a new Main.MyHashMap
     */

    public MyHashMap() {
        this(defaultCapacity);
    }

    public MyHashMap(int defaultCapacity) {
        buckets = new ArrayList<>(defaultCapacity);
        for (int i = 0; i < defaultCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    /*
     * Inserting an element with a key and value
     */
    public void put(K key, V value) {
        increaseCapacity();
        int bucketIndex = getBucket(key);
        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);
        for (MyEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setKey(key);
                return;
            }
        }
        MyEntry<K, V> myEntry = new MyEntry<>(key, value);
        size++;
        bucket.add(myEntry);
    }

    /*
     * Returns the value by key , or null
     */
    public V get(K key) {
        int bucketIndex = getBucket(key);
        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);
        for (MyEntry<K, V> entry : bucket) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /*
     * Delete the value by key
     */
    public void remove(K key) {
        int bucketIndex = getBucket(key);
        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);
        Iterator<MyEntry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            MyEntry<K, V> entry = iterator.next();
            if (key.equals(entry.getKey())) {
                iterator.remove();
                size--;
                return;
            }
        }
    }

    /*
     * Return the size value
     */
    public int size() {
        return size;
    }

    /**
     * Checks the key content of the map
     */
    public boolean containKey(K key) {
        int bucketIndex = getBucket(key);
        LinkedList<MyEntry<K, V>> bucket = buckets.get(bucketIndex);
        for (MyEntry<K, V> entry : bucket) {
            if (key.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    /*
     * Checks the value content of the map
     */
    public boolean containValue(V value) {
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Checks if map is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /*
     * Delete all values of map
     */
    public void clear() {
        buckets.clear();
        size = 0;
    }

    /*
     * Returns set of all map keys
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /*
     * Return collection of all map values
     */
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    /*
     * Return set of all map entries
     */
    public Set<MyEntry<K, V>> entrySet() {
        Set<MyEntry<K, V>> entries = new HashSet<>();
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            entries.addAll(bucket);
        }
        return entries;
    }

    /*
     * Get index of bucket
     */
    private int getBucket(K key) {
        return Math.abs(key.hashCode() % defaultCapacity);
    }

    /*
     * Expansion of the map if the coefficient exceeds
     * the threshold value
     */
    public void increaseCapacity() {
        if ((double) size() / defaultCapacity > LOAD_FACTOR) {
            int newCapacity = (int) (defaultCapacity * 1.5 + 1);
            List<LinkedList<MyEntry<K, V>>> newBuckets = new ArrayList<>(newCapacity);
            for (int i = 0; i < newCapacity; i++) {
                newBuckets.add(new LinkedList<>());
            }
            for (LinkedList<MyEntry<K, V>> bucket : buckets) {
                for (MyEntry<K, V> entry : bucket) {
                    int newBucketIndex = Math.abs(entry.getKey().hashCode() % defaultCapacity);
                    newBuckets.get(newBucketIndex).add(entry);
                }
            }
            buckets = newBuckets;
            defaultCapacity = newCapacity;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LinkedList<MyEntry<K, V>> bucket : buckets) {
            for (MyEntry<K, V> entry : bucket) {
                stringBuilder.append(entry + ", ");
            }
        }
        return stringBuilder.toString();
    }
}