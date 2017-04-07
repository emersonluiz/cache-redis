package br.com.emersonluiz;

import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
@ComponentScan
@Configuration
@EnableCaching
public class Application extends CachingConfigurerSupport {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Bean
	  public JedisConnectionFactory redisConnectionFactory() {
	    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

	    // Defaults
	    redisConnectionFactory.setHostName("localhost");
	    redisConnectionFactory.setPort(6379);
	    redisConnectionFactory.setUsePool(true);
	    return redisConnectionFactory;
	  }

	  @Bean
	  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
	    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	    redisTemplate.setConnectionFactory(cf);
	    return redisTemplate;
	  }

	  @Bean
	  public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
	    return cacheManager;
	  }
	  
	  @Bean
	  public KeyGenerator customKeyGenerator() {
	    return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
		        sb.append(o.getClass().getName());
		        sb.append(method.getName());
		        for (Object obj : objects) {
		          sb.append(obj.toString());
		        }
		        return sb.toString();
			}
	    };
	  }
}
