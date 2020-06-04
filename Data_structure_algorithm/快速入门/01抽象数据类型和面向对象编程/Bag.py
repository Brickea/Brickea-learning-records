class Bag(object):
    '''
    抽象数据类型 ADT
    背包 Bag
    '''
    def __init__(self,maxsize=10):
    # 初始化函数
        # 最大容量
        self.maxsize = maxsize
        # 存放数据的容器
        self._items = list()
    
    def add(self,item):
    # 添加数据函数
        # 判断背包是否装满
        if len(self) > self.maxsize:
            raise Exception("Bag is full")
        # 背包未满，添加新物品
        self._items.append(item)
    
    def remove(self,item):
    # 添加移除物品函数
        # 移除物品
        self._items.remove(item)

    def __len__(self):
    # 魔术方法，在调用 len(object) 时会自动调用此方法
        return len(self._items)
    
    def __iter__(self):
    # 迭代器
        for item in self._items:
            yield item
    
def test_bag():
    # 单元测试
    bag = Bag()

    bag.add(1)
    bag.add(2)
    bag.add(3)

    assert len(bag) == 3

    bag.remove(3)
    assert len(bag) == 2

    for i in bag:
        print(i)