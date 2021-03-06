package com.javaloping.homr.app.search;

import java.util.Date;
import java.util.Map;

/**
 * @author victormiranda@gmail.com
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

    static Boolean getBooleanProperty(Map<String, Object> map, String property) {
        Boolean res = null;
        if (map.containsKey(property)) {
            res = (Boolean) map.get(property);
        }
        return res;
    }

    static Date getDateProperty(Map<String, Object> map, String property) {
        if (map.containsKey(property)) {
            return (Date) map.get(property);
        }
        return null;
    }
}
