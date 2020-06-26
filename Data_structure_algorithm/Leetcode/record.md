# Leetcode 练习记录

## Linked list practice

* 206 Reverse Linked List
  * iteratively
    * 40 ms 15.3 MB python3
  * recursively
    * 28 ms	18.5 MB	python3
* 141 Linked List Cycle
  * 列表存储走过的节点
    * 1172 ms 5.4% faster 17 MB	100% memory less python3
  * 快慢指针
    * 52 ms	47% faster 16.9 MB 100% memory less python3
* 876 Middle of the Linked List
  * 列表存储走过的节点
    * 32 ms 40% faster	13.7 MB	7% memory less python3
  * 快慢指针
    * 32 ms	40% faster 14 MB 7% memory less	python3
* 24 Swap Nodes in Pairs
  * recursively
    * 16 ms 99.75% faster	13.8 MB	6.6% memory less python3
* 328 Odd Even Linked List
  * iteratiely
    * 36 ms	97% faster 15.9 MB 8.33% memory less python3
* 92 Reverse Linked List II
  * iteratively
    * 28 ms	82% faster 14 MB 7.4% memory less python3

待整理

---

## Tree

* 144 Binary Tree Preorder Traversal 进阶：迭代方法
  * recursively
    * 28 ms 73% faster	13.7 MB	91% memory less python3
* 94 Binary Tree Inorder Traversal
  * recursively
    * 32 ms	45% faster 14 MB 5% memory less	python3
* 145 Binary Tree Postorder Traversal
  * recursively
    * 32 ms	44% faster 13.8 MB	57% memory less python3
* 102 Binary Tree Level Order Traversal
  * 32 ms 80% faster	14 MB	72% memory less python3
* 107 Binary Tree Level Order Traversal II
  * 28 ms	57% faster 14.1 MB	87% memory less python3
* 103 Binary Tree Zigzag Level Order Traversal
  * 28 ms	88% faster 14.1 MB 13% memory less	python3

待整理

---

## Binary search

* 278 First Bad Version e
  * 二分查找
    ```
    Runtime: 14 ms, faster than 29.55% of Java online submissions for First Bad Version.
    Memory Usage: 35.9 MB, less than 80.52% of Java online submissions for First Bad Version.
    ```
* 35 Search Insert Position e
  * 二分查找
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
    Memory Usage: 41.2 MB, less than 5.06% of Java online submissions for Search Insert Position.
    ```
* 33 Search in Rotated Sorted Array m
  * 魔改版二分查找
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
    Memory Usage: 39.3 MB, less than 37.54% of Java online submissions for Search in Rotated Sorted Array.
    ```
* Search in Rotated Sorted Array II m
  * 可以用二分查找，但是有点复杂，试着直接一遍遍历，结果还挺好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array II.
    Memory Usage: 39.4 MB, less than 35.34% of Java online submissions for Search in Rotated Sorted Array II.
    ```
* Find Minimum in Rotated Sorted Array m
  * 直接一遍遍历，结果很好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    Memory Usage: 38.7 MB, less than 95.30% of Java online submissions for Find Minimum in Rotated Sorted Array.
    ```
* Find Minimum in Rotated Sorted Array II
  * 我裂开了，还是一遍遍历就行了。我怀疑他的测试用例是随机找的pivat点，所以点的分布更接近中心，平均下来可能是$N/2$，比起复杂条件的二分，直接一边遍历的开销甚至更小。
```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
Memory Usage: 39.5 MB, less than 36.19% of Java online submissions for Find Minimum in Rotated Sorted Array II.
```