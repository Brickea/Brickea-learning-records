from collections import deque
class EmptyError(Exception):
    '''
    自定义异常，说明此时栈空
    '''
class Stack(object):
    '''
    使用collections.deque实现栈
    '''
    def __init__(self):
    # 初始化函数
        self.doube_queue = deque()

    def __len__(self):
    # 长度魔法函数
        return len(self.doube_queue)

    def push(self,value):
    # 添加元素到栈
        self.doube_queue.append(value)

    def pop(self):
    # 依照 LIFO 原则取出元素
        if len(self) == 0:
            raise EmptyError('Stack is empty')
        return self.doube_queue.pop()


def test_stack():
    # stack 单元测试
    size = 10
    s = Stack()
    for i in range(size):
        s.push(i)

    assert len(s) == 10

    for i in range(size):
        assert (s.pop() + i) == (size -1)
    import pytest
    with pytest.raises(EmptyError) as excinfo:
        s.pop()
    assert 'Stack is empty' in str(excinfo.value)