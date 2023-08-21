package io.github.flynn.demo.k8s.model;

import io.github.flynn.polaris.spring.apollo.annotation.RefreshableConfigurationProperties;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RefreshableConfigurationProperties(prefix = "user-service.test")
public class TestProperties {

  private String property;
  private Map<String, String> maps;
}
