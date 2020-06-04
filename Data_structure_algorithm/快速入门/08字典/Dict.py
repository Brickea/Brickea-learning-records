# -*- coding: utf-8 -*8
class Array(object):
    '''
    list 实现定长 Array
    '''

    def __init__(self, size=32, default=None):
        # 初始化函数
        # 默认长度 Array 默认长度 32
        self.size = size
        # 初始化容器
        self._items = [default] * self.size

    def __getitem__(self, index):
        # 魔术方法 __getitem__ 实例通过下标访问的时候会执行
        return self._items[index]

    def __setitem__(self, index, value):
        # 魔术方法 同 __getitem__
        self._items[index] = value

    def __len__(self):
        # 魔术方法，返回定长
        return self.size

    def clear(self, value=None):
        for i in range(self.size):
            self._items[i] = value

    def __iter__(self):
        # 迭代器
        for item in self._items:
            yield item


class Slot(object):
    '''
    定义存储单元，也就是哈希表中的槽
    槽有三种状态，一种是未使用过的情况，None
                一种是正在使用中
                一种是使用过，但是移除了里面的元素
    '''

    def __init__(self, key, value):
        # 槽初始化函数
        self.key, self.value = key, value


class HashTable(object):
    '''
    实现基于定长数组的哈希表
    '''
    # 定义槽的状态
    UNUSED = None
    EMPTY = Slot(None, None)

    def __init__(self):
        # 初始化函数
        # 初始化定长数组
        self._table = Array(8, default=HashTable.UNUSED)
        # 初始化长度
        self.length = 0

    @property
    def _load_factor(self):
        # 装载因子，代表当前装载数量和当前总容量的比值，当前认为阈值为0.8
        return self.length/float(len(self._table))

    def __len__(self):
        # 长度魔术方法
        return self.length

    def _hash(self, key):
        # 哈希函数，计算对应key的index
        # 这里是用了内置的哈希函数
        return abs(hash(key)) % len(self._table)

    def _find_index(self, key):
        # 寻找数据对应的槽的位置
        _index = self._hash(key)
        _len = len(self._table)
        while self._table[_index] is not HashTable.UNUSED:
            # 如果从已使用的槽中找到状态为“已使用但移除内容”的位置，则用处理哈希冲突的方式计算下一index
            if self._table[_index] is HashTable.EMPTY:
                # 找到为空的地方，则说明当前要查找的key与其他已存储但是移除了的元素有哈希冲突
                # cpython 使用的一种解决哈希冲突的方式，
                _index = (_index * 5 + 1) % _len
                continue
            elif self._table[_index].key == key:
                # 找到对应key存储的的位置
                return _index
            else:
                # 同样，比对不上则转移到下一个下标
                _index = (_index * 5 + 1) % _len
        # 循环结束也没有返回则说明现在这个key没有存储在哈希表中，所以返回None
        return None

    def _slot_can_insert(self, index):
        # 查看此槽位可否插入
        return (self._table[index] is HashTable.EMPTY or self._table[index] is HashTable.UNUSED)

    def _find_slot_insert(self, key):
        # 寻找一个槽给新数据插入用
        _index = self._hash(key)
        _len = len(self._table)
        while not self._slot_can_insert(_index):
            _index = (_index * 5 + 1) % _len
        return _index

    def __contains__(self, key):
        # 重写 in 操作符
        return (self._find_index(key) is not None)

    def add(self, key, value):
        # 添加元素
        if key in self:
            # 说明key已经在哈希表中
            _index = self._find_index(key)
            self._table[_index].value = value
        else:
            # 没有在表中
            _index = self._find_slot_insert(key)
            self._table[_index] = Slot(key, value)
            self.length += 1
            if self._load_factor > 0.8:
                self._rehash()

    def _rehash(self):
        # rehash方法，长度增加策略为延长为原来负载长度的两倍
        old_table = self._table
        newsize = len(self._table) * 2
        self._table = Array(newsize, HashTable.UNUSED)
        self.length = 0
        for slot in old_table:
            if slot is not HashTable.EMPTY and slot is not HashTable.UNUSED:
                _index = self._find_slot_insert(slot.key)
                self._table[_index] = slot
                self.length += 1

    def get(self, key, default=None):
        # 获得对应key的数值
        _index = self._find_index(key)
        if _index is not None:
            return self._table[_index].value
        else:
            return default

    def remove(self, key):
        # 删除对应key的slot
        _index = self._find_index(key)
        if _index is None:
            raise KeyError()
        value = self._table[_index].value
        self._table[_index] = HashTable.EMPTY
        self.length -= 1
        return value

    def __iter__(self):
        # 迭代器方法，迭代返回key
        for slot in self._table:
            if slot is not HashTable.EMPTY and slot is not HashTable.UNUSED:
                yield slot.key

####################################
# Dict ADT 实现
####################################

class Dict(HashTable):
    '''
    借助hashtable实现dict结构
    '''

    def __setitem__(self,key,value):
        self.add(key,value)

    def __getitem__(self,key):
        if key not in self:
            raise KeyError()
        return self.get(key)

    def _iter__slot(self):
        for slot in self._table:
            if slot not in (HashTable.EMPTY,HashTable.UNUSED):
                yield slot

    def items(self):
        for item in self._iter__slot():
            yield (item.key,item.value)

    def keys(self):
        for item in self._iter__slot():
            yield item.key

    def values(self):
        for item in self._iter__slot():
            yield item.value

def test_dict():

    d = Dict()

    d['a'] = 1
    d['b'] = 2

    assert d['a'] == 1

    test_list = list(range(30))
    import random
    random.shuffle(test_list)

    for i in test_list:
        d.add(i,i)

    for i in range(30):
        assert d[i] == i