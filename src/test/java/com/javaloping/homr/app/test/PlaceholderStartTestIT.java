package com.javaloping.homr.app.test;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author victormiranda@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApp.class})
public class PlaceholderStartTestIT {

    @Autowired
    private WebApplicationContext wac;
    protected MockMvc mock;

    @Before
    public void setUp() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testPlaceholder() throws Exception {
        final MvcResult resultPublish = mock.perform(
                post("/user/placeholder")
                        .accept(MediaType.TEXT_PLAIN)
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        Assert.assertEquals("This is a placeholder", resultPublish.getResponse().getContentAsString());
    }

}
