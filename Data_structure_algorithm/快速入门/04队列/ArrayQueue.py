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

########################################
# Queue 实现
########################################
class FullError(Exception):
    '''
    自定义异常，说明此时队列已满
    '''
    pass

class EmptyError(Exception):
    '''
    自定义异常，说明此时队列已空
    '''
    pass

class Queue(object):
    '''
    用 Array 实现定长队列
    '''
    def __init__(self,maxsize=None):
    # 初始化函数
        self.maxsize, self.array = maxsize, Array(maxsize)
        # 初始化头尾位置
        self.head,self.tail = 0,0

    def __len__(self):
    # 返回长度
        return self.head - self.tail

    def push(self,value):
    # 添加元素到定长队列
        if len(self) >= self.maxsize:
            raise FullError('Queue is full!')
        self.array[self.head%self.maxsize] = value
        self.head+=1
    
    def pop(self):
    # 依据 FIFO 原则取出元素
        if len(self) == 0:
            raise EmptyError('Queue is empty!')
        value = self.array[self.tail%self.maxsize]
        self.tail += 1
        return value

def test_Queue():
    # 单测
    import pytest
    size = 10
    q = Queue(size)

    for i in range(size):
        q.push(i)

    with pytest.raises(FullError) as excinfo:
        q.push(1)
    assert 'Queue is full!' in str(excinfo.value)

    for i in range (size):
        assert q.pop() == i

    assert len(q) == 0

    q.push(100)

    assert q.pop() == 100