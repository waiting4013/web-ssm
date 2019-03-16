package test;

import redis.clients.jedis.Jedis;

public class RedisTest {


    public  void redis(String...  qw){
        Jedis jedis=new Jedis("121.12.12.12");
        jedis.auth("redis");
        jedis.set("a","asd");
        jedis.lpush("list","qwqw");
        jedis.lpush("list","qwee");
        jedis.rpush("sa","1212");

    }
    public void ss(){

        redis("qw","qeq","qeq","12");
    }

}
