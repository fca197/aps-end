package com.olivia.peanut.config;

import com.olivia.peanut.config.entity.DouBaoConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "ai-properties")
public class AiProperties {

  private DouBaoConfig douBao;

}
