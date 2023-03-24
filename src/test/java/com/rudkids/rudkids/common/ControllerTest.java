package com.rudkids.rudkids.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.rudkids.domain.auth.application.AuthService;
import com.rudkids.rudkids.domain.user.application.UserService;
import com.rudkids.rudkids.interfaces.user.UserController;
import com.rudkids.rudkids.interfaces.user.dto.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({
        UserController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserService userService;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected UserDtoMapper userDtoMapper;

    @MockBean
    protected RestTemplateBuilder restTemplateBuilder;
}