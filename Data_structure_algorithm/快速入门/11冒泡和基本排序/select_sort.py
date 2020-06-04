# -*- coding: utf-8 -*-
def select_sort(unsorted_array):
    # 选择排序
    length = len(unsorted_array)
    for i in range(length):
        for j in range(i+1,length):
            if unsorted_array[j]<unsorted_array[i]:
                unsorted_array[j],unsorted_array[i] = unsorted_array[i],unsorted_array[j]
    return unsorted_array

def test_select_sort():
    import random
    unsorted_array = list(range(10))
    random.shuffle(unsorted_array)

    assert select_sort(unsorted_array) == sorted(unsorted_array)