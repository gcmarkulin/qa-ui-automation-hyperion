package com.company.framework.base;


import com.company.framework.utils.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;
import java.io.File;
import java.util.Map;


public class BrowserFactoryNew {

    private static WebDriver driver = null;
    private static ChromeDriverService serviceChrome;
    private static GeckoDriverService serviceFirefox;
    private static SafariDriverService serviceSafari;

    private static String getFirefoxPlatformPath(String OS){
        String path;
        String arch = System.getProperty("os.arch");
        if(OS.toLowerCase().contains("windows")){
            if(arch.toLowerCase().contains("64")) {
                path = BaseUtil.getWin64firePath();
            }
            else{
                path = BaseUtil.getWin32firePath();
            }
        }
        else if(OS.toLowerCase().contains("mac")){
            path = BaseUtil.getMacFirePath();
        }
        else{
            path = BaseUtil.getLinuxFirePath();
        }
        return path;
    }

    private static String getChromePlatformPath(String OS){
        String path;
        if(OS.toLowerCase().contains("windows")){
            path = BaseUtil.getWinChromePath();
        }
        else if(OS.toLowerCase().contains("mac")){
            path = BaseUtil.getMacChromePath();
        }
        else{
            path = BaseUtil.getLinuxChromePath();
        }
        return path;
    }

    public static WebDriver getBrowserDriver(Context context, String browserName, Map<String, Object> capMap, String OS) throws Exception {
        String pathDriver;
        String pathCurrent = System.getProperty("user.dir");

        if (browserName.equals("firefox")) {
            String firefox = getFirefoxPlatformPath(OS);
            pathDriver = pathCurrent + firefox;
            serviceFirefox = new GeckoDriverService.Builder()
                    .usingDriverExecutable(new File(pathDriver))
                    .usingAnyFreePort()
                    .build();
            serviceFirefox.start();

            driver = new RemoteWebDriver(serviceFirefox.getUrl(), new DesiredCapabilities(capMap));

        }
        else if (browserName.equals("chrome"))
            {
                String chrome = getChromePlatformPath(OS);
                pathDriver = pathCurrent + chrome;
                serviceChrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(pathDriver))
                        .usingAnyFreePort()
                        .build();
                serviceChrome.start();

                driver = new RemoteWebDriver(serviceChrome.getUrl(), new DesiredCapabilities(capMap));

            } else if (browserName.equals("safari"))
            {
                serviceSafari = new SafariDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                serviceSafari.start();

                driver = new RemoteWebDriver(serviceSafari.getUrl(), new DesiredCapabilities(capMap));
            }
            else{
            //If no browser passed throw exception
            throw new Exception("Not Supported Browser.");
        }
        return driver;
    }

}
