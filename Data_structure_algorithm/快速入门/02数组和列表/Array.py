class Array(object):
    '''
    list 实现定长 Array
    '''
    def __init__(self,size=32):
    # 初始化函数
        # 默认长度 Array 默认长度 32
        self.size = size
        # 初始化容器
        self._items = [None] * self.size

    def __getitem__(self,index):
    # 魔术方法 __getitem__ 实例通过下标访问的时候会执行
        return self._items[index]

    def __setitem__(self,index,value):
    # 魔术方法 同 __getitem__
        self._items[index] = value
    
    def __len__(self):
    # 魔术方法，返回定长
        return self.size

    def clear(self,value = None):
        for i in range(self.size):
            self._items[i] = value
 
    def __iter__(self):
    # 迭代器
        for item in self._items:
            yield item

def test_Array():
    # 单测
    
    array = Array(10)

    array[0] = 1
    array[1] = 2

    assert array[0] == 1

    assert len(array) == 10

    array.clear()

    assert array[0] == None