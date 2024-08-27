
### **ArrayList Example**
```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        
        // Printing the list
        System.out.println("ArrayList: " + fruits); // [Apple, Banana, Orange, Mango]
        
        // Finding an element
        String fruit = fruits.get(2);
        System.out.println("Found element: " + fruit); // Orange
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("ArrayList after removal: " + fruits); // [Apple, Orange, Mango]
    }
}
```
- **Order of Elements:** Maintains the insertion order.
- **Access Time:** Fast (O(1) for getting elements by index).

### **LinkedList Example**
```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> fruits = new LinkedList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");

        // Printing the list
        System.out.println("LinkedList: " + fruits); // [Apple, Banana, Orange, Mango]
        
        // Finding an element
        String fruit = fruits.get(2);
        System.out.println("Found element: " + fruit); // Orange
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("LinkedList after removal: " + fruits); // [Apple, Orange, Mango]
    }
}
```
- **Order of Elements:** Maintains the insertion order.
- **Access Time:** Slower than `ArrayList` (O(n) for accessing elements by index).

### **HashSet Example**
```java
import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> fruits = new HashSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");

        // Printing the set
        System.out.println("HashSet: " + fruits); // Order is not guaranteed
        
        // Finding an element
        boolean containsOrange = fruits.contains("Orange");
        System.out.println("Contains Orange? " + containsOrange); // true
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("HashSet after removal: " + fruits); // Order is not guaranteed
    }
}
```
- **Order of Elements:** Does not maintain any specific order.
- **Access Time:** Fast (O(1) for add, remove, and contains operations).

### **TreeSet Example**
```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<String> fruits = new TreeSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");

        // Printing the set
        System.out.println("TreeSet: " + fruits); // [Apple, Banana, Mango, Orange]
        
        // Finding an element
        boolean containsOrange = fruits.contains("Orange");
        System.out.println("Contains Orange? " + containsOrange); // true
        
        // Removing an element
        fruits.remove("Banana");
        System.out.println("TreeSet after removal: " + fruits); // [Apple, Mango, Orange]
    }
}
```
- **Order of Elements:** Maintains elements in natural order (ascending order).
- **Access Time:** Logarithmic time (O(log n) for add, remove, and contains operations).

### **HashMap Example**
```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> fruitCalories = new HashMap<>();

        // Adding elements
        fruitCalories.put("Apple", 95);
        fruitCalories.put("Banana", 105);
        fruitCalories.put("Orange", 62);
        fruitCalories.put("Mango", 99);

        // Printing the map
        System.out.println("HashMap: " + fruitCalories); // Order is not guaranteed
        
        // Finding an element
        int calories = fruitCalories.get("Orange");
        System.out.println("Calories in Orange: " + calories); // 62
        
        // Removing an element
        fruitCalories.remove("Banana");
        System.out.println("HashMap after removal: " + fruitCalories); // Order is not guaranteed
    }
}
```
- **Order of Elements:** Does not maintain any specific order.
- **Access Time:** Fast (O(1) for get, put, and remove operations).

### **TreeMap Example**
```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> fruitCalories = new TreeMap<>();

        // Adding elements
        fruitCalories.put("Apple", 95);
        fruitCalories.put("Banana", 105);
        fruitCalories.put("Orange", 62);
        fruitCalories.put("Mango", 99);

        // Printing the map
        System.out.println("TreeMap: " + fruitCalories); // {Apple=95, Banana=105, Mango=99, Orange=62}
        
        // Finding an element
        int calories = fruitCalories.get("Orange");
        System.out.println("Calories in Orange: " + calories); // 62
        
        // Removing an element
        fruitCalories.remove("Banana");
        System.out.println("TreeMap after removal: " + fruitCalories); // {Apple=95, Mango=99, Orange=62}
    }
}
```
- **Order of Elements:** Maintains keys in natural order (ascending order).
- **Access Time:** Logarithmic time (O(log n) for get, put, and remove operations).

### **Summary of Ordering and Performance:**

- **ArrayList:** Maintains insertion order, fast random access (O(1)), slower insertions and deletions in the middle.
- **LinkedList:** Maintains insertion order, slower random access (O(n)), fast insertions, and deletions (O(1)).
- **HashSet:** No guaranteed order, fast add/remove/find operations (O(1)).
- **TreeSet:** Maintains natural order, logarithmic time operations (O(log n)).
- **HashMap:** No guaranteed order, fast key-based operations (O(1)).
- **TreeMap:** Maintains natural order of keys, logarithmic time operations (O(log n)).

This should give you a comprehensive understanding of how these Java collections work in terms of adding, finding, and removing elements, along with their ordering behavior.
