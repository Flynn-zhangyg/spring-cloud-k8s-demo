package io.github.flynn.demo.k8s.controller;

import static java.util.Collections.singletonList;

import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.polaris.common.models.Response;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @GetMapping
  Response<List<Product>> getProducts(@RequestParam("ids") List<Long> ids) {
    return Response.ok(singletonList(Product.builder().id(111L).name("Toys").build()));
  }
}
