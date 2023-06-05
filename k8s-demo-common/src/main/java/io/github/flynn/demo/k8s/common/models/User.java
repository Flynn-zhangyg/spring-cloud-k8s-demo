package io.github.flynn.demo.k8s.common.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class User {

  private Long id;
  private String name;
}
