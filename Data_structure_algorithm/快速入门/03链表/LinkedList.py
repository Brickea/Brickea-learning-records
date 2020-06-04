# -*- coding: utf-8 -*-
class Node(object):
    '''
    定义单链表存储节点 Node
    '''
    def __init__(self,value = None,next = None):
    # 初始化函数
        self.value,self.next = value,next


class LinkedList(object):
    '''
    定义一个单链表
    '''
    def __init__(self,maxsize = None):
    # 初始化函数
        # 不能允许单链表无限扩充
        self.maxsize = maxsize
        # 初始化跟节点，其next会指向头节点
        self.root = Node()
        # 初始化尾节点，其为root.next节点
        self.tail = self.root.next
        # 初始化当前单链表长度
        self.length = 0

    def __len__(self):
    # len 魔法函数，返回当前单链表长度
        return self.length

    def append(self,value):
    # 默认在尾节点添加新数据
        # 首先判断当前容量是否已满
        if self.maxsize is None or self.length > self.maxsize:
            raise Exception('linkedlist is full!')
        tail_node = self.tail
        new_node = Node(value)
        # 判断当前单链表是否已经有数据
        if tail_node is None:
            # 当前单链表没有数据
            self.root.next = new_node
        else :
            # 当前单链表已存数据
            self.tail.next = new_node
        # 更新尾节点
        self.tail = new_node
        # 更新长度
        self.length += 1

    def left_append(self,value):
    # 在头节点处添加数据
        # 同，先判断容量是否已满
        if self.maxsize is None and self.length > self.maxsize:
            raise Exception('linked list is full!')
        new_node = Node(value)
        # 判断当前单链表是否存数据
        head_node = self.root.next
        if head_node is None:
            # 没有存数据
            self.root.next = new_node
            self.tail = new_node
        else:
            # 已经存数据
            self.root.next = new_node
            new_node.next = head_node
        # 更新长度
        self.length += 1

    def iter_node(self):
    # node节点迭代器
        current_node = self.root.next
        while current_node is not self.tail:
            # 只要没到尾节点
            yield current_node
            current_node = current_node.next
        # 最后也要把尾节点返回
        yield current_node

    def __iter__(self):
    # 单链表数值迭代器
        for node in self.iter_node():
            yield node.value

    
    def find(self,value):
    # 查找相应数值，返回序列号
        index = 0
        for node in self.iter_node():
            # 遍历查找
            if node is not None and node.value == value:
                return index
            index +=1
        # 没找到
        return -1

    def remove(self,value):
    # 删除一个制定数值的节点
        pre_node = self.root
        current_node = self.root.next
        for current_node in self.iter_node():
            if current_node.value == value:
                # 找到对应数字
                if current_node == self.tail:
                    # 如果是尾节点
                    pre_node.next = None
                    del current_node
                    self.tail = pre_node
                else:
                    pre_node.next = current_node.next
                    del current_node
                # 更新长度
                self.length -= 1
                return 1
            pre_node = current_node
        return -1

    def clear(self):
    # 清空单链表
        self.root.next = None
        self.tail = self.root.next
        self.length = 0

def test_LinkedList():
    linkedlist = LinkedList(3)

    linkedlist.append(1)
    linkedlist.append(2)
    linkedlist.append(3)

    assert linkedlist.find(2) == 1

    assert linkedlist.remove(2) == 1

    assert linkedlist.find(2) == -1

    linkedlist.left_append(20)
    assert linkedlist.find(20) == 0

    assert linkedlist.find(1) == 1
    
    linkedlist.append(2)

    linkedlist.clear()

    assert linkedlist.find(2) == -1

    assert len(linkedlist) == 0