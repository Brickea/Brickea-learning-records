# Week 3 <!-- omit in toc -->

- [Overview](#overview)
- [Priority Queues](#priority-queues)
  - [API and elementary implementations](#api-and-elementary-implementations)

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