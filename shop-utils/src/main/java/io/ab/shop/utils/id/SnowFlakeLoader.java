package io.ab.shop.utils.id;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 定义加载 params.properties 文件的工具类
 */
public class SnowFlakeLoader {

    public static final String DATA_CENTER_ID = "data.center.id";
    public static final String MACHINE_ID = "machine.id";
    private volatile static Properties instance;

    static {
        InputStream inputStream = SnowFlakeLoader.class.getClassLoader().getResourceAsStream("snowflake/snowflake.properties");
        instance = new Properties();
        try {
            instance.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringValue(String key){
        return instance.getProperty(key, "");
    }

    private static Long getLongValue(String key){
        String v = getStringValue(key);
        return (v == null || v.trim().isEmpty()) ? 0 : Long.parseLong(v);
    }

    public static Long getDataCenterId() {
        return getLongValue(DATA_CENTER_ID);
    }

    public static Long getMachineId() {
        return getLongValue(MACHINE_ID);
    }

}
