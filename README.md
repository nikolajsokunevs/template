Selenium / Allure / JUnit5 
=======================
### How to run test?
        mvn clean install test    - execute all tests
        mvn allure:serve          - generate allure report and start jetty webserver for quick access

### Configuration

Configuration defined in ApplicationProperties.java

You can specify environment to run
-Dapplication.env=local
-Dapplication.env=dev

You can specify browser by using one of the following switches:
- -Dbrowser=FIREFOX
- -Dbrowser=CHROME

Also you can specify where to run tests, on lochal machine, or grid:
- -Dapplication.remotDriver=true 
- -DseleniumGridURL=http://localhost:4444/wd/hub 

docker-compose file is created, you can setup local grid with docker command: 
- docker-compose -f docker-compose.yaml up

### DriverFactory
Main idea: static method getDriver(), returns 1 browser instance per thread
