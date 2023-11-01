# HealthLink Pro
- Username: vishal139
- password: password

Please be connected to OT VPN when using this service.

* This application supports user signup but , since it is using google fit data, we need to white list the email in google API console.
* There for any other user can signup but wont be able to use the Google fit apis to fetch data.
* Let me know in advance if you need to test with your own email.

# Steps to use the application:

## login
`http://localhost:8080/login`
- Username: vishal139
- password: password
## acess data
The provides access to 4 health parameters
  - GET `http://localhost:8080/bodytemperature`
  - GET `http://localhost:8080/oxygensaturation`
  - GET `http://localhost:8080/heartrate`
  - GET `http://localhost:8080/bloodpressure` 
  
