# HealthLink Pro
- Username: vishal139
- password: password

Please be connected to OT VPN when using this service.

* This application supports user signup but , since it is using google fit data, we need to white list the email in google API console.
* There for any other user can signup but wont be able to use the Google fit apis to fetch data.
* Let me know in advance if you need to test with your own email.

Please watch the following demo video:

[<img src="https://img.youtube.com/vi/QPJKXIw45C0/hqdefault.jpg" width="720" height="450"
/>](https://youtube.com/embed/QPJKXIw45C0)


# Steps to use the application:


## signup 
### signup not applicatble as the user credentials has been provided.
- `http://localhost:8080/user/signup`
- for signing up can use the frontend application
- `https://github.com/vishal2468/fox-ui.git`
- the above frontend is not ready so , can only signup at the moment. Please dont try to login through frontend.


## login
`http://localhost:8080/login`
- Username: vishal139
- password: password

## OAuth
`http://localhost:8080/google/auth`
- You will be redirected to hoogle signin page
- You will be able to see the description of requested data.
- you can approve or deny the request.


## acess data
The provides access to 4 health parameters
  - GET `http://localhost:8080/bodytemperature`
  - GET `http://localhost:8080/oxygensaturation`
  - GET `http://localhost:8080/heartrate`
  - GET `http://localhost:8080/bloodpressure` 
  
