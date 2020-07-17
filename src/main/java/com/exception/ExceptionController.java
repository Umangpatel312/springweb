package com.exception;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controller.DigitToWordController;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler({RuntimeException.class})
    public String handleException(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return "enter valid input";
    }
}
