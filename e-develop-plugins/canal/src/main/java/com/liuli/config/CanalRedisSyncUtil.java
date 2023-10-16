package com.liuli.config;


import com.liuli.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import javax.annotation.Resource;

/**
 * 运河redis同步工具类  -> 用于同步mysql数据到redis CanalTable的value代表表名
 *
 * @author zhanghui
 * @date 2023/08/23 10:35:23
 */
@CanalTable(value = "student")
@Component
@Slf4j
public class CanalRedisSyncUtil implements EntryHandler<Student> {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void insert(Student student) {
        log.info("redis新增数据");
        log.info("student:{}",student);
        redisTemplate.opsForValue().set("student:"+student.getId(),student);
        log.info("redis新增数据成功");
    }

    @Override
    public void update(Student before, Student after) {
        log.info("redis更新数据");
        log.info("before:{}",before);
        log.info("after:{}",after);
        Integer id = before.getId() == null ? after.getId() : before.getId();
        redisTemplate.opsForValue().set("student:"+id,after);
        log.info("redis更新数据成功");
    }

    @Override
    public void delete(Student student) {
        log.info("redis删除数据");
        log.info("student:{}",student);
        redisTemplate.delete("student:"+student.getId());
        log.info("redis删除数据成功");
    }
}
