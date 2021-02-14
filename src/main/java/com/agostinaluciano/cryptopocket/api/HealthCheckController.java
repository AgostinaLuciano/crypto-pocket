package com.agostinaluciano.cryptopocket.api;


import com.agostinaluciano.cryptopocket.dto.StatusDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping
    public StatusDTO healthCheck() {
         return new StatusDTO("OK");
    }
}
