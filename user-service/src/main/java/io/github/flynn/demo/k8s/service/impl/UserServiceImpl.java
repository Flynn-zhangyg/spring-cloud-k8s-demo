package io.github.flynn.demo.k8s.service.impl;

import static java.util.Collections.singletonList;

import io.github.flynn.demo.k8s.client.ProductClient;
import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.demo.k8s.common.models.User;
import io.github.flynn.demo.k8s.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final ProductClient productClient;

  @Override
  public User getUserInfoById(long id) {
    return User.builder().id(id).name("Bob").build();
  }

  @Override
  public List<Product> getProductsOfUser(long id) {
    return productClient.getProductInfoByIds(singletonList(1L)).data();
  }

  @Override
  public List<Product> getProductsOfUserWithSleep(long id) {
    return productClient.getProductInfoByIdsWithSleep(singletonList(1L)).data();
  }
}
