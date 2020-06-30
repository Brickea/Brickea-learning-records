# Leetcode 练习记录<!-- omit in toc -->

- [Linked list practice](#linked-list-practice)
- [Tree](#tree)
- [Binary search](#binary-search)
- [String](#string)
- [Math](#math)
- [Array](#array)

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
* 81 Search in Rotated Sorted Array II m
  * 可以用二分查找，但是有点复杂，试着直接一遍遍历，结果还挺好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array II.
    Memory Usage: 39.4 MB, less than 35.34% of Java online submissions for Search in Rotated Sorted Array II.
    ```
* 153 Find Minimum in Rotated Sorted Array m
  * 直接一遍遍历，结果很好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    Memory Usage: 38.7 MB, less than 95.30% of Java online submissions for Find Minimum in Rotated Sorted Array.
    ```
* 154 Find Minimum in Rotated Sorted Array II h
  * 我裂开了，还是一遍遍历就行了。我怀疑他的测试用例是随机找的pivat点，所以点的分布更接近中心，平均下来可能是$N/2$，比起复杂条件的二分，直接一边遍历的开销甚至更小。
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    Memory Usage: 39.5 MB, less than 36.19% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    ```

## String

* 28 Implement strStr() e
  * What should we return when needle is an empty string? This is a great question to ask during an interview. For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
    ```
    Runtime: 1 ms, faster than 64.82% of Java online submissions for Implement strStr().
    Memory Usage: 39.4 MB, less than 23.61% of Java online submissions for Implement strStr().
    ```
* 14 Longest Common Prefix e
  * ```substring```
      ```
      Runtime: 3 ms, faster than 27.74% of Java online submissions for Longest Common Prefix.
      Memory Usage: 39.8 MB, less than 6.60% of Java online submissions for Longest Common Prefix.
      ```
* 58 Length of Last Word e
  * ```trim```
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Length of Last Word.
    Memory Usage: 37.5 MB, less than 74.80% of Java online submissions for Length of Last Word.
    ```
* 387 First Unique Character in a String e
  * Can be optimized
    ```
    Runtime: 37 ms, faster than 10.01% of Java online submissions for First Unique Character in a String.
    Memory Usage: 39.6 MB, less than 87.99% of Java online submissions for First Unique Character in a String.
    ```
* 383 Ransom Note e
  * ```HashMap, toCharArray```
    ```
    Runtime: 9 ms, faster than 46.81% of Java online submissions for Ransom Note.
    Memory Usage: 40.8 MB, less than 17.90% of Java online submissions for Ransom Note.
    ```
* 344 Reverse String e
  * Nothing to optimized
    ```
    Runtime: 1 ms, faster than 56.87% of Java online submissions for Reverse String.
    Memory Usage: 46.2 MB, less than 58.97% of Java online submissions for Reverse String.
    ```

## Math

* 7 Reverse Integer e
  * ```Math.pow```, ```Integer.MAX_VALUE```
    ```
    Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse Integer.
    Memory Usage: 36.6 MB, less than 76.60% of Java online submissions for Reverse Integer.
    ```
* 165 Compare Version Numbers m
  * ```str.split()```, ```\\.```, ```Integer.parseInt()```,```()? : ```
    ```
    Runtime: 1 ms, faster than 90.04% of Java online submissions for Compare Version Numbers.
    Memory Usage: 37.6 MB, less than 39.17% of Java online submissions for Compare Version Numbers.
    ```
* 66 Plus One e
  * Nothing to say
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Plus One.
    Memory Usage: 38.3 MB, less than 19.00% of Java online submissions for Plus One.
    ```
* 8 String to Integer (atoi) m
  * ```StringBuilder```
    ```
    Runtime: 4 ms, faster than 25.51% of Java online submissions for String to Integer (atoi).
    Memory Usage: 40.5 MB, less than 6.15% of Java online submissions for String to Integer (atoi).
    ```
* 258 Add Digits
  * Nothing to say
    ```
    Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Digits.
    Memory Usage: 36.9 MB, less than 47.08% of Java online submissions for Add Digits.
    ```
* 67 Add Binary
  * ```Integer.toBinaryString```, ```Integer.parseInt(str,radix)```, ```StringBuilder```, ```StringBuilder.reverse().toString()```
    ```
    Runtime: 2 ms, faster than 76.96% of Java online submissions for Add Binary.
    Memory Usage: 37.9 MB, less than 90.16% of Java online submissions for Add Binary.
    ```
## Array

* 27 Remove Element e
  * Nothing to say
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
    Memory Usage: 40 MB, less than 5.06% of Java online submissions for Remove Element.
    ```
* 26 Remove Duplicates from Sorted Array e
  * Nothing to say
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
    Memory Usage: 41.3 MB, less than 55.16% of Java online submissions for Remove Duplicates from Sorted Array.
    ```
* 80 Remove Duplicates from Sorted Array II m
  * Double pointers, pay attention to the condition
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
    Memory Usage: 39.6 MB, less than 59.90% of Java online submissions for Remove Duplicates from Sorted Array II.
    ```
* 189 Rotate Array e
  * Nothing to say
    ```
    Runtime: 248 ms, faster than 5.63% of Java online submissions for Rotate Array.
    Memory Usage: 39.5 MB, less than 98.82% of Java online submissions for Rotate Array.
    ```
* 41 First Missing Positive h (need to be improved)
  * Put the number into the position that the index is same as number. Then interate from the begining. Find the first one that not match the index.
    ```
    Runtime: 24 ms, faster than 5.26% of Java online submissions for First Missing Positive.
    Memory Usage: 40 MB, less than 5.07% of Java online submissions for First Missing Positive.
    ```
* 134 Gas Station m (need to be improved)
  * Nothing to say
    ```
    Runtime: 57 ms, faster than 15.64% of Java online submissions for Gas Station.
    Memory Usage: 39.6 MB, less than 79.80% of Java online submissions for Gas Station.
    ```