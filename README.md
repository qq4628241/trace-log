# trace-log
# trace log in microservices app

### Log Trace For Help follow the request header 'Trace-Id' from customer client to another backend service

1. pom 
   ```pom
   <dependency>
       <groupId>com.scott</groupId>
       <artifactId>trace-log</artifactId>
      <version>1.0-SNAPSHOT</version>
   </dependency>
   ```

2. application.yml 

   ```yml
   trace.enabled=true
   ```


3. log4j2.xml or logback.xml

   ```xml
    <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level [traceId=%X{traceId}] %logger{36} - %msg%n"/>
   # pattern add [traceId=%X{traceId}] 
   ```
