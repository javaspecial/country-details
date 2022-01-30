# country-details

A server that allows you to look up a country by name and returns the full name,
population, and a list of its official currencies including the current exchange rate to IDR.
Requests should require a valid JWT obtained from a separate /login endpoint(make your
assumptions here for partial flow, OAuth2 flow is preferred). Store the exchange rates of the
currencies queried so far by users for audit purposes.


Use this API by following steps...

Note: I have used postman for testing

Step 1:
=======
URL: http://localhost:8080/register (POST Request)

Body: Should be valid username and password- like
{
    "username": "country",
    "password": "country"
}

Response: 
---------
{
    "username": "country"
}

Step 2:
=======
URL: http://localhost:8080/authenticate (POST Request)

Body: Should be valid username and password- like
{
    "username": "country",
    "password": "country"
}

Response:
---------
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3VudHJ5IiwiZXhwIjoxNjQzNTA0Mjc5LCJpYXQiOjE2NDM0ODYyNzl9.bWtM_ZE3GUAMydMpI_8_ZSyDeI03fGZJMTm6IB6-PL_MeEoP2ipJ7xMJMXqABxthYm6VgYcKK8niRRSt4SQ5bg"
}

Step 3: 
=======
URL: http://localhost:8080/country/BD (GET Request)

Header: header key and token should be Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3VudHJ5IiwiZXhwIjoxNjQzNTA0Mjc5LCJpYXQiOjE2NDM0ODYyNzl9.bWtM_ZE3GUAMydMpI_8_ZSyDeI03fGZJMTm6IB6-PL_MeEoP2ipJ7xMJMXqABxthYm6VgYcKK8niRRSt4SQ5bg

Response:
---------

{
  "full_name" : "BD",
  "population" : "16 crore",
  "currencies" : { }
}.

Step 3: 
=======
URL: http://localhost:8080/audit (GET Request)

Header: header key and token should be Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3VudHJ5IiwiZXhwIjoxNjQzNTA0Mjc5LCJpYXQiOjE2NDM0ODYyNzl9.bWtM_ZE3GUAMydMpI_8_ZSyDeI03fGZJMTm6IB6-PL_MeEoP2ipJ7xMJMXqABxthYm6VgYcKK8niRRSt4SQ5bg

Body: Should be valid username- like
{
    "username": "country",
}

Response:
---------

[
    {
        "code": BDT,
        "name": BDT,
        "symbol": null,
        "exchangeRateIdr": 2
    },
    {
        "code": null,
        "name": null,
        "symbol": null,
        "exchangeRateIdr": null
    }
]
