package cucumber.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public final String workspace;
    public final String url;
    public final String environment;
    public final Map<String, Object> deviceMap;
    public final String username;

    public Config() {
        this.workspace = System.getProperty("user.dir");
        this.username = System.getProperty("username", "UserOne");
        this.environment = System.getProperty("environment", "testofw");

        this.url = System.getProperty("seleniumGrid", "http://0.0.0.0:4444/wd/hub");
        // changes device in use
        String deviceName = System.getProperty("deviceName", "chrome");
        this.deviceMap = getDevice(deviceName);
    }

    private HashMap<String, Object> getDevice(String device) {

        Type hashType = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        Gson gson = new Gson();

        InputStream deviceFile = getClass().getResourceAsStream("/devices/devices.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(deviceFile));

        JsonParser parser = new JsonParser();

        JsonElement jsonElement = parser.parse(reader).getAsJsonObject().get(device);

        return gson.fromJson(jsonElement, hashType);
    }
}
