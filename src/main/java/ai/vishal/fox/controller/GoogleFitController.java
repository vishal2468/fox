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
import ai.vishal.fox.model.request.StatsRequestBody;
import ai.vishal.fox.model.security.MyUserDetails;
import ai.vishal.fox.service.AccessService;

@RestController
public class GoogleFitController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccessService accessService;

    @GetMapping("/bp")
    public String getBloodpressure(@LoggedInUser MyUserDetails userDetails) {
        String url="https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody=new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.blood_pressure")))
                        .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                        .setStartTimeMillis(System.currentTimeMillis()-86400000l)
                        .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth(accessService.getAccessToken(userDetails.getRefreshToken()));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
    @GetMapping("/heartrate")
    public String getHeartRate(@LoggedInUser MyUserDetails userDetails) {
        String url="https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody=new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.heart_rate.bpm")))
                        .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                        .setStartTimeMillis(System.currentTimeMillis()-86400000l)
                        .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth(accessService.getAccessToken(userDetails.getRefreshToken()));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
    @GetMapping("/oxygensat")
    public String getOxygenSaturation(@LoggedInUser MyUserDetails userDetails) {
        String url="https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody=new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.oxygen_saturation")))
                        .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                        .setStartTimeMillis(System.currentTimeMillis()-86400000l)
                        .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth(accessService.getAccessToken(userDetails.getRefreshToken()));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
    @GetMapping("/bodytemp")
    public String getBodyTemperature(@LoggedInUser MyUserDetails userDetails) {
        String url="https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody=new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.body.temperature")))
                        .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                        .setStartTimeMillis(System.currentTimeMillis()-86400000l)
                        .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setBearerAuth(accessService.getAccessToken(userDetails.getRefreshToken()));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
}
