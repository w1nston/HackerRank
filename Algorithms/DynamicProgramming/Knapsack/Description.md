### Problem Statement

You are given an integer *k* as well as *n*, not necessarily distinct, integers. Define the **MULTSUM** of a multiset (defined below) to be the sum of all integers in the multiset. Then, for all multisets of the n integers, where one can insert a number more than once, you want to find one (or more in case of a tie) so that he **MULTSUM** is closest to, but less than or equal to *k*. Once you find this (or these, in case of a tie) output its **MULTSUM**.

Note: We define a multiset of the *n* integers as a set of numbers consisting of some, but not necessarily all, of the *n* numbers. Also note that one integer can be added to the multiset multiple times.

### Input Format

The first line contains **T** the number of test cases. 
For each test case: 
The first line contains *n* and *k*. 
The next line contains *n* integers, each ranging between 1 and 2000 (inclusive).

### Constraints

1 ≤ **T** ≤ 10 <br/>
1 ≤ *n* ≤ 2000 <br/>
1 ≤ *k* ≤ 2000 <br/>

### Output Format

Output **T** lines, the answer for each test case.

### Sample Input

```
2
3 12
1 6 9
5 9
3 4 4 4 8
```

### Sample Output

```
12
9
```

### Explanation

In the first test case, one can pick {6, 6}. In the second, we can pick {3,3,3}.
