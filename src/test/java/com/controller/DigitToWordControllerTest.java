package com.controller;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bean.Digit;
import com.bean.DigitWord;
import com.config.MyWebApplicationInitializer;
import com.config.WebConfig;
import com.service.DigitToWordConvert;
import com.util.TestUtil;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MyWebApplicationInitializer.class,WebConfig.class})
@WebAppConfiguration
public class DigitToWordControllerTest {
//	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Mock
	private DigitWord digitWordBean;
	
	@Mock
	private Map<Integer,String> map;
	
	@Mock
	private DigitToWordConvert digitService;
	@InjectMocks
	private DigitToWordController  digitToWordController;
	
	
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders
				.standaloneSetup(DigitToWordController.class)
				.build();
		MockitoAnnotations.initMocks(this);
	}

	
	@Test	
	public void createDigitShouldReturnValidOutput() throws Exception{
		DigitWord digitWordBean=new DigitWord(12,"twelve   ..");
		
		Mockito.when(digitService.digitToWordConvert(anyInt())).thenReturn(digitWordBean);
		mockMvc.perform(post("/digits/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(new Digit(12))))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.digit", is(12)))
		.andExpect(jsonPath("$.word", is("twelve ")))
		.andExpect(jsonPath("$.*",hasSize(2)));
	}
	
	@Test
	public void creteDigitTestForNull() throws Exception{
		mockMvc.perform(post("/digits/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"digit\"}:\"test\""))
		.andExpect(status().isBadRequest());
	}
//	
//	@Test
//	public void GetDigitShouldReturnValidOutput() throws Exception{
////		DigitToWordConvert test=new DigitToWordConvert(new HashMap<>(), new DigitWord());
////		test.digitToWordConvert(13);
//		DigitWord digitTest=new DigitWord(13,"thirteen ");
//		Mockito.when(digitToWordConvertMock.digitToWordConvert(anyInt())).thenReturn(digitTest);
//		mockMvc.perform(get("/digits/13")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(jsonPath("$.digit", is(13)))
//				.andExpect(jsonPath("$.word", is("thirteen")))
//				.andExpect(jsonPath("$.*",hasSize(2)));
//	}
	
}
