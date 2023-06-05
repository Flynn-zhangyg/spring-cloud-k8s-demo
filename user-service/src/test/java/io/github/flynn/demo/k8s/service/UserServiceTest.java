package io.github.flynn.demo.k8s.service;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.flynn.demo.k8s.client.ProductClient;
import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.demo.k8s.common.models.User;
import io.github.flynn.demo.k8s.service.impl.UserServiceImpl;
import io.github.flynn.polaris.common.models.Response;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  private final ProductClient productClient = mock(ProductClient.class);
  private final UserService userService = new UserServiceImpl(productClient);

  @Test
  void shouldGetUserInfoById() {
    User user = userService.getUserInfoById(1L);
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getName()).isEqualTo("Bob");
  }

  @Test
  void shouldGetProductOfUser() {
    when(productClient.getProductInfoByIds(singletonList(1L)))
        .thenReturn(Response.ok(singletonList(Product.builder().id(1L).name("Toy").build())));
    List<Product> products = userService.getProductsOfUser(1L);

    assertThat(products.size()).isEqualTo(1);
    assertThat(products.get(0).getId()).isEqualTo(1L);
    assertThat(products.get(0).getName()).isEqualTo("Toy");
    verify(productClient).getProductInfoByIds(singletonList(1L));
  }
}
