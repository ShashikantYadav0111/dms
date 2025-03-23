package com.project.dairy_management_system.Service.Redis;

import com.project.dairy_management_system.DTO.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String,CustomerDto> redisTemplate;

    // Save data to Redis with expiration time
    public void saveData(String key, CustomerDto value, long timeout){
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);
    }

    // Retrieve data from Redis
    public CustomerDto getData(String key){
        return (CustomerDto)redisTemplate.opsForValue().get(key);
    }

    // Delete data from Redis
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
