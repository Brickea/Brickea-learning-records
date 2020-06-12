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
- [Elementary Sorts](#elementary-sorts)
  - [Rules of the game](#rules-of-the-game)
    - [Sort any type of data - Example](#sort-any-type-of-data---example)
    - [Sort any type of data - Callback](#sort-any-type-of-data---callback)
    - [Total order 全序关系](#total-order-全序关系)
    - [Comparable API](#comparable-api)
    - [Two Sorting abstraction](#two-sorting-abstraction)
    - [Sort Testing](#sort-testing)
  - [Selection sort 选择排序](#selection-sort-选择排序)
    - [Selection sort inner loop](#selection-sort-inner-loop)
    - [Selection sort Java implementation](#selection-sort-java-implementation)
    - [Selection sort mathematical analysis](#selection-sort-mathematical-analysis)
  - [Insertion sort](#insertion-sort)
    - [Insertion sort inner loop](#insertion-sort-inner-loop)
    - [Insertion sort Java implementation](#insertion-sort-java-implementation)
    - [Insertion sort mathematical analysis](#insertion-sort-mathematical-analysis)
      - [Insertion sort best and worst case](#insertion-sort-best-and-worst-case)
    - [Insertion sort in partially-sorted arrays (Has good performance!)](#insertion-sort-in-partially-sorted-arrays-has-good-performance)
  - [Shell sort](#shell-sort)
    - [H-sorting](#h-sorting)
    - [Which increment sequence to use?](#which-increment-sequence-to-use)
    - [Shell sort Java implementation](#shell-sort-java-implementation)
    - [Shell sort mathematical analysis](#shell-sort-mathematical-analysis)
    - [Why we interested in shellsort?](#why-we-interested-in-shellsort)
- [Sort Application](#sort-application)
  - [Shuffle sort](#shuffle-sort)
    - [Shuffle without cost of sort - Knuth Shuffle](#shuffle-without-cost-of-sort---knuth-shuffle)
    - [Shuffle bad example](#shuffle-bad-example)
- [Sort Application - Convex hull](#sort-application---convex-hull)
  - [Human Method to find convex hull](#human-method-to-find-convex-hull)
  - [Convex hull applications](#convex-hull-applications)
    - [Robot motion planning](#robot-motion-planning)
    - [Farthest pair](#farthest-pair)
  - [Convex hull geometric properties](#convex-hull-geometric-properties)
    - [Graham scan 葛立恒扫描法](#graham-scan-葛立恒扫描法)
      - [Implementing ccw](#implementing-ccw)
    - [Graham scan implementation](#graham-scan-implementation)

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

## Elementary Sorts

### Rules of the game

**Sort** Rearrange array of $N$ items into ascending order.

Ex: Student records in a university

#### Sort any type of data - Example

**Sample sort client 1**
* Sort random real numbers in ascending order.

```java
public class Experiment{
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for(int i=0;i<N;i++){
            a[i] = StdRandom.uniform();
        }
        Insertion.sort(a);
        for(int i=0;i<N;i++){
            StdOut.println(a[i]);
        }
    }
}
```

**Sample sort client 2**
* Sort strings from file in alphabetical order.

```java
public class StringSorter{
    public static void main(String[] args){
        String[] a = In.readStrings(args[0]);
        Insertion.sort(a);
        for(int i=0;i<a.length;i++){
            StdOut.println(a[i]);
        }
    }
}
```

**Sample sort client 3**
* Sort the file in a given directory by filename

```java
import java.io.File;
public class FileSorter{
    public static void main(String[] args){
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        Insertion.sort(files);
        for(int i=0;i<files.length;i++){
            StdOut.println(files[i].getName());
        }
    }
}
```

#### Sort any type of data - Callback

How can ```sort()``` know how to compare data of type ```Double```, ```String```, and ```java.io.FIle``` without any information about the type of an item's key?

**Callback = reference to executable code**
* Client passes array of objects to ```sort()``` function
* The ```sort()``` function calls back object's ```compareTo()``` method as needed.

**Implementation callback**
* Java: interface
* C: function pointers
* C++: class-type functors

**Take this client as example**
```java
import java.io.File;
public class FileSorter{
    public static void main(String[] args){
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        Insertion.sort(files); // here it use the sort algorithm we implemented.
        for(int i=0;i<files.length;i++){
            StdOut.println(files[i].getName());
        }
    }
}
```

Then we can use ```Comparable interface```

```java
public interface Comparable<Item>{
    public int compareTo(Item that);
}
```

**Object Implementation**
```java
public class File implements Comparable<File>{
    ...
    public int compareTo(File b){
        ...
        return -1; // <
        ...
        return 1; // >
        ...
        return 0; // =
    }
}
```

Then in our sort implementation
```java
public static void sort(Comparable[] a){
    int N = a.length;
    for(int i=0;i<N;i++){
        for(int j=i;j<N;j++){
            if(a[j].compareTo(a[j-1])<0){
                exch(a,j,j-1);
            }
            else{
                break;
            }
        }
    }
}
```

#### Total order 全序关系

A total order is a binary relation $\leq$ that satisfies
* Antisymmetry: if $v \leq w$ and $w \leq v$, then $v = w$
* Transitivity: if $v \leq w$ and $w \leq x$, then $v \leq x$
* Totality: either $v\leq w$ or $w \leq v$ or both

**Ex**
* Standard order for natural and real number
* Alphabetical order for strings
* Chronological order for dates.

#### Comparable API

**Implement ```compareTo()``` so that v.compareTo(w)**
* Is a total order
* Returns a negative integer, zero, or positive integer if v is less than, equal to, or greater than w, respectively
* Throws an exception if incompatible types(or either is null)

**Built-in comparable types**: Integer, Double, String, Date, File, ...

**User-defined comparable types**: Implement the ```Comparable``` interface.

![](Elementary%20Sorts/res/comparable%20date.png)

#### Two Sorting abstraction

**Helper functions** Refer to data throught compares and exchanges

**Less** is item v less thant w?

```java
private static boolean less(Comparable v, Comparable w){
    return v.compareTo(w) <0;
}
```

**Exchange** Swap item in array ```a[]``` at index ```i``` with the one at index ```j```

```java
private static void exch(Comparable[] a,int i,int j){
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
}
```

**Question**

Consider the data type Temperature defined below. Which of the following required properties of the Comparable interface does the compareTo() method violate?
```java

public class Temperature implements Comparable<Temperature> {
    private final double degrees;
    
    public Temperature(double degrees) {
        if (Double.isNaN(degrees))
            throw new IllegalArgumentException();
        this.degrees = degrees;
    }

    public int compareTo(Temperature that) {
        double EPSILON = 0.1;
        if (this.degrees < that.degrees - EPSILON) return -1;
        if (this.degrees > that.degrees + EPSILON) return +1;
        return 0;
    }
    ...
}
```
Correct 
Transitivity is violated. Suppose that a, b, and c refer to objects corresponding to temperatures of 10.16∘, 10.08∘, and 10.00 ∘, respectively. Then, a.compareTo(b) <= 0 a.compareTo(b) <= 0 and b.compareTo(c) <= 0 b.compareTo(c) <= 0, but a.compareTo(c) > 0 a.compareTo(c) > 0. For this reason, you must not introduce a fudge factor when comparing two floating-point numbers if you want to implement the Comparable interface.


#### Sort Testing

```java
private static boolean isSorted(Comparable[] a){
    for(int i=1;i<a.length;i++){
        if(less(a[i],a[i-1])) return false;
    }
}
```

### Selection sort 选择排序

**Main**
* In iteration ```i```, find index ```min``` of smallest remaining entry
* Swap ```a[i]``` and ```a[mim]```

**Algorithm** pointer scans from left to right

**Invariants**
* Entries the left of pointer (including pointer) fixed and in ascending order
* No entry to right of pointer is smaller than any entry to the left of pointer

![](Elementary%20Sorts/res/selectionSortInvariant.png)

#### Selection sort inner loop

![](Elementary%20Sorts/res/selectionSortInnerLoop.png)

#### Selection sort Java implementation

```java
public class Selection{
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int min = i;
            for(int j=i;j<N;j++){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    private static boolean less(Comparable v, Comparable w){/* as before*/}
    
    private static void exch(Comparable[] a,int i, int j){/* as before*/}
}
```

#### Selection sort mathematical analysis

**Proposition** Selection sort uses $(N-1)+(N-2)+...+1+0 \sim N^2/2$ compares and $N$ exchanges

![](Elementary%20Sorts/res/selectionSortMathematicalAnalysis.png)

**Running time insensitive to input** Quadratic time, even if input is sorted

**Data movement is minimal** Linear number of exchanges.

### Insertion sort

**Algorithm** pointer scans from left to right

**Invariants**
* Entries to the left of pointer (including pointer) are in ascending order.
* Entries to the right of pointer have not yet been seen.

![](Elementary%20Sorts/res/insertionSort.png)

#### Insertion sort inner loop

![](Elementary%20Sorts/res/insertionSortInnerLoop.png)

#### Insertion sort Java implementation

```java
public class Insertion{
    public static void sort(Comparable[] a){
        int N= a.length;
        for(int i=0;i<N;i++){
            for(int j=i;j>0;j--){
                if (less(a[j],a[j-1])){
                    exch(a[j],a[j-1]);
                }
                else{
                    break;
                }
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){/* as before*/}
    
    private static void exch(Comparable[] a,int i, int j){/* as before*/}
}
```

#### Insertion sort mathematical analysis

**Proposition** To sort a randomly-ordered array with distinct keys, insertion sort uses $\sim 1/4 N^2$ compares and $\sim 1/4 N^2$ exchanges on average.

**Pf** Expect each entry to move halfway back.

![](Elementary%20Sorts/res/insertionSortMathematicalAnalysis.png)

##### Insertion sort best and worst case

**Best case** If the array is in ascending order, insertion sort makes $N-1$ compares and 0 exchanges

**Worst case** If the array is in descending order ( and no duplicates), insertion sort makes $\sim1/2 N^2$ compares and $\sim1/2 N^2$ exchanges.

#### Insertion sort in partially-sorted arrays (Has good performance!)

**Def** An *inversion* is a pair of keys that are out of order.

```

A E E L M O T R X P S

```

T_R T_P T_S R_P X_P X_S are the inversions(6 inversions)

**Def** An array is *partially sorted* if the number of inversion is $\leq cN$
* Ex: A subarray of size 10 appended to a sorted subarray of size $N$
* Ex: An array of size $N$ with only 10 entries out of place.

**Proposition** For partially-sorted arrays, insertion sort runs in linear time

**Pf** Number of exchanges equals the number of inversions.

### Shell sort

**Idea** Move entries more than one position at a time by *h-sorting* the array

![](Elementary%20Sorts/res/shellSortOverview.png)

**Shellsort** ```[Shell 1959]``` *h-sorting* array for decreasing sequence of values of $h$

![](Elementary%20Sorts/res/shellSortExample.png)

#### H-sorting

**How to h-sort an array** Insertion sort, with stride length $h$

![](Elementary%20Sorts/res/shellSortExample.png)

在这里其实 h-sort很好理解，就是使用之前的insertion sort，但是不再是每次只从pointer往前回1步，现在是往前回 h 步

![](Elementary%20Sorts/res/shellSortExample2.png)

看这个例子，算法pointer从起始往后 h 步开始，第一次交换也就是在 M 和 E之间，后面的以此类推

**Why insertion sort**
* Big increments 会导致进行排序的子数组长度很小，这个时候任意的排序方式都可以获得较高的性能
* Small increment 较小的增量，因为之前的排序，大部分都是有序的子数组，而且我们知道insertion sort 对于这种部分有序的数组排序是由性能优势的

Complete shell sort example

![](Elementary%20Sorts/res/shellSortCompleteExample.png)

#### Which increment sequence to use?

**Powers of two** 1,2,4,8
* No. Because it will not compare even and odd before the 1-sort. This will result very bad performance

**Powers of two minus one** 1, 3, 7
* Maybe. This is Shell's idea in 1960

**Knuth $3x + 1$** 1,4,13,40
* Ok. Esasy to compute.

**Professor provide a good sequence** 1,5,19,41,109,209,505,929,2161...3905
* Good. Tough to beat in empirical studies.

#### Shell sort Java implementation

```java
public class Shell{
    public static void sort(Comparable[] a){
        int N = a.length;

        int h = 1;

        while( h< n/3) h = 3*h +1 // 3x+1 increment sequence

        while(h>=1){
            // h-sort the array
            for(int i=h;i<N;i++){
                for(int j=i;j>=h && less(a[j],a[j-h]);j-=h) {// insertion sort
                    exch(a,j,j-h)
                }
            }

            h = h/3 // move to next increment
        }
    }
    private static boolean less(Comparable v, Comparable w){/* as before*/}
    
    private static void exch(Comparable[] a,int i, int j){/* as before*/}
}
```

![](Elementary%20Sorts/res/shellSortTrace.png)

#### Shell sort mathematical analysis

**Proposition** The worst-case number of compares used by shellsort with the 3x+1 increments is $O(N^{3/2})$

**Property** Number of compares used by shellsort with the 3x+1 increments is at most by a small multiple of $N$ times the # of increments used.

![](Elementary%20Sorts/res/shellSortMathematicalAnalysis.png)

**Remark** Accurate model has not yet been discovered

#### Why we interested in shellsort?

**Example of simple idea leading to substantial performance gains**

**Useful in practice**
* Fast unless array size is huge
* Tiny, fixes footprint for code (used in embedded systems)
* Hardware sort prototype

**Simple algorithm, nontrivial performance, interesting questions**
* Asymptotic growth rate?
* Best sequence of increments
* Average-case performance

## Sort Application

### Shuffle sort

**Idea** 
* Generate a random real number for each array entry
* Sort the array

**Proposition** Shuffle sort produces a uniformly random permutation of the input array, provided no duplicate values.

**Drawback** Need to pay cost of sort?

#### Shuffle without cost of sort - Knuth Shuffle

**Goal** Rearrange array so that result is a uniformly random permutation in linear time

* In iteration ```i```, pick integer ```r``` between 0 and ```i``` uniformly at random
* Swap ```a[i]``` and ```a[r]```

```java
public class StdRandom{
    ...
    public static void shuffle(Object[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int r = StdRandom.uniform(i+1);
            // int r = StdRandom.uniform(N); this won't work
            exch(a,i,r);
        }
    }
}
```

Here the uniformly random number should picked between 0 and i , this is very important. If you only choose a random place for every entry in your array. That will not give you a uniformly distribute shuffle array.

#### Shuffle bad example

```
for i := 1 to 52 do begin
    r := random(51) + 1;
    swap := card[r];
    card[r] := card[i];
    card[i] := swap;
end;
```

* Bug1: Random number r never 52
* Bug2: Shuffle not uniform
* Bug3: random() use 32-bit seed. Less than all possible shuffles result($2^32 << 52!$)
* Bug4: Seed = milliseconds since midnight => 86.4 million shuffles.

**Exploit** After seeing 5 cards and synchronizing with server clock, can determine all future card in real time.

## Sort Application - Convex hull

The *convex hull* of a set of $N$ points is the smallest perimeter fence enclosing the points

![](Elementary%20Sorts/res/convexHull.png)

**Equivalent definitions**
* Smallest convex set containing all the points.
* Smallest area convex polygon enclosing the poitns.
* Convex polygon enclosing the points, whose vertices are points in set.

![](Elementary%20Sorts/res/convexHullOutput.png)

**Convex hull output** Sequence of vertices in counterclockwise order.

### Human Method to find convex hull

Hammer nails perpendicular to plane; stretch elastic rubber band around points.

![](Elementary%20Sorts/res/convexHullHuman.png)

### Convex hull applications

#### Robot motion planning

Find shortest path in the plane from s to t that avoids a polygonal obstacle

![](Elementary%20Sorts/res/robotMotionPlanning.png)

**Fact** Shortest path is either straight line from s to t or it is one of two  polygonal chins of convex hull

#### Farthest pair

Give $N$ points in the plane, find a pair of points with the largest Euclidean distance between them.

![](Elementary%20Sorts/res/farthestPair.png)

Farthest pair of points are extreme points on convex hull. 

### Convex hull geometric properties

We can start from these two properties if we want to program it.

* Can traverse the convex hull by making only conterclockwise turns
* The vertices of convex hull appear in increasing order of polar angle with respect to point p with lowest y-coordinate

![](Elementary%20Sorts/res/convexHullProperties.png)

#### Graham scan 葛立恒扫描法

* Choose point p with smallest y-coordinate
* Sort points by polar angle with p
* consider points in order; discard unless it create a ccw turn

**How to find point p with smallest y-coordinate?**
* Define a total order, comparing by y-coordinate

**How to sort points by polar angle with respect to p?**
* Define a total order for each point p

**How to determine whether $p_1 \rightarrow p_2 \rightarrow p_3$ is a conterclockwise trun?**
* Computational geometry

**How to sort efficiently**
* Mergesort sorts in $N \lg N$ time

##### Implementing ccw

**How to determine whether $p_1 \rightarrow p_2 \rightarrow p_3$ is a conterclockwise trun?**
* Computational geometry

![](Elementary%20Sorts/res/ccw.png)

![](Elementary%20Sorts/res/ccwImplementation.png)

```java
public class Point2D{
    private final double x;
    private final double y;

    public Point2D(double x,double y){
        this.x = x;
        this.y = y;
    }
    ...

    public static int ccw(Point2D a,Point2D b, Point2D c){
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if(area2 < 0) return -1; // clockwise
        else if (area2 > 0) return 1; //counter-clockwise
        else return 0; //collinear
    }
}
```

#### Graham scan implementation

**Simplifying assumptions** No three points on a line; at least 3 points.

```java
Stack<Point2D> hull = new Stack<Point>();

Arrays.sort(p,Point2D.Y_ORDER); // this will make p[0] to be the point with lowest y-coordinate
Arrays.sort(p,p[0].BY_POLAR_ORDER); // sort by polar angle with respect to p[0]

hull.push(p[0]);
hull.push(p[1]);

for(int i=2;i<n;i++){
    Point2D top = hull.pop();
    while(Point2D.ccw(hull.peek(),top,p[i]) <= 0)
        top = hull.pop();
    hull.push(top);
    hull.push(p[i]); // add p[i] to putative hull
}
```

**Running time** $N \lg N$ for sorting and linear for rest.

**Pf** $N\lg N$ for sorting; each point pushed and popped at most once.