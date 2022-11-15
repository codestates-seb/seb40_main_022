package com.backend.fitchallenge.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTemplate<String, String> redisBlackListTemplate;

    public void setValues(String key, String data){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValues(String key, String data, Long expiration) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data, expiration);
    }

    public String getValues(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public void checkRefreshToken(String username, String refreshToken) {
        String redisRT = this.getValues(username);
        if(!refreshToken.equals(redisRT)) {
            throw new RuntimeException(); //todo token expired 에러클래스 만들기.
        }
    }

    public void setBlackListValues(String key, String data){
        ValueOperations<String, String> values = redisBlackListTemplate.opsForValue();
        values.set(key, data);
    }

    public String getBlackListValues(String key){
        ValueOperations<String, String> values = redisBlackListTemplate.opsForValue();
        return values.get(key);
    }
}
