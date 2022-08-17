package com.yunboklog.api.Controller;

import com.yunboklog.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public String get(@RequestBody PostCreate param) {

        log.info("param = {}", param.toString());
        return "Hello World";
    }
}
