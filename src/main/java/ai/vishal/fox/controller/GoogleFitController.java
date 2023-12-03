package ai.vishal.fox.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ai.vishal.fox.configuration.LoggedInUser;
import ai.vishal.fox.model.dto.AggregateBy;
import ai.vishal.fox.model.dto.BucketByTime;
import ai.vishal.fox.model.request.StatsRequestBody;
import ai.vishal.fox.model.security.MyUserDetails;
import ai.vishal.fox.model.security.User;
import ai.vishal.fox.repository.UserRepository;
import ai.vishal.fox.service.AccessService;
import ai.vishal.fox.service.KmsService;

@RestController
public class GoogleFitController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KmsService kmsService;

    @Autowired
    AccessService accessService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/bloodpressure")
    public String getBloodpressure(@LoggedInUser MyUserDetails userDetails) {
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.blood_pressure")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userDetails.getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/heartrate")
    public String getHeartRate(@LoggedInUser MyUserDetails userDetails) {
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.heart_rate.bpm")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userDetails.getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/oxygensaturation")
    public String getOxygenSaturation(@LoggedInUser MyUserDetails userDetails) {
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody
                .setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.oxygen_saturation")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userDetails.getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/bodytemperature")
    public String getBodyTemperature(@LoggedInUser MyUserDetails userDetails) {
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.body.temperature")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userDetails.getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/bloodpressure/{username}")
    public String getBloodpressure(@PathVariable String username , @LoggedInUser MyUserDetails doctor) {
        if(!canAccessPatientData(doctor,username)) return "Patient not accessible";
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.blood_pressure")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userRepository.findByUserName(username).get().getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/heartrate/{username}")
    public String getHeartRate(@PathVariable String username , @LoggedInUser MyUserDetails doctor) {
        if(!canAccessPatientData(doctor,username)) return "Patient not accessible";
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.heart_rate.bpm")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userRepository.findByUserName(username).get().getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/oxygensaturation/{username}")
    public String getOxygenSaturation(@PathVariable String username , @LoggedInUser MyUserDetails doctor) {
        if(!canAccessPatientData(doctor,username)) return "Patient not accessible";
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody
                .setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.oxygen_saturation")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userRepository.findByUserName(username).get().getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @GetMapping("/bodytemperature/{username}")
    public String getBodyTemperature(@PathVariable String username , @LoggedInUser MyUserDetails doctor) {
        if(!canAccessPatientData(doctor,username)) return "Patient not accessible";
        String url = "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
        StatsRequestBody statsRequestBody = new StatsRequestBody();
        statsRequestBody.setAggregateBy(Arrays.asList(new AggregateBy().setDataTypeName("com.google.body.temperature")))
                .setBucketByTime(new BucketByTime().setDurationMillis(3600000))
                .setStartTimeMillis(System.currentTimeMillis() - 86400000l)
                .setEndTimeMillis(System.currentTimeMillis());
        HttpHeaders httpHeaders = new HttpHeaders();
        String encryptedAccessToken = userRepository.findByUserName(username).get().getRefreshToken();
        String decryptedToken = kmsService.decrypt(encryptedAccessToken);
        httpHeaders.setBearerAuth(accessService.getAccessToken(decryptedToken));
        HttpEntity<StatsRequestBody> requestEntity = new HttpEntity<>(statsRequestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    private boolean canAccessPatientData(MyUserDetails doctor, String username) {
        return doctor.getUser().getAccessiblePatients().contains(username);
    }
}
