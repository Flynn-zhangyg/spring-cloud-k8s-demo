package io.github.flynn.demo.k8s.controller;

import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.demo.k8s.common.models.User;
import io.github.flynn.demo.k8s.service.UserService;
import io.github.flynn.polaris.common.models.Response;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;
  private final DiscoveryClient discoveryClient;

  @GetMapping("/{id}")
  Response<User> getUserInfoById(@PathVariable("id") Long id) {
    return Response.ok(userService.getUserInfoById(id));
  }

  @GetMapping("/{id}/products")
  Response<List<Product>> getProductsByUserId(@PathVariable("id") Long id) {
    return Response.ok(userService.getProductsOfUser(id));
  }

  @GetMapping("/{id}/products/sleep")
  Response<List<Product>> getProductsByUserIdWithSleep(@PathVariable("id") Long id) {
    return Response.ok(userService.getProductsOfUserWithSleep(id));
  }

  @GetMapping("/services")
  public Response<List<String>> getServices() {
    List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
    instances.forEach(instance -> {
      System.out.println(instance.getHost());
      System.out.println(instance.getInstanceId());
      System.out.println(instance.getPort());
    });
    return Response.ok(discoveryClient.getServices());
  }
}
