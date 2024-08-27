# Java Concepts Overview

## Java Shell Tool

The Java Shell Tool, also known as **JShell**, is an interactive command-line tool introduced in Java 9. It allows developers to execute Java code snippets, test code, and experiment with new features quickly without the need to write a full program or set up a project. It’s particularly useful for trying out small code snippets, testing APIs, and learning Java concepts interactively.

## Java Wrapper Class

A Java Wrapper Class is a class that provides an object representation of a primitive data type. Each of the eight primitive types in Java (int, char, double, etc.) has a corresponding wrapper class (Integer, Character, Double, etc.). These wrapper classes are useful when you need to use primitives in contexts that require objects, such as in collections like `ArrayList` or when you want to utilize methods that work with objects.

### Example:
- **Primitive Type:** int
- **Wrapper Class:** Integer

### Key Features of Wrapper Classes:
- **Conversion:** They allow conversion between primitives and their corresponding object types (autoboxing and unboxing).
- **Utilities:** They provide useful utility methods for manipulating and converting values. For example, `Integer.parseInt("123")` converts a `String` to an `int`.

---

## Method Overloading

**Method Overloading** in Java is a feature that allows a class to have more than one method with the same name but different parameter lists (i.e., different types, number, or order of parameters). Overloading enables methods to perform similar or related functions with different inputs, enhancing code readability and reusability.

### Key Points:
- **Different Parameters:** The methods must differ in the number of parameters, the types of parameters, or the order of parameters.
- **Same Method Name:** All overloaded methods share the same name but are distinguished by their parameter list.
- **Compile-time Polymorphism:** Method overloading is a form of compile-time polymorphism, where the method to be executed is determined at compile time based on the method signature.

## Static Members

### Key Characteristics:
- **Shared Across All Instances:** Static members belong to the class itself rather than to any specific instance. This means that all instances of the class share the same static variables or methods.
- **Accessed via Class Name:** Static members can be accessed directly using the class name, without creating an instance of the class.
- **Common Usage:** Static members are typically used for constants, utility functions, or properties that should be shared across all instances (e.g., `Math.PI`, `System.out.println()`).
- **Memory Management:** Static members are loaded into memory when the class is loaded and remain there throughout the program's execution.

## Object-Oriented Programming (OOP)

### Programming Paradigm:
OOP is a programming paradigm centered around the concept of objects. Objects represent real-world entities, combining data (attributes) and behavior (methods) into a single unit.

### Key Principles:
1. **Encapsulation:** Bundling data and methods that operate on that data within a single unit (class) and restricting access to some of the object's components.
2. **Inheritance:** The mechanism by which one class can inherit properties and behaviors (fields and methods) from another class.
3. **Polymorphism:** The ability for different classes to be treated as instances of the same class through inheritance, allowing for method overriding and interface implementation.
4. **Abstraction:** The process of hiding the complex implementation details and showing only the essential features of the object.

## Static vs. Non-Static

### Association:
- **Static Variable:** Belongs to the class itself, not to any particular object of the class. Shared across all instances of the class.
- **Non-Static (Instance) Variable:** Belongs to an instance of the class, meaning each object has its own copy of the variable.

### Memory Allocation:
- **Static Variable:** Allocated memory only once, when the class is loaded into memory.
- **Non-Static (Instance) Variable:** Memory is allocated when an object is created and freed when the object is destroyed.


Use static class or not:
![Screenshot from 2024-08-26 18-34-33](https://github.com/user-attachments/assets/07167463-7475-4c25-9356-dd8d6af5a033)

When you have a non-static meth, you have to create the class first: 
![Screenshot from 2024-08-26 18-43-50](https://github.com/user-attachments/assets/bbeb830f-11e5-4b00-b472-50354cbdcc37)

But for a static method inside a class, you do not need to declare it first:

![Screenshot from 2024-08-26 18-45-03](https://github.com/user-attachments/assets/f7faa408-bf92-4f1b-8cb2-f062a5b68f5f)



### Access:
- **Static Variable:** Can be accessed directly using the class name without needing to create an object.  
  Example: `ClassName.staticVariable;`
- **Non-Static (Instance) Variable:** Must be accessed through an object of the class.  
  Example: `objectName.instanceVariable;`

## Polymorphism

Polymorphism simply means “many forms”. In Java, it allows one interface to be used for a general class of actions. It enables a single method or object to take on many forms, depending on the context in which it is used.

### Types of Polymorphism:
1. **Compile-time Polymorphism (Method Overloading):**  
   - Define multiple methods with the same name but different parameter lists within the same class.
   - The correct method to call is determined at compile-time based on the method signature.
   
2. **Runtime Polymorphism (Method Overriding):**  
   - Allows a subclass to provide a specific implementation of a method that is already defined in its superclass.

## Wrapper Classes

Each primitive type in Java has a corresponding wrapper class (e.g., `int -> Integer`, `double -> Double`).

## Final

### Final Variables:
- **Constant Values:** When a variable is declared as final, it means that once the variable is assigned a value, it cannot be changed or reassigned.

### Final Methods:
- **Preventing Overriding:** When a method is declared as final, it cannot be overridden by subclasses.

## Abstract

### Abstract Class:
- **Cannot Be Instantiated:** An abstract class cannot be instantiated directly.
- **Contains Abstract and Non-Abstract Methods:** An abstract class can have both abstract methods (without a body) and concrete methods (with a body).

### Abstract Method:
- **No Implementation:** An abstract method is declared without an implementation.
- **Must Be in Abstract Class:** Abstract methods can only be declared inside an abstract class.

## Interface

Interfaces cannot have constructors because they cannot be instantiated. An interface only defines a contract that other classes must adhere to.

## Interface vs. Abstract Class vs. Concrete Class

### 1. Purpose:
- **Interface:** Defines a contract or a set of methods that implementing classes must provide.
- **Abstract Class:** Serves as a base class with partial implementation.
- **Concrete Class:** A fully implemented class that can be instantiated.

### 2. Method Implementation:
- **Interface:** Methods are abstract by default (before Java 8).
- **Abstract Class:** Can have both abstract and concrete methods.
- **Concrete Class:** All methods have full implementations.

### 3. Multiple Inheritance:
- **Interface:** A class can implement multiple interfaces.
- **Abstract Class:** A class can extend only one abstract class.
- **Concrete Class:** A concrete class can extend only one class but can implement multiple interfaces.

### 4. Fields:
- **Interface:** Can only contain static and final fields.
- **Abstract Class:** Can have instance variables and static fields.
- **Concrete Class:** Can have instance variables and static fields.

### 5. Constructors:
- **Interface:** Cannot have constructors.
- **Abstract Class:** Can have constructors to initialize fields of the abstract class.
- **Concrete Class:** Can have constructors to initialize its fields.

### 6. Instantiation:
- **Interface:** Cannot be instantiated directly.
- **Abstract Class:** Cannot be instantiated directly.
- **Concrete Class:** Can be instantiated directly.

### 7. When to Use:
- **Interface:** Define a contract that can be implemented by any class.
- **Abstract Class:** Share code among related classes while enforcing some methods to be overridden.
- **Concrete Class:** A fully functional class that can be instantiated.

## Generics

Generics in Java is a feature that allows you to define classes, interfaces, and methods with placeholder types, which can be specified when the class, interface, or method is used. This helps create more flexible and reusable code by enabling type safety without the need to specify specific data types.
. **Generic Interfaces:**
   - Interfaces can also be generic, allowing them to work with different types.
   - **Example:**
     ```java
     interface Container<T> {
         void add(T item);
         T get();
     }

     class StringContainer implements Container<String> {
         private String item;

         public void add(String item) {
             this.item = item;
         }

         public String get() {
             return item;
         }
     }
```
