# Interview Question

## Stacks and Queue

**Queue with two stacks.** Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.

```
If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this process, they're now back in order.
```

**Stack with max.** Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.

```
Use two stacks, one to store all of the items and a second stack to store the maximums.
```

**Java generics.** Explain why Java prohibits generic array creation.

```
to start, you need to understand that Java arrays are covariant but Java generics are not: that is, String[] is a subtype of Object[], but Stack<String> is not a subtype of Stack<Object>.
```