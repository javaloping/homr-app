package com.javaloping.homr.app.type;

/**
 * @author victormiranda@gmail.com
 */
public enum PropertyType {
    FLAT, HOUSE, OFFICE;

    public static PropertyType byName(String type) {
        for (PropertyType aux : PropertyType.values()) {
            if (aux.name().equals(type)) {
                return aux;
            }
        }
        return null;
    }
}
