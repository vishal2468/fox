package ai.vishal.fox.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ai.vishal.fox.configuration.LoggedInUser;
import ai.vishal.fox.model.dto.AggregateBy;
import ai.vishal.fox.model.dto.BucketByTime;
import ai.vishal.fox.model.request.BloodPressure;
import ai.vishal.fox.model.security.MyUserDetails;
import ai.vishal.fox.service.AccessService;

@RestController
public class GoogleFitController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccessService accessService;

    @GetMapping("/bloodpressure")
    public String getAccessToken(@LoggedInUser MyUserDetails userDetails) {
        String url="https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        BloodPressure bloodPressure=new BloodPressure();
        bloodPressure.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.blood_pressure")))
                        .setBucketByTime(new BucketByTime().setDurationMillis(86400000))
                        .setStartTimeMillis(1663180200000l)
                        .setEndTimeMillis(1665772200000l);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth(accessService.getAccessToken(userDetails.getRefreshToken()));
        HttpEntity<BloodPressure> requestEntity = new HttpEntity<>(bloodPressure, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
}
