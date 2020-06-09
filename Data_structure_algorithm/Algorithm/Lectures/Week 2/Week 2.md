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

![](Stacks%20and%20QUeues/stack%20and%20queue.png)

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

![](Stacks%20and%20QUeues/stack%20linked%20list.png)

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

![](Stacks%20and%20QUeues/stack%20linked-list%20memory%20used.png)

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

![](Stacks%20and%20QUeues/double%20resizing.png)

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

![](Stacks%20and%20QUeues/stack%20array%20time%20cost.png)

The memory usage is also less the linked-list implementation

![](Stacks%20and%20QUeues/stack%20array%20memory%20usage.png)

### linked-list vs resizing array

**Tradeoffs** Can implement a stack with either resizing array or linked list; client can use interchangeably.

**linked-list implementation**
* Every operation takes constant time in the worst case.
* Uses extra time and space to deal with the links

**Resizing-array implementation**
* Every operation takes constant amortized time
* Less wasted space

So we usually have different for the same API. It's all depends on the device you are going to use.