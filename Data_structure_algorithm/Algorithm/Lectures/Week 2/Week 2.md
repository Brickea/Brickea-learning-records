# Week 2 <!-- omit in toc -->

- [Overview](#overview)
- [Stacks and queues](#stacks-and-queues)
  - [Stack API - Stack of strings data type](#stack-api---stack-of-strings-data-type)
    - [Practice client - reverse the input string](#practice-client---reverse-the-input-string)
  - [Stack Linked-list representation](#stack-linked-list-representation)
  - [Stack linked-list implementation](#stack-linked-list-implementation)
  - [Stack linke-list performance](#stack-linke-list-performance)
  - [Stack Array implementation](#stack-array-implementation)
  - [Stack array considerations](#stack-array-considerations)
  - [Stack resizing-array implementation](#stack-resizing-array-implementation)
    - [Growing array - repeated doubling](#growing-array---repeated-doubling)
    - [Shrinking array - one-quarter shrink](#shrinking-array---one-quarter-shrink)
  - [Stack resizing-array implementation performance](#stack-resizing-array-implementation-performance)
  - [linked-list vs resizing array](#linked-list-vs-resizing-array)
  - [Queue API](#queue-api)
  - [Queue linked-list representation](#queue-linked-list-representation)
  - [Queue resizing array implementation](#queue-resizing-array-implementation)
  - [Bag API](#bag-api)
- [Generic 泛化](#generic-泛化)
  - [Generic stack: linked-list implementation](#generic-stack-linked-list-implementation)
  - [Generic stack: Array implementation](#generic-stack-array-implementation)
  - [Generic data type: autoboxing](#generic-data-type-autoboxing)
- [Iterators](#iterators)
  - [Stack iterator: linked-list implementation](#stack-iterator-linked-list-implementation)
  - [Stack iterator: Array implementation](#stack-iterator-array-implementation)
  - [Question](#question)
- [Application](#application)
  - [Java collections library](#java-collections-library)
  - [Stack Application](#stack-application)
    - [Arithmetic expression evaluation](#arithmetic-expression-evaluation)

## Overview

*You may be familiar with several of the algorithms and data structures that we consider this week, but perhaps not with our approach to data abstraction and Java language mechanisms for implementing them, so it's worthwhile to pay close attention. In the week's first lecture, we consider robust implementations for stacks and queues. In the week's second lecture, we begin our study of sorting algorithms. In both cases, we consider applications that illustrate the efficacy of careful modular programming when implementing algorithms.*

**Lecture: Stacks and Queues.** We consider two fundamental data types for storing collections of objects: the stack and the queue. We implement each using either a singly-linked list or a resizing array. We introduce two advanced Java features—generics and iterators—that simplify client code. Finally, we consider various applications of stacks and queues ranging from parsing arithmetic expressions to simulating queueing systems.

**Lecture: Elementary Sorts.** We introduce the sorting problem and Java's Comparable interface. We study two elementary sorting methods (selection sort andinsertion sort) and a variation of one of them (shellsort). We also consider two algorithms for uniformly shuffling an array. We conclude with an application of sorting to computing the convex hull via the Graham scan algorithm.

**Exercises.** (Sorry, we are still waiting for Coursera to migrate the exercises from the old platform.) Drill exercises on the lecture material.

**Programming Assignment: Deques and Randomized Queues.** Your programming assignment will involve developing implementations of two conceptually simple "collection" data types—the deque and the randomized queue---which are quite useful in practice. Properly implementing these data types will require using a linked data structure for one and a resizing array for the other.

**Job Interview Questions.** Algorithmic interview questions based on the lecture material.

**Suggested Readings.** Section [1.3](https://algs4.cs.princeton.edu/13stacks/) and [2.1](https://algs4.cs.princeton.edu/21elementary/) in Algorithms, 4th edition.

## Stacks and queues

**Fundamental data types**
* Value: Collection of objects
* Operations: insert, remove, iterate, test if empty
* Intent is clear when we insert
* Which item do we remove?

![](Stacks%20and%20QUeues/res/stack%20and%20queue.png)

**Stack** Examine the item most recently added
* LIFO

**Queue** Examine the item least recently added
* FIFO 

### Stack API - Stack of strings data type

```java
public class StackOfStrings(){
    public StackOfStrings() // create an empty stack
    public void push(String item) // insert a new string onto stack
    public String pop() // remove and return the string most recently added
    public boolean isEmpty() // is the stack empty?
}
```

#### Practice client - reverse the input string

```java
public static void main(String[] args){
    StackOfString stack = new StackOfString()
    while(!StdIn.isEmpty()){
        String s = StdIn.readString();
        if(s.equals("-")) StdOut.print(stack.pop());
        else stack.push(s);
    }
}
```

### Stack Linked-list representation

Maintain pointer to first node in a linked list; insert/remove from front.

![](Stacks%20and%20QUeues/res/stack%20linked%20list.png)

As linked-list structure, we will need a **inner class** in order to maintain the stack.

```java
private class Node{
    String item;
    Node next;
}
```

### Stack linked-list implementation

* pop()
  * Save item to return
  ```java
  String item = first.item;
  ```
  * Delete first node
  ```java
  first = first.next
  ```
  * Return saved item
  ```java
  return item;
  ```
* push()
  * Save a link to the list
   ```java
   Node oldfirst = first;
   ```
  * Create a new node for the begining
  ```java
  first = new Node();
  ```
  * Set the instance variables in the new node
  ```java
  first.item = 'new things';
  first.next = oldfirst;
  ```

Complete java implementation

```java
public class LinkedStackOfStrings(){
    private class Node{
        String item;
        Node next;
    }
    private Node first = null;

    public void push(String item){
        Node oldfirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldfirst;
    }

    public String pop(){
        String item = this.first.item;
        this.first = this.first.next;
        return item
    }
}
```

### Stack linke-list performance

**Proposition** Every operation takes constant time in the worst case.

**Proposition** A stack with $N$ items uses ~ $40N$  bytes.

![](Stacks%20and%20QUeues/res/stack%20linked-list%20memory%20used.png)

How do I know the memory usage in Java? [Click here](../Week%201/Week%201.md#memory)

**Remark** Analysis includes memory for the stack, but not the strings themselves, which the client owns.

### Stack Array implementation

**Array implementation of a stack**
* Use array ```s[]``` to store $N$ items on stack
* ```push()``` add new item at ```s[N]```
* ```pop()``` remove item from ```s[N-1]```

**Defect** Stack overflows when $N$ exceeds capacity

```java
public class FixedCapacityStackOfStrings(){
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity){
        this.s = new String[capacity]
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push(String item){
        this.s[N++] = item;
    }

    public String pop(){
        return this.s[--N];
    }
}
```

### Stack array considerations

**Overflow and underflow**
* Underflow: throw exception if pop from an empty stack
* Overflow: use resizing array for array implementation

**Null items** We allow null items to be inserted

**Loitering 游荡** Holding a reference to an object when it is no longer needed.

```java
public String pop(){
    String item = s[--N];
    s[N] = null;
    return item;
}
```

### Stack resizing-array implementation

**Problem** Requiring client to provide capacity does not implement API

So how can we grow and shrink array?

**First try**
* push() increase size of array by 1
* pop() decrease size of array by 1

**Too expensive**
* Need to copy all item to a new array
* Inserting first $N$ items takes time proportional to $1+2+...+N \sim N^2/2$

**Challenge** Ensure that array resizing happens infrequently

#### Growing array - repeated doubling

if array is full, create a new array of twice the size, and copy items
```java
public ResizingArrayStackOfStrings(){
    s = new String[1];

    public void push(String item){
        if(N == s.length) resize(2*s.length);
        s[N++] = item;
    }

    private void resize(int capacity){
        String[] copy = new String[capacity];
        for(int i=0;i<s.length;i++){
            copy[i] = s[i]
        }
        s = copy;
    }
}
```

Cost of inserting first N items. $N+(2+4+8+...+N) \sim 3N$

![](Stacks%20and%20QUeues/res/double%20resizing.png)

#### Shrinking array - one-quarter shrink

**First try**
* ```push()``` double size of array ```s[]``` when array is full
* ```pop()``` halve size of array ```s[]``` when array is one-half full

**Too expensive in worst case**
* Consider push-pop-push-pop sequence when array is full
* Each operation takes time proportional to $N$

**Efficient solution**
* ```push()``` double size of array ```s[]``` when array is full
* ```pop()``` halve size of array ```s[]``` when array is one-quarter full

```java
public String pop(){
    String item = s[N--];
    s[N] = null;
    if(N>0 && N==s.length/4) resize(s.length/2);
    return item;
}
```

**Invariant** Array is between 25% and 100% full.


### Stack resizing-array implementation performance

**Amorized analysis** Average running time per operation over a worst-case sequence of operations.

**Proposition** Starting from an empty stack, any sequence of $M$ push and pop operations takes time proportional to $M$.

![](Stacks%20and%20QUeues/res/stack%20array%20time%20cost.png)

The memory usage is also less the linked-list implementation

![](Stacks%20and%20QUeues/res/stack%20array%20memory%20usage.png)

### linked-list vs resizing array

**Tradeoffs** Can implement a stack with either resizing array or linked list; client can use interchangeably.

**linked-list implementation**
* Every operation takes constant time in the worst case.
* Uses extra time and space to deal with the links

**Resizing-array implementation**
* Every operation takes constant amortized time
* Less wasted space

So we usually have different for the same API. It's all depends on the device you are going to use.

### Queue API

```java
public class QueueOfStrings(){
    public QueueOfStrings() // create an empty queue

    public void enqueue(String item) //insert a new string onto queue

    public String dequeue() // remove and return the string least recently added

    public boolean isEmpty() // is the queue empty?
}

```

### Queue linked-list representation

Maintain pointer to first and last nodes in a linked list

insert/remove from opposite ends.

![](Stacks%20and%20QUeues/res/Queue%20linked%20list.png)

```java
// inner class
private class Node{
    String item;
    Node next;
} 
```

Dequeue

![](Stacks%20and%20QUeues/res/queueDequeue.png)

Enqueue

![](Stacks%20and%20QUeues/res/queueEnqueue.png)

```java
public class LinkedQueueOfStrings{
    private Node first, last;

    private class Node{
        String item;
        Node next;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public void enqueue(String item){
        Node oldLast = this.last;
        this.last = new Node()
        this.last.item = item;
        this.last.next = null;
        if this.isEmpty() this.first = this.last;
        else oldLast.next = this.last;
    }

    public String dequeue(){
        String item = this.first.item;
        this.first = this.first.next;
        if(this.isEmpty()) last = null;
        return item;
    }
}
```

### Queue resizing array implementation

* Use array ```q[]``` to store items in queue
* ```enqueue()``` add new item in the ```q[last]```
* ```dequeue()``` remove item in the ```q[first]```
* update first and last modulo the capacity
* add resizing array

### Bag API

![](Stacks%20and%20QUeues/res/bag.png)

## Generic 泛化

We implemented: StackOfStrings

We also want StackOfURLs, StackOfInts, StackOfVans, ...

Attemp 1: Implement a separate stack class for each type

Attemp 2: Implement a stack with items of type  Objects.
* Casting is required in cline
* Casting is error-prone, run-time error if types mismathc.

```java
StackOfObjects s = new StackOfObjects();

Apple a = new Apple();
Orange b = new Orange();

s.push(a);
s.push(b);

a = (Apple) (s.pop());
```

Attemp 3: Java generics.
* Avoid casting in client
* Discover type mismatch errors at compile-time instead of run-time

```java
Stack<Apple> s = new Stack<Apple>();
Apple a = new Apple();
Orange b = new Orange();
s.push(a);
s.push(b); // this will resault compile-time error
```

### Generic stack: linked-list implementation

```java

public class Stack<Item>{
    private Node first = null;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public void push(Item item){
        Node oldFirst = this.first;
        this.first = New Node();
        this.first.item = item;
        this.first.next = oldFirst;

    }

    public Item pop(){
        Item item = this.first.item;
        this.first = this.first.next;
        return item;
    }
}
```

### Generic stack: Array implementation

```java
public class FixedCapacityStack<Item>{
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity){
        // this.s = new Item[capacity]; However, in Java, it doesn't allow we create generic array.
        // so one valid approach is

        this.s = (Item []) new Object[capacity]; // 用强制类型转化来创建不定类型的数组
    }

    public boolean isEmpty(){
        return this.N == 0;
    }

    public void push(Item item){
        this.s[this.N++] = item;
    }

    public Item pop(){
        return this.s[--this.N];
    }
}
```

However, when you compile this code, you will get a warning like this.

![](Stacks%20and%20QUeues/res/genericWarning.png)

But it's fine. We will only use this kind of type transfer in this code. It will no longer appear in any following course.

### Generic data type: autoboxing

**What to do about primitive types?**

**Wrapper type**
* Each primitive type has a wrapper object type.
* Ex: ```Integer``` is wrapper type for int

**Autoboxing** Automatic cast between a primitive type and its warpper.

**Syntactic sugar**. Behind-the-scenes casting

```java
Stack<Integer> s  = new Stack<Integer>();
s.push(17); // s.push(new Integer(17));
int a = s.pop(); // int a = s.pop().intValue();
```

## Iterators

**Design challenge**. Support iteration over stack items by client without revealing the internal representation of the stack.

No matter array or linked-list we use to implement the stack.

**What is an Iteralbe interface**
* Has a method that returns an ```Iterator```

```java
public interface Iterable<Item>{
    Iterator<Item> iterator();
}
```

**What is an Iterator interface**
* Has methods ```hasNext()``` and ```next()```

```java
public interface Iterator<Item>{
    boolean hasNext();
    Item next();
    void remove(); // optional
}
```

**Why make data structures Iterable**
* Java support elegant client code

```java
// foreach statement
for(String s: stack){
    StdOut.println(s);
}

// equivalent code
Iterator<String> i = stack.iterator();
while(i.hasNext()){
    String s = i.next();
    StdOut.println(s);
}
```

### Stack iterator: linked-list implementation

Alert:

注意匿名类和内部类中的中的this。
有时候，我们会用到一些内部类和匿名类，如事件处理。当在匿名类中用this时，这个this则指的是匿名类或内部类本身。这时如果我们要使用外部类的方法和变量的话，则应该加上外部类的类名。

```java

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
    ...
    public Iterator<Item> iterator(){return new ListIterator();}

    private class ListIterator implements Iterator<Item>{
        private Node current = Stack.this.first;

        public public boolean hasNext(){
            return current != null;
        }

        public void remove(){
            /* not supported */
            // throw UnsupportedOPerationException
        }

        public Item next(){
            // throw NoSuchElementException if no more items in iteration
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
```

### Stack iterator: Array implementation

```java
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
    ...
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = Stack.this.N;

        public boolean hasNext(){return i>0;}

        public void remove(){/* not supported */}

        public Item next(){return this.s[--i];}
    }
}
```

### Question

Suppose that we copy the iterator code from our linked list and resizing array implementations of a stack to the corresponding implementations of a queue.

Which queue iterator(s) will correctly return the items in FIFO order?

```
The linked-list iterator will work without modification because the items in the linked list are ordered in FIFO order (which is the main reason we dequeue from the front and enqueue to the back instead of vice versa).

The array iterator will fail for two reasons. First, the the items should be iterated over in the opposite order. Second, the items won't typically be stored in the array as entries 0 to n-1n−1.
```

## Application

### Java collections library

**List interface** java.util.List is API for sequence of items.

```java

public interface List<Item> implements Iterable<Item>{
    public List() // Create an empty list

    public boolean isEmpty() // it the list empty

    public int size() // return number of items

    public void add(Item item) // append item to the end

    public Item get(int index) // return item at given index

    public Item remove(int index) //return and delete item at given index

    public boolean contains(Item item) // does the list contain the given item

    public Iterator<Item> iterator();
}
```

```java.util.ArrayList``` uses resizing array

```java.util.Stack```
* Supports ```push()```, ```pop()```, and iteration
* Also implements ```java.util.List``` interface from previous.
* But it is bloated and poorly-designed API. Best practice is use our implementations of Stack, Queue, and Bag.
* This is because the committee may add too many feature to the library API. It's very hard to estimate the performance of the library API

![](Stacks%20and%20QUeues/res/badDesign.png)


### Stack Application

* Parsing in a coompiler
* Java virtual machine
* Undo in a word processor
* Back button in a Web browser
* PostScript language for printers
* Implementing function call in a compiler

#### Arithmetic expression evaluation

**Goal** Evaluate infix expressions.

**Two-stack algorithm** ```E.W.Dijkstra```
* Value: push onto the value stack
* Operator: push onto the operator stack
* Left Parenthesis: ignore
* Right parenthesis: pop operator and two values; push the result of applying that operator to those values onto the operand stack

![](Stacks%20and%20QUeues/res/arithmetic.png)