#  LGV Automation Test Framework
The framework to write and run automation test for Logivan product in all platform: 'Desktop Site, Android App, iOS App'. Based on : 'Serenity', JUnit', 'Appium', 'Selenium'.

## Installation
1. Install JDK >= 11
2. Install maven >= 3.6.3
2. Install NodeJS
3. Install IntelliJ
4. Install Appium Desktop v1.11.1
5. Open project with IntelliJ. At the first time build, Maven will auto download required library.


## Android automation test set up
1. run appium desktop with host:0.0.0.0 and port:4723
2. use real device or run emulator in android studio
3. run adb devices to get connected deviceUID then input capabilities in file serenity.properties
4. run OrderCreation file by IDE or use commandline : mvn -Dit.test=OrderCreation -Dskip-test=true clean verify
