package com.gitbaby.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class IndexController {
  @GetMapping("")
  @ResponseBody // 없으면 jsp forward
  public Map<?, ?> index() {
    return Map.of("message", "Hello World");
  }

}
