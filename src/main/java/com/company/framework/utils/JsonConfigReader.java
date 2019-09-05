package com.company.framework.utils;

import com.company.framework.base.BaseUtil;
import java.util.Map;


public class JsonConfigReader {

    String path = System.getProperty("user.dir");
    String jsonFileConfigPath;

    public Map<String, Object> getBrowserJsonConfig(String fileConfig) {
        jsonFileConfigPath = path + BaseUtil.getWebdriverConfigPath() + fileConfig + ".json";
        return getConfig(jsonFileConfigPath);
    }

    public Map<String, Object> getConfig(String jsonFileConfigPath) {
        return JsonReader.getJsonObject(jsonFileConfigPath);
    }

}
