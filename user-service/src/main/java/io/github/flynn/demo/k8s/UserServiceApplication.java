package io.github.flynn.demo.k8s;

import io.github.flynn.demo.k8s.client.ProductClient;
import io.github.flynn.demo.k8s.model.TestProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {
    ProductClient.class
})
@EnableDiscoveryClient
@EnableConfigurationProperties(TestProperties.class)
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class);
  }
}



