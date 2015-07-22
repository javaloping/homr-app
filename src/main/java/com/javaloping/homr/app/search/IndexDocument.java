package com.javaloping.homr.app.search;

import java.util.Map;

/**
 * Created by victor on 22/07/15.
 */
public interface IndexDocument {

    static Integer getIntegerProperty(Map<String, Object> map, String property) {
        if (map.containsKey(property)) {
            return (Integer) map.get(property);
        }
        return null;
    }

    static String getStringProperty(Map<String, Object> map, String property) {
        if (map.containsKey(property)) {
            return (String) map.get(property);
        }
        return null;
    }

    static Double getDoubleProperty(Map<String, Object> map, String property) {
        if (map.containsKey(property)) {
            return (Double) map.get(property);
        }
        return null;
    }

    static Float getFloatProperty(Map<String, Object> map, String property) {
        if (map.containsKey(property)) {
            return (Float) map.get(property);
        }
        return null;
    }
}
