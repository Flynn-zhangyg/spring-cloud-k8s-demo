package io.github.flynn.demo.k8s.client;

import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.polaris.common.models.Response;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "product-service",
    contextId = "user-service-client"
)
public interface ProductClient {

  @GetMapping("/products")
  Response<List<Product>> getProductInfoByIds(@RequestParam("ids") List<Long> productIds);

  @GetMapping("/products/sleep")
  Response<List<Product>> getProductInfoByIdsWithSleep(@RequestParam("ids") List<Long> productIds);
}
