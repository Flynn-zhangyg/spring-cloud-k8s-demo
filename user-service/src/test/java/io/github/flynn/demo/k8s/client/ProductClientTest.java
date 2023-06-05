package io.github.flynn.demo.k8s.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.github.flynn.demo.k8s.common.models.Product;
import io.github.flynn.polaris.common.models.Response;
import java.util.Collections;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
class ProductClientTest {


  @ClassRule
  public static WireMockRule WIREMOCK = new WireMockRule(options().port(9000));

  @Autowired
  private ProductClient productClient;

  @BeforeAll
  static void beforeAll() throws Exception {
    WIREMOCK.stubFor(get(urlEqualTo("/product/ids"))
        .withQueryParam("productIds", equalTo("1"))
        .willReturn(aResponse()
            .withBody(new ObjectMapper().writeValueAsString(Response.ok(
                Collections.singletonList(Product.builder().id(1L).name("Toy").build())))))
    );
  }

  @Test
  @Ignore
  void shouldGetProductsInfo() {
    Response<List<Product>> productInfoByIds =
        productClient.getProductInfoByIds(Collections.singletonList(1L));
    assertThat(productInfoByIds.data().size()).isEqualTo(1);
    assertThat(productInfoByIds.data().get(0).getName()).isEqualTo("Toy");
    assertThat(productInfoByIds.data().get(1).getId()).isEqualTo(1);
  }
}