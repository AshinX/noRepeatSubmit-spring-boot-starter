package com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator;

import com.fengyue95.noRepeatSubmitspringbootstarter.keygenerator.service.LockKeyGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultLockKeyGenerator implements LockKeyGenerator {
    @Override
    public String generate(String key) {
        return key;
    }
}
