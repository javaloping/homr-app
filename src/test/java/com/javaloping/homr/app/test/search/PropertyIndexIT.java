package com.javaloping.homr.app.test.search;

import com.javaloping.homr.app.model.Property;
import com.javaloping.homr.app.model.RentProperty;
import com.javaloping.homr.app.search.PropertySearcher;
import com.javaloping.homr.app.test.TestApp;
import com.javaloping.homr.app.type.PropertyType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author victormiranda@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApp.class})
public class PropertyIndexIT {

    @Autowired
    private PropertySearcher propertySearcher;

    @Test
    public void testSimpleIndex() throws IOException {
        Property property = new RentProperty();

        property.setId(1);
        property.setPrice(BigDecimal.ONE);
        property.setName("Fantastic rent");
        property.setType(PropertyType.FLAT);

        propertySearcher.index(property);

        Object res = propertySearcher.fetch(1);

    }
}
