package com.company.framework.base;

import com.company.framework.utils.Context;
import com.company.framework.utils.JsonConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Framework {
    private static Context context;
    private static WebDriver webdriver = null;
    private static final HashMap<String, Object> serverParameters = new HashMap<String, Object>();
    public static JsonConfigReader jsonConfigReader = new JsonConfigReader();
    private static Map<String, Object> capMap;
    private static final Logger LOGGER = LoggerFactory.getLogger(Framework.class);

    public Framework(Context context){
        this.context = context;
    }

    public static WebDriver setup_desktop_browser(Context context, String config) throws Exception {
        capMap = jsonConfigReader.getBrowserJsonConfig(config);
        String OS = System.getProperty("os.name");
        context.setValue("platform", OS);
        context.setValue("cap", capMap);

        //LOCAL BROWSER
        if (context.getValue("gridServerUrl").equals("")) {
            String browserName = capMap.get("browserName").toString();
            context.setValue("browserName", browserName);
            webdriver = BrowserFactoryNew.getBrowserDriver(context, browserName, capMap, OS);
        }

        //REMOTE BROWSER
        else {
            String hub_url = context.getValue("gridServerUrl").toString();
            webdriver = create_webdriver_remote(hub_url, new DesiredCapabilities(capMap));
        }

      return webdriver;
    }

    public static WebDriver create_webdriver_remote(String url, DesiredCapabilities capability) throws
            MalformedURLException {
        webdriver = new RemoteWebDriver(new URL(url), capability);
        return webdriver;
    }

    public static void addKeysAndValuesToContext(Context context, Map<String, Object> map){
        for (Map.Entry<String, Object> pair : map.entrySet()) {
            context.setValue(pair.getKey(), pair.getValue().toString());
        }
    }


}
