package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.UserResponse;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.appserver.service.model.User;
import net.andreinc.mockneat.MockNeat;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getUser_Exists() throws Exception {
        User user = new User();
        User persistedUser = userService.createNewUser(user);
        mvc.perform(get("/{userId}", persistedUser.getUserId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .value(Matchers.is(user.getUserId())))
                .andExpect(status().isOk());
//                User user = new User();
//        User persistedUser = userService.createNewUser(user);
//
//        mvc.perform(get("/{userId}", persistedUser.getUserId())
//                    .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

    @Test
    public void getUser_WrongUserId_Fails() throws Exception {
        String userId = "123456";

        mvc.perform(get("/{userId}", userId)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void createNewUser_Successful() {
//        User user = new User();
//        User persistedUser = userService.findByUserId(user.getUserId());
//        mvc.perform(post(user))
//    }



















}
