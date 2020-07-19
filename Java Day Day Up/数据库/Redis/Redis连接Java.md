# Redis 连接数据库

下载jar[依赖](https://mvnrepository.com/artifact/redis.clients/jedis/3.3.0)

测试是否联通

```java
package JDBCRedisTest;

import redis.clients.jedis.Jedis;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: Jedis
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description: Connect to localhost redis server
 *************************************************************************/
public class Main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("测试链接"+jedis.ping());
    }
}

```