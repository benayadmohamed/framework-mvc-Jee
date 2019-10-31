package framework.configurationFramwork.models;

import java.util.HashMap;

public class Model extends HashMap<String, Object> {

    public void addAttribute(String key, Object value) {
        this.put(key, value);
    }
}
