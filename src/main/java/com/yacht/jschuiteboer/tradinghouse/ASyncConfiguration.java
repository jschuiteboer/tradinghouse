package com.yacht.jschuiteboer.tradinghouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ASyncConfiguration implements AsyncConfigurer {
}
