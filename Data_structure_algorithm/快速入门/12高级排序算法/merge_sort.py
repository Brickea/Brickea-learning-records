# -*- coding: utf-8 -*-
def merge_sort(unsorted_array):
    # 归并排序
    length = len(unsorted_array)

    if length != 1:
        mid = length//2
        left = merge_sort(unsorted_array[:mid])
        right = merge_sort(unsorted_array[mid:])
        return merge_sorted_array(left,right)
    else:
        return unsorted_array

def merge_sorted_array(left,right):
    sorted_array = []
    left_len,right_len = len(left),len(right)
    left_index = right_index = 0
    while left_index < left_len and right_index < right_len:
        if left[left_index] < right[right_index]:
            sorted_array.append(left[left_index])
            left_index+=1
        else:
            sorted_array.append(right[right_index])
            right_index +=1
    while left_index < left_len:
        sorted_array.append(left[left_index])
        left_index+=1
    while right_index<right_len:
        sorted_array.append(right[right_index])
        right_index+=1
    return sorted_array

def test_merge_sort():
    a = list(range(10))
    import random
    random.shuffle(a)

    assert merge_sort(a) == sorted(a)