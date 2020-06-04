# -*- coding: utf-8 -*-
def insert_sort(unsorted_array):
    # 插入排序
    length = len(unsorted_array)
    for i in range(length):
        value = unsorted_array[i]
        po = i
        while po >0 and unsorted_array[po-1] > value:
            unsorted_array[po] = unsorted_array[po-1]
            po -=1
        unsorted_array[po] = value
    return unsorted_array

def test_insert_sort():
    import random
    unsorted_array = list(range(10))
    random.shuffle(unsorted_array)

    assert insert_sort(unsorted_array) == sorted(unsorted_array)
        