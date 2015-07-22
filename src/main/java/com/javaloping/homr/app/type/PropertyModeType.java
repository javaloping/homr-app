package com.javaloping.homr.app.type;

/**
 * @author victormiranda@gmail.com
 */
public enum PropertyModeType {
    RENT, SALE;

    public static PropertyModeType byName(String mode) {
        for (PropertyModeType aux : PropertyModeType.values()) {
            if (aux.name().equals(mode)) {
                return aux;
            }
        }
        return null;
    }
}
