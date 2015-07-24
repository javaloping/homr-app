package com.javaloping.homr.app.test.search;

import com.javaloping.homr.app.model.*;
import com.javaloping.homr.app.search.PropertyDocument;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        Assert.assertEquals(document.getLift(), property.getFeatures().getLift());
        Assert.assertEquals(document.getBedrooms(), property.getFeatures().getBedrooms());
        Assert.assertEquals(document.getBathrooms(), property.getFeatures().getBathrooms());
        Assert.assertEquals(document.getSqMeters(), property.getFeatures().getSqMeters());

        Address propertyAddress = property.getAddress();

        if (propertyAddress != null) {
            Assert.assertEquals(document.getLongitude(), property.getAddress().getLongitude());
            Assert.assertEquals(document.getLatitude(), property.getAddress().getLatitude());
        }

    }

    @Test
    public void testUnconversion() throws IOException {
        final Property property = getTestProperty();
        final PropertyDocument document = PropertyDocument.fromProperty(property);

        final Map<String, Object> map = new HashMap<>();

        map.put("id", document.getId());
        map.put("name", document.getName());
        map.put("price", document.getPrice().doubleValue());
        map.put("floor", document.getFloor());
        map.put("bedrooms", document.getFloor());
        map.put("bathrooms", document.getBathrooms());
        map.put("lift", document.getLift());
        map.put("sqMeters", document.getSqMeters());
        map.put("areaId", document.getAreaId());
        map.put("latitude", document.getLatitude());
        map.put("latitude", document.getLatitude());
        map.put("publishedDate", document.getPublishedDate());

        final PropertyDocument unconvertedDocument = PropertyDocument.fromSource(map);

        Assert.assertTrue(document.equals(unconvertedDocument));
    }

    private Property getTestProperty() {
        Property property = new RentProperty();

        property.setId(1);
        property.setName("Fabolous");

        property.setPrice(BigDecimal.valueOf(34232));
        property.setPublishedDate(new Date());

        Features features = new Features();
        features.setBathrooms(1);
        features.setBedrooms(2);
        features.setFloor(2);
        features.setLift(false);
        features.setSqMeters(55f);

        property.setFeatures(features);

        Address address = new Address();
        address.setLatitude(234234f);
        address.setLongitude(5345345f);
        Area area = new Area();
        area.setId(45);
        address.setArea(area);
        return property;
    }

}
