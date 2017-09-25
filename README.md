# flightsearch
Flight search testing application

## Unit test
mvn clean test

## Integration test
mvn clean integration-test -Pintegration

## Sonar integration
mvn clean test
mvn sonar:sonar

* Do not forget to set sonar url either in pom.xml or in settings.xml
