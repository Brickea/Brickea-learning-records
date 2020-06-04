# -*- coding: utf-8 -*-
def binary_search(sorted_array, target):
    # 二分查找
    if not sorted_array:
        return -1
    begin = 0
    end = len(sorted_array)-1

    while begin <= end:
        mid = (begin+end)//2
        if sorted_array[mid] == target:
            return mid
        elif sorted_array[mid] > target:
            end = mid - 1
        else:
            begin = mid+1
    return -1

def test_binary_search():
    sorted_array = [1,2,3,4,5,6,7]
    assert binary_search(sorted_array,1) == 0
    assert binary_search(sorted_array,10) == -1
