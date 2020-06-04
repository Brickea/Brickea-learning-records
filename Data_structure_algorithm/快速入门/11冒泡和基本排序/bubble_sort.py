# -*- coding: utf-8 -*-
def bubble_sort(unsorted_array):
    # 冒泡排序法
    for i in range(len(unsorted_array)):
        for j in range(len(unsorted_array)-1-i):
            if unsorted_array[j]>unsorted_array[j+1]:
                temp = unsorted_array[j+1]
                unsorted_array[j+1] = unsorted_array[j]
                unsorted_array[j] = temp
    return unsorted_array

def test_bubble_sort():
    import random
    unsorted_array = list(range(10))
    random.shuffle(unsorted_array)
    print(unsorted_array)
    
    assert bubble_sort(unsorted_array) == sorted(unsorted_array)

