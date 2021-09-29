# java-run-personal-project

**active links**
- http://localhost:8080/
- http://localhost:8080/schedule
- http://localhost:8080/movies/search?name=titanic
- http://localhost:8080/movies/search?name=austin%20powers
- http://localhost:8080/movies/search?name=green%20elephant
- http://localhost:8080/tickets?hall=a1&time=21
- http://localhost:8080/tickets?hall=b1&time=14
- http://localhost:8080/tickets?hall=a1&time=13
- http://localhost:8080/tickets?hall=b1&time=19
- http://localhost:8080/tickets?hall=a1&time=17
- http://localhost:8080/tickets?hall=b1&time=17

**purchase ticket requests**

*for session*\
http://localhost:8080/tickets?hall=a1&time=21

*use*
```
curl http://localhost:8080/purchase -X POST -d "hall=a1&time=21&seat=b4"
```

*where you choose last param "seat"*