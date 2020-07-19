# Redis基础 <!-- omit in toc -->

## 数据类型

Redis支持5中数据类型
* string 字符串
* hash 哈希
* list 列表
* set 集合
* zset 有序集合

### string 字符串

string是redis最基本的类型，一个key对应一个value。

string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。

string类型是Redis最基本的数据类型，一个键最大能存储512MB。

```
127.0.0.1:6379> SET name Brickea
OK
127.0.0.1:6379> GET name
"Brickea"
```

> 在以上实例中我们使用了 Redis 的 SET 和 GET 命令。键为 name

### hash 哈希 

Redis hash 是一个键值对集合。

Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。


```
127.0.0.1:6379> HMSET person:1 name brickea money 100 gender male
OK
127.0.0.1:6379> HGETALL person:1
1) "name"
2) "brickea"
3) "money"
4) "100"
5) "gender"
6) "male"
```

以上实例中 hash 数据类型存储了包含用户脚本信息的用户对象。实例中我们使用了 Redis HMSET, HGETALL 命令，person:1 为键值。

每个 hash 可以存储 $2{^32} - 1$ 键值对（40多亿）。

### list 列表

Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。

```
127.0.0.1:6379> LPUSH myList brickea
(integer) 1
127.0.0.1:6379> LPUSH myList sandy
(integer) 2
127.0.0.1:6379> LRANGE myList 0 10
1) "sandy"
2) "brickea"
```

列表最多可存储 $2{^32} - 1$ 元素 (4294967295, 每个列表可存储40多亿)。

### Set集合

Redis的Set是string类型的无序集合。

集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是$O(1)$。

添加一个string元素到,key对应的set集合中，成功返回1,如果元素以及在集合中返回0,key对应的set不存在返回错误。

```
127.0.0.1:6379> SADD mySet brickea
(integer) 1
127.0.0.1:6379> SADD mySet brickea
(integer) 0
127.0.0.1:6379> SMEMBERS mySet
1) "brickea"
127.0.0.1:6379> SADD mySet sandy
(integer) 1
127.0.0.1:6379> SMEMBERS mySet
1) "brickea"
2) "sandy"
```

集合最多可存储 $2{^32} - 1$ 元素 (4294967295, 每个列表可存储40多亿)。

### zset (sorted set：有序集合)

Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。  
不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

zset的成员是唯一的,但分数(score)却可以重复。

添加元素到集合，元素在集合中存在则更新对应score

```
127.0.0.1:6379> ZADD mySortedSet 0 brickea
(integer) 1
127.0.0.1:6379> ZADD mySortedSet 0 sandy
(integer) 1
127.0.0.1:6379> zrangebyscore mySortedSet 0 10
1) "brickea"
2) "sandy"
127.0.0.1:6379> ZADD mySortedSet 2 brickea
(integer) 0
127.0.0.1:6379> zrangebyscore mySortedSet 0 10
1) "sandy"
2) "brickea"
```