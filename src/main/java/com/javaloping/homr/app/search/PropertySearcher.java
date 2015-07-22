package com.javaloping.homr.app.search;

import com.javaloping.homr.app.model.Property;

import java.io.IOException;
import java.util.List;

/**
 * @author victormiranda@gmail.com
 */
public interface PropertySearcher {

    PropertyDocument index(final Property property) throws IOException;

    Object fetch(final int propertyId);

    List<PropertyDocument> search(final PropertyDocument searchDocument);

    void refresh();
}
