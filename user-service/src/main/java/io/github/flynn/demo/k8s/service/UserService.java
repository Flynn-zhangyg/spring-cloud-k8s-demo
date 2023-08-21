package io.github.flynn.demo.k8s.service;

import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.demo.k8s.common.models.User;
import java.util.List;

public interface UserService {
  User getUserInfoById(long id);

  List<Product> getProductsOfUser(long id);

  List<Product> getProductsOfUserWithSleep(long id);
}
