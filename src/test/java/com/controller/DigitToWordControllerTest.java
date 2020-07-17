package com.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CorsFilter;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import com.bean.Digit;
import com.config.MyWebApplicationInitializer;
import com.config.WebConfig;
import com.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebApplicationInitializer.class,WebConfig.class})
@WebAppConfiguration
public class DigitToWordControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private Digit digitMock;

	@InjectMocks
	private DigitToWordController digitToWordController;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(digitToWordController)
                .addFilters(new CorsFilter())
                .build();
    }
	
	@Test	
	public void createDigitShouldReturnValidOutput() {
		Digit dto = new DigitBuilder()
                .digit("12")
                .word("qwert")
                .build();
		
		Digit digitTest=new Digit(12);
		mockmvc.perform(post("/digits/")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(digitTest))
        )
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.digit", is(12)))
		.andExpect(jsonPath("$.word", is(12)))
				
	}
}
