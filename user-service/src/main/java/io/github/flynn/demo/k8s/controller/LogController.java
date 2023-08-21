package io.github.flynn.demo.k8s.controller;

import io.github.flynn.demo.k8s.model.TestProperties;
import io.github.flynn.polaris.common.exceptions.AppException;
import io.github.flynn.polaris.common.models.ErrorCode;
import io.github.flynn.polaris.common.models.Response;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
@Slf4j
public class LogController {

  @Autowired
  private TestProperties testProperties;

  @GetMapping
  Response<Void> log() {
    log.trace("========trace log========");
    log.debug("========debug log========");
    log.info("========info log========");
    log.warn("========warn log========");
    log.error("========error log========");
    return Response.ok();
  }

  @GetMapping("/test")
  Response<Map<String, String>> test() {
    log.info("========{}=======", testProperties.getProperty());
    return Response.ok(testProperties.getMaps());
  }

  @GetMapping("exception")
  Response<Void> exception() {

    log.error("Unexpected exception caught, ", new RuntimeException("hahaha"));
    throw new AppException(new ErrorCode() {
      @Override
      public int getCode() {
        return 10000;
      }

      @Override
      public String getDesc() {
        return "jijiji";
      }
    });
  }
}
