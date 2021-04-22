package com.fengyue95.noRepeatSubmitspringbootstarter.intercepter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "enabled.autoConfituration", matchIfMissing = true)
public class NoRepeatSubmitAutoConfiguration {
}
