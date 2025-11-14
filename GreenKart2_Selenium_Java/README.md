# End To End Selenium Automation Framework using TestNG-Java

Framework include below dependency in POM.xml

1) maven-surefire-plugin

2) selenium-java

3) testng

4) extentreports

5) log4j-core

# src/main/java includes below Packages:-

## greenKartPages

In this Created seperate pages for different Web pages available using Page factory Structure

## utilities

1) basepage.java includes all basic function for initializing browser and all common methods.

2) extentreports.java includes method for creating extent report.

3) Listeners.java implements ITestListener and implented method for getting screenshot and updating report based on Pass and failure of Test Cases.

4) data.properties consist of URL for web application that we are opening for Testing and on which browser TC should run

# src/test/java includes below Packages:-

Added all TestNG Test cases that we have to run.

# Screenshot

Capture Screenshot for all failed Test Cases.

# TestReports

Generated extent html report for every run with Date and Time as Folder Name

# testng.xml

provided all Test cases that we have to run.

can run Test cases in parallel mode.

Integrated with jenkins so that we can run our all Test Cases from jenkins
