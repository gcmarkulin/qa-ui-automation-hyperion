package com.company.framework.constants;


public interface Constants {


    String PROPS_HYPERION_URL = "hyperion.url";

    String PROPS_WIN_CHROME_PATH = "win.browser.chrome.path";
    String PROPS_WIN_IE_PATH = "win.browser.ie.path";
    String PROPS_WIN_FIREFOX32_PATH = "win.browser.gecko.x32.path";
    String PROPS_WIN_FIREFOX64_PATH = "win.browser.gecko.x64.path";

    String PROPS_MAC_CHROME_PATH = "mac.browser.chrome.path";
    String PROPS_MAC_FIREFOX_PATH = "mac.browser.gecko.path";

    String PROPS_LINUX_CHROME_PATH = "linux.browser.chrome.path";
    String PROPS_LINUX_FIREFOX_PATH = "linux.browser.gecko.path";

    /* Config File constants */
    String PROPS_WEBDRIVER_CONFIG = "webdriver.configs.path";

    /* Grid */
    String PROPS_GRID_SERVER = "grid.server.url";

    /* Users */
    String PROPS_USER_HYPERION = "user_hyperion";

    /* Reports */
    String PROPS_FAILED_TESTS = "failed.tests";
    String PROPS_CHARTS_CONFIG = "charts";

    /* Config Web Files */
    String SAFARI_DEFAULT = "firefox_default_config";
    String FIREFOX_DEFAULT = "firefox_default_config";
    String FIREFOX_HEADLESS = "firefox_headless_config";
    String CHROME_DEFAULT = "chrome_default_config";
    String CHROME_HEADLESS = "chrome_headless_config";
    String CHROME_MOBILE_EMULATED = "chrome_mobile_emulated_config";
}
