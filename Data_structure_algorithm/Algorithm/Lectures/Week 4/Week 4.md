# Week 4 <!-- omit in toc -->

- [Overview](#overview)
- [Priority Queues](#priority-queues)
  - [API and elementary implementations](#api-and-elementary-implementations)
  - [Priority queue applications](#priority-queue-applications)
  - [Priority queue example](#priority-queue-example)
    - [Easy client](#easy-client)
  - [Priority queue: unordered and ordered array implementation](#priority-queue-unordered-and-ordered-array-implementation)
  - [Binary Heaps](#binary-heaps)
  - [Complete binary tree](#complete-binary-tree)
  - [Binary heap representations](#binary-heap-representations)
    - [Basic set up](#basic-set-up)
    - [Promotion in a heap](#promotion-in-a-heap)
    - [Insertion in a leap](#insertion-in-a-leap)
    - [Demotion in a heap](#demotion-in-a-heap)
    - [Delete the maximum in a leap](#delete-the-maximum-in-a-leap)
  - [Binary heap: JAVA implementation](#binary-heap-java-implementation)
  - [Binary heap consideration](#binary-heap-consideration)
    - [Immutability in JAVA](#immutability-in-java)
- [Heapsort](#heapsort)
  - [Heap construction](#heap-construction)
  - [Sortdown](#sortdown)
  - [JAVA implementation](#java-implementation)
  - [Heapsort Mathematical analysis](#heapsort-mathematical-analysis)
- [Sorting algorithm summary](#sorting-algorithm-summary)

## Overview

*This week we are going to introduce two fundamental data types, address the challenges of developing algorithms and data structures that can serve as the basis of efficient implementations, and try to convince you that such implementations enable solution of a broad range of applications problems that could not be solved without them.*

**Lecture: Priority Queues.** We introduce the priority queue data type and an efficient implementation using the binary heap data structure. This implementation also leads to an efficient sorting algorithm known as heapsort. We conclude with an applications of priority queues where we simulate the motion of NN particles subject to the laws of elastic collision.

**Lecture: Elementary Symbol Tables.** We define an API for symbol tables (also known as associative arrays) and describe two elementary implementations using a sorted array (binary search) and an unordered list (sequential search). When the keys are Comparable, we define an extended API that includes the additional methods min, max floor, ceiling, rank, and select. To develop an efficient implementation of this API, we study the binary search tree data structure and analyze its performance.

**Programming Assignment:** 8-Puzzle. Your programming assignment is to implement the famous A* search algorithm to solve a combinatorial problem, and to substantially speed it up with an efficient priority queue implementation.

Job Interview Questions. Algorithmic interview questions based on the lecture material.

Suggested Readings. Section [2.4](https://algs4.cs.princeton.edu/24pq/), [3.1](https://algs4.cs.princeton.edu/31elementary/), and [3.2](https://algs4.cs.princeton.edu/31elementary/) in Algorithms, 4th edition.

## Priority Queues

### API and elementary implementations

**Colleactions** Insert and delete items. Which item to delete?

* **Stack** Remove the item most recently added
* **Queue** Remove the item least recently added
* **Randomized queue** Remove a random item
* **Priority queue** Remove the largest or smallest item

**Requirement** Generic items are ```Comparable```

```java
public class MaxPQ<Key extends Comparable<Key>>{
    public MaxPQ() //Create an empty priority queue

    public MaxPQ(Key[] a) //Create a priority queue with given keys

    public void insert(Key v) //insert a key into the priority queue

    public Key delMax() // return and remove the largest key

    public boolean isEmpty() // is the priority queue empty?

    public Key max() // return the largest key

    public int size() // number of entries in the priority queue
}
```

### Priority queue applications

![](Priority%20Queues/priorityQueuesApplication.png)

### Priority queue example

**Challenge** Find the largest $M$ items is a stream of $N$ items ($N$ huge, $M$ large)
* Fraud detection: isolate $$ transactions.
* File maintenance: find biggest files or directories.

**Constraint** Not enough memory to store $N$ items.

#### Easy client

```java
MinPQ<Transaction> pq = new MinPQ<Transaction>();// transaction data type is Comparable

while(StdIn.hasNextLine()){
  String line = StdIn.readLine();
  Transaction item = new Transaction(line);
  pq.insert(item);
  if(pq.size()>M){ // pd contains largest M items
    pq.delMin();
  }
}
```

|implementation|time|space|info|
|--|--|--|--|
sort|$N\log N$|$N$|we sort first and choose the last element.However we don't have enought$N$ space to store that|
elementary PQ|$MN$|$M$|This will be a better solution if we get huge $N$. However if our $M$ is very large, the time cost will be very big|
binary heap|$N \log M$|$M$|This is good solution, and the time cost will be very close to the best theory|
best in theory|$N$|$M$||

### Priority queue: unordered and ordered array implementation

**unordered**
* Only add the element in the end of data structure
* If we want to remove the max(min), we need to iterate all data structure

**ordered**
* When adding element, we need to iterate the data structure in order to make sure the data is ordered
* If we want to remove the max(min), we just need to remove the last item.

![](Priority%20Queues/PriorityQueuesOrderUnorder.png)

|implementation|insert|del max|max|
|--|--|--|--|
unordered array|$1$|$N$|$N$|
ordered array|$N$|$1$|$1$|
goal|$\log N$|$\log N$|$\log N$|

### Binary Heaps

### Complete binary tree

**Binary tree** Empty or node with links to left and right binary trees

**Complete** tree Perfectly balanced, except for bottom level.

![](Priority%20Queues/binaryTree.png)

**Propert** Height of complete tree with $N$ nodes is $\lg N$

### Binary heap representations

#### Basic set up

**Binary heap** Array representation of a heap-ordered complete binary tree

**Heap-ordered binary tree**
* Keys in nodes
* Parent's key no smaller than children's keys

**Array representation**
* indices start at 1
* Take nodes in level order
* No explicit links needed!

![](Priority%20Queues/binaryHeap.png)

**Proposition** Largest key is ```a[1]```, which is root of binary tree

**Proposition** Can use array indices to move through tree
* Parent of node at ```k``` is at ```k/2```
* Children of node at ```k``` are at ```2k``` and ```2k+1```

#### Promotion in a heap

**Scenario** child's key becomes larger key than its parent's key

**To eliminate the violation**
* Exchange key in child with key in parent
* Repeat until heap order restored.

![](Priority%20Queues/promotionHeap.png)

```java
private void swin(int k){
  while(k>1&&less(k/2,k)){
    exch(k,k/2);
    k = k/2;
  }
}
```

#### Insertion in a leap

**insert** add node at end, then swim it up

**Cost** At most $1+\lg N$ comapres

```java
public void insert(Key x){
  pq[++N] = x;
  swim(N);
}
```

![](Priority%20Queues/insertHeap.png)

#### Demotion in a heap

**Scenario** Parent's key becomes smaller than one (or both) of its children's

**To eliminate the violation**
* Exchange key in parent with key in larger child
* Repeat until heap order restored.

![](Priority%20Queues/demotionHeap.png)

```java
private void sink(int k){
  while(2*k <= N>{
    int j = 2*k;
    if(j<N&&less(j,j+1)) j++; // find the bigger one from children of node k
    if(!less(k,j)) break;
    exch(k,j);
    k = j;
  })
}
```

#### Delete the maximum in a leap

**Delete** max: Exchange root with node at end, then sink it down

**Cost** At most $2 \lg N$ compares

![](Priority%20Queues/deleteMaxHeap.png)

```java
public Key delMax(){
  Key max = pq[1];
  exch(1,N--);
  sink(1);
  pq[N+1] = null;
  return max;
}
```

### Binary heap: JAVA implementation

```java
public class MaxPQ<Key extends Comparable<Key>>{
  private Key[] pd;
  private int N;

  public MaxPQ(int capacity){
    pq = (Key[]) new Comparable[capacity+1];
  }

  public boolean isEmpty(){
    return N == 0;
  }

  public void insert(Key key)
  public Key delMax()

  private void swin(int k)
  private void sink(int k)

  private boolean less(int i,int j){
    return pd[i].compareTo(pd[j]) < 0;
  }

  private void exch(int i, int j){
    Key t = pq[i];
    pq[i] = pq[j];
    pq[j] = t;
  }
}
```

|Implementation|insert|del max|max|
|--|--|--|--|
unordered array|$1$|$N$|$N$|
ordered array|$N$|$1$|$1$|
binary heap|$\log N$|$\log N$|$1$|
d-ary heap|$\log_d N$|$\log_d N$|$1$|
Fibonacci (complex in practice)|$1$|$\log N$|$1$|

### Binary heap consideration

**Immutability** of keys
* Assumption: client does not change keys while they're on the PQ
* Best practice: use immutable keys.

**Underflow and overflow**
* Underflow: throw exception if deleting from empty PQ.
* Overflow: add no-arg constructor and use resizing array.

**Minimum-oriented priority queue**
* Replace ```less()``` with ```greater()```
* Implement ```greater()```

**Other operations**
* Remove an arbitrary item
* Change the priority of an item

#### Immutability in JAVA

**Data type** Set of values and operations on those values

**Immutable data tpye** Can't change the data type value once created.

```java
public final class Vector{
  // can't override instance methods

  // all instance variables private and final
  private final int N;
  private final double[] data;

  public Vector(double[] data){
    this.N = data.length;
    // defensive copy of mutable instance variables
    this.data = new double[N];
    for(int i=0;i<N;i++){
      this.data[i] = data[i];
    }
  }
  ...
}
```

**Immutable** String, Integer, Double,Color, Vector, Transaction, Point2D

**Mutable** StringBuilder, Stack, Counter, Java array

## Heapsort

**Basic plan for in-place sort**
* Create max-heap with all $N$ keys
* Repeatedly remove the maximum key

![](Priority%20Queues/heapsort.png)

### Heap construction

**First pass** build heap using bottom-up method

```java
for(int k=N/2;k>=1;k--){
  sink(a,k,N);
}
```

![](Priority%20Queues/heapConstruction.png)

### Sortdown

**Second pass**
* Remove the maximum, one at a time
* Leave in array, instead of nulling out.

```java
while(N>1){
  exch(a,1,N);
  sink(a,1,--N);
}
```

![](Priority%20Queues/heapSortdown.png)

### JAVA implementation

```java
public class Heap{
  public static void sort(Comparable[] pq){
    int N = pq.length;
    // bottom-up construction
    for(int k=N/2;k>=1;k--){
      sink(pq,k,N);
    }

    // sortdown
    while(N>1){
      exch(pq,1,N);
      sink(pq,1,--N);
    }
  }

  private static void sink(Comparable[] pq, int k, int N)

  private static boolean less(Comparable[] pq,int i, int j)

  private static void exch(Comparable[] pq, int i, int j)

}
```

### Heapsort Mathematical analysis

![](Priority%20Queues/heapsortAnalysis.png)

## Sorting algorithm summary

![](Priority%20Queues/sortSummary.png)