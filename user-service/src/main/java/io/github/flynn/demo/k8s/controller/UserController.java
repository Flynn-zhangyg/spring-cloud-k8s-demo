package io.github.flynn.demo.k8s.controller;

import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.demo.k8s.common.models.User;
import io.github.flynn.demo.k8s.service.UserService;
import io.github.flynn.polaris.common.models.Response;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
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

  @GetMapping("/services")
  public Response<List<String>> getServices() {
    return Response.ok(discoveryClient.getServices());
  }
}
