# Java Automation Framework

This framework has been developed for internal use on Belatrix projects.
It uses Java as main language, [Selenium WebDriver](http://www.seleniumhq.org) for Web testing, [Appium](http://appium.io) for Mobile testing and [RestAssured](http://rest-assured.io) for API testing. It supports multiple browsers, mobile simulators and real devices for test automation.

##Prerequisites
###For Windows (coming soon)

###For MAC
####Download Java SDK
The recommendation is to have installed Java 8 on your machine, simply got to the [Java SE download page](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) download and install the java version according to your OS.
#####Setting JAVA_HOME
Then you need to set up the JAVA_HOME environment variable on your machine. 
To do this open your terminal and open your .bash_profile file using nano or vi.
```
nano .bash_profile
```

add this line 

`export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_152.jdk/Contents/Home` 

save and exit the editor and finally recompile your bash file using 

`source ~/.bash_profile` on your terminal. 

To check if this was successful on your terminal write `echo $JAVA_HOME` and if it worked, you should read the path where your sdk is located. 

####INSTALLING XCODE
For running iOS mobile testing, you need to have Xcode, it has all the iOS SDK's for configure and running simulators such as iPhones and iPads.

Installing this is quite simple, just go to the App Store on your mac search [Xcode](https://itunes.apple.com/pe/app/xcode/id497799835?mt=12) and just click on the install button. 

####INSTALLING ANDROID STUDIO
For running Android mobile test you need to have the Android Studio installed on your machine to manage all the simulators, configurations and installations for Android devices.
You may to follow [these instructions](https://developer.android.com/studio/install.html) to install it.
#####Setting ANDROID_HOME
After you install the Android SDK you must set up the ANDROID_HOME environment variable along others finish the installation.

To do so, open your terminal and open your bash profile as you did when setting JAVA_HOME.
On your bash profile paste the following lines

```
export ANDROID_HOME=/Users/[your user]/Library/Android/sdk
export PATH="$PATH:$ANDROID_HOME/tools"
export PATH="$PATH:$ANDROID_HOME/platform-tools"
export PATH="$PATH:$ANDROID_HOME/platform-tools/adb"
export PATH="$PATH:$ANDROID_HOME/build-tools"
```

To finalize, again recompile your bash file using `source ~/.bash_profile` command. Now you are fully set.

#####Setting Android Virtual Devices
After the previous steps are completed you must configure at least one simulator, and for doing so, please follow [this instructions](https://developer.android.com/studio/run/managing-avds.html) on how to create and mange Android Virtual Devices.

##Working Platforms
* **Working OS**
    * Windows
    * MAC
    
* **Working Browsers**
    * Chrome
    * Mozilla Firefox
    * Safari (Mac Only)
    
* **Working Mobile Devices OS**
    * iOS
    * Android
    
##Requirements
###WINDOWS (coming soon)
###MAC
 Previous all installations, if you are only automating Web tests you only need to have the latest versions of Mozilla and Chrome Browsers on your MAC, you may want to jump to the writing your test scripts section, but if you are running mobile tests, please keep reading forward.

 The environment needs certain software to be previously installed before you are able to run the framework.
 
####INSTALLING NODE AND NPM

If you have homebrew installed on your machine this should be quite easy if not, open your terminal on your mac and copy-paste this line on the command prompt

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

This will install homebrew on your machine, now you are ready to install node.

If you don't want to use homebrew, you can always go to the [Node.js Downloads page](https://nodejs.org/en/download/), select the macOS installer and run the .pkg file.

Now for installing node simply on the command prompt write 

```
brew install node
```

If you want to check te installation of both tools put on the Terminal `node -v` to check which version of node is installed, or `npm -v` if you want to check the npm version. For updating node and npm first update the homebrew packages typing `brew update` and then type `brew upgrade node`, you are running the latest version of node and npm on your machine.

####INSTALLING APPIUM

For installing Appium just open a Terminal and put

```
npm install -g appium
```

This will install the latest version of Appium. If you want to download the Desktop version go to the the[releases page](https://github.com/appium/appium-desktop/releases)directly and download it.

####INSTALLING CARTHAGE
For iOS mobile testing you must have carthage installed, if you want to know more about carthage please [read this](https://github.com/Carthage/Carthage).

There are multiple options for installing Carthage:

**Installer:** Download and run the `Carthage.pkg` file for the latest [release](https://github.com/Carthage/Carthage/releases), then follow the on-screen instructions.

**Homebrew:** You can use [Homebrew](http://brew.sh) and install the `carthage` tool on your system simply by running `brew update` and `brew install carthage`. (note: if you previously installed the binary version of Carthage, you should delete `/Library/Frameworks/CarthageKit.framework`).

**FINALLY YOU ARE READY TO BEGIN WRITING YOUR TESTS**
 
##Running the Framework

The framework was design for running on multiple platforms, covering the most used browsers (Mozilla and Chrome) and the most important OS for mobile devices (iOS and Android).
The following instructions will cover each scenario on how the tests should be written to use all the framework capabilities.

######Cucumber and BDD
The framework uses BDD to write test cases, if you are not familiar neither Cucumber or BDD go through [this documentarion](https://cucumber.io/blog/2017/05/15/intro-to-bdd-and-tdd) about Gherkin and how BDD works.  

###Web Automation

######Setting the target url to test
For setting the url for the page to test, you need to do the following:

Open the project root folder, and on the properties folder change on the _.properties_ file the line with **url** variable

```
url = http://yourpagetotest
```

#####Writing test cases
This Framework has implemented several capabilities for the previously listed platforms. In this section all the implemented capabilities will be explained for  all the platforms.

######Web Capabilites
-[x] Multi browser automation *(Google Chrome, Mozilla Firefox)*
-[x] Multi driver automation
-[x] Headless Automation
-[x] Mobile Emulation *(Chrome only)*        
######*Test case Example:*

      
        
For instance:
```java
    @Working
    @Test(dataProvider = "getData")
    public void exampleTest(String configName) throws Exception {
        LOGGER.info("Login Issue Tracker with : " + configName);
        driver = Commons.setup(getContext(), typeTest, configName);
        driver.get(BaseUtil.getIssueTrackerUrl());

        issueTrackerLoginPage = new IssueTrackerLoginPage(driver);
        parts = Commons.getUserApplication("issuetracker");
        issueTrackerLoginPage.login(parts[0],parts[1]);

        issueTrackerMyPage = issueTrackerLoginPage.clickOnLoginBtn();

        assertThat(issueTrackerMyPage.checkMyPage()).isTrue();
    }
    
    @DataProvider(name = "getData")
    public Object[][] provideData() {
          return new Object[][] {
                    { FIREFOX_DEFAULT },
                    { FIREFOX_HEADLESS },
                    { CHROME_DEFAULT },
                    { CHROME_HEADLESS },
                    { CHROME_MOBILE_EMULATED }
            };
        }
```

##Helpful Links
#### Java:
* **Java course from Zero** -> [https://javadesdecero.es/](https://javadesdecero.es/)
* **Another Java Course** -> [https://www.programarya.com/Cursos/Java](https://www.programarya.com/Cursos/Java)
* **Free Java Basics Course** -> [https://automationintesting.com/java/course/](https://automationintesting.com/java/course/)

#### Git:
* **Git - The simple guide** -> [http://rogerdudler.github.io/git-guide/index.html](http://rogerdudler.github.io/git-guide/index.html)
* **Official Website** -> [https://git-scm.com/book/en/v2](https://git-scm.com/book/en/v2)

#### Selenium:
* **Getting Started with WebDriver in Java Using IntelliJ** -> [https://saucelabs.com/resources/articles/getting-started-with-webdriver-in-java-using-intellij](https://saucelabs.com/resources/articles/getting-started-with-webdriver-in-java-using-intellij-on-windows)
* **Official Website** -> [https://www.seleniumhq.org/](https://www.seleniumhq.org/)
* **Selenium Tutorial for Beginners: Learn WebDriver in 7 Days** -> [https://www.guru99.com/selenium-tutorial.html](https://www.guru99.com/selenium-tutorial.html)

#### TesNG:
* **Official Website** -> [https://testng.org/doc/](https://testng.org/doc/)

#### Page Objects:
* **Getting Started with Page Object Pattern for Your Selenium Tests** -> [https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests](https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests)
* **Page Object Model (POM) & Page Factory: Selenium WebDriver Tutorial** -> [https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html](https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html)

