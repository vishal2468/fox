package ai.vishal.fox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoogleFitController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/datasource")
    public String getAccessToken(@RequestParam String accessToken) {
        return null;
    }
}
