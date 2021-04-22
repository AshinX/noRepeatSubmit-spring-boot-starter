package com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator;

import com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.service.LockKeyGenerator;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MD5LockKeyGenerator implements LockKeyGenerator {

    @Override
    public String generate(String key) {
        return MD5Encoder.encode(key.getBytes());
    }
}
