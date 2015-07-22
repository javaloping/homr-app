package com.javaloping.homr.app.test.search;

import com.javaloping.homr.app.model.*;
import com.javaloping.homr.app.search.PropertyDocument;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author victormiranda@gmail.com
 */
public class PropertyDocumentTest {

    @Test
    public void testConversion() throws IOException {
        final Property property = getTestProperty();

        PropertyDocument document = PropertyDocument.fromProperty(property);

        Assert.assertEquals(document.getId(), property.getId());
        Assert.assertEquals(document.getName(), property.getName());
        Assert.assertEquals(document.getPrice(), property.getPrice().doubleValue());

        Assert.assertEquals(document.getFloor(), property.getFeatures().getFloor());
        Assert.assertEquals(document.isLift(), property.getFeatures().isLift());
        Assert.assertEquals(document.getBedrooms(), property.getFeatures().getBedrooms());
        Assert.assertEquals(document.getBathroom(), property.getFeatures().getBathrooms());
        Assert.assertEquals(document.getSqMeters(), property.getFeatures().getSqMeters());
        Assert.assertEquals(document.getSqMeters(), property.getFeatures().getSqMeters());
        Assert.assertEquals(document.getLongitude(), property.getAddress().getLongitude());
        Assert.assertEquals(document.getLatitude(), property.getAddress().getLatitude());
    }

    @Test
    public void testUnconversion() throws IOException {
        final Property property = getTestProperty();
    }

    private Property getTestProperty() {
        Property property = new RentProperty();

        property.setId(1);
        property.setName("Fabolous");

        property.setPrice(BigDecimal.valueOf(34232));

        Features features = new Features();
        features.setBathrooms(1);
        features.setBedrooms(2);
        features.setFloor(2);
        features.setLift(true);
        features.setSqMeters(55f);

        property.setFeatures(features);

        Address address = new Address();
        Area area = new Area();
        area.setId(45);
        address.setArea(area);
        return property;
    }

}
