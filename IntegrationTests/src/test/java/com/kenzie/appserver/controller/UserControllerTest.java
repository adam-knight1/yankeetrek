package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import net.andreinc.mockneat.MockNeat;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    private final MockNeat mockNeat = MockNeat.threadLocal();


    /*@Test
    public void getUser_Successful() throws Exception {
        User user = new User();
        User persistedUser = userService.createNewUser(user);
        mvc.perform(get("/{userId}", persistedUser.getUserId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .value(is(user.getUserId())))
                .andExpect(status().isOk());
    }
*/
    @Test
    public void getUser_WrongUserId_Fails() throws Exception {
        String userId = "123456";

        mvc.perform(get("/{userId}", userId)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewUser_Successful() throws Exception {
        String email = "random@email.com";
        User user = new User();
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail(email);

        mvc.perform(post("/user")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userCreateRequest)))
                .andExpect(jsonPath(("userId"))
                        .exists())
                .andExpect(jsonPath("email")
                        .value(email))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewUser_NullInfo_Fails() throws Exception {

        mvc.perform(post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(null)))
                .andExpect(jsonPath(("userId"))
                        .doesNotExist())
                .andExpect(status().isBadRequest());
    }

   /* @Test
    public void updateUser_Successful() throws Exception {
        String email = "random@email.com";
        User user = new User();
        UserCreateRequest userCreateRequest = new UserCreateRequest();

        mvc.perform(put("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userCreateRequest)))
                .andExpect(jsonPath(("userId"))
                        .value(is(user.getUserId())))
                .andExpect(status().isOk());
    }
*/

    @Test
    public void updateUser_Successful() throws Exception {
        //passing now -adam
        UserRecord existingUser = new UserRecord();
        existingUser.setUsername("adam");
        existingUser.setEmail("knight");
        existingUser.setPassword("aknight");
        existingUser.setUserId("342342342");
        existingUser = userRepository.save(existingUser);

        String existingUserId = existingUser.getUserId();

        UserCreateRequest userUpdateRequest = new UserCreateRequest();
        userUpdateRequest.setUsername("newAdam");
        userUpdateRequest.setEmail("newKnight");
        userUpdateRequest.setPassword("new");

        mvc.perform(put("/user/" + existingUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("userId")).value("342342342"));
    }

    /*@Test
    public void updateUser_NonExistentUser_Fails() throws Exception {
        UserCreateRequest userCreateRequest = new UserCreateRequest();

        mvc.perform(put("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userCreateRequest)))
                .andExpect(jsonPath(("userId"))
                        .value(is("123456")))
                .andExpect(status().isNotFound());
    }*/

   /* @Test
    public void deleteUser_ExistingUser_Successful() throws Exception {
        User user = new User();
        UserCreateRequest userCreateRequest = new UserCreateRequest();

        mvc.perform(delete("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .value(is(user.getUserId())))
                .andExpect(status().isNoContent());
    }
*/


   /* @Test
    public void deleteUser_NonExistentUser_Fails() throws Exception {
        UserCreateRequest userCreateRequest = new UserCreateRequest();

        mvc.perform(delete("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .doesNotExist())
                .andExpect(status().isNotFound());
    }*/

    @Test
    public void deleteUser_ExistingUser_Successful() throws Exception {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId("test-user-id");
        userRecord.setUsername("testUsername");
        userRecord.setPassword("testPassword");
        userRecord.setEmail("test@email.com");

        userRepository.save(userRecord);

        mvc.perform(delete("/user/" + userRecord.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(userRecord.getUserId()).isPresent());
    }

}
