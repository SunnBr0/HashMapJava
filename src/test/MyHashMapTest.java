package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.MyHashMap;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MyHashMapTest {
    @Test
    public void SizeTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        Assert.assertEquals(0, TestHashMap.size());
        TestHashMap.put(1, "TEST");
        Assert.assertEquals(1, TestHashMap.size());
    }

    @Test
    public void GetTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        TestHashMap.put(1, "TEST_1");
        TestHashMap.put(2, "TEST_2");
        assertEquals("TEST_2", TestHashMap.get(2));
    }

    @Test
    public void ContainKeyTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        for (int i = 0; i < 100011; i++) {
            TestHashMap.put(i, "TEST_" + i);
        }
        Assert.assertTrue(TestHashMap.containKey(10001));
    }

    @Test
    public void ContainValueTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        for (int i = 0; i < 100011; i++) {
            TestHashMap.put(i, "TEST_" + i);
        }
        Assert.assertTrue(TestHashMap.containValue("TEST_3312"));
    }

    @Test
    public void RemoveTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        for (int i = 0; i < 100011; i++) {
            TestHashMap.put(i, "TEST_" + i);
        }
        Assert.assertTrue(TestHashMap.containValue("TEST_1221"));
        TestHashMap.remove(1221);
        Assert.assertFalse(TestHashMap.containValue("TEST_1221"));
    }

    @Test
    public void ClearTest() {
        MyHashMap<Integer, String> TestHashMap = new MyHashMap<>();
        for (int i = 0; i < 100011; i++) {
            TestHashMap.put(i, "TEST_" + i);
        }
        TestHashMap.clear();
        Assert.assertTrue(TestHashMap.isEmpty());
    }
}