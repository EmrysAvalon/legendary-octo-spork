package com.example.dierenarts.controller;

import com.example.dierenarts.model.User;
import com.example.dierenarts.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes={UserController.class})
class UserControllerTest {

    @MockBean
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ADMIN", authorities = {"ADMIN"})
    public void GetUsers() throws Exception {
        User user = new User();
        user.setUsername("user");
        List<User> allUsers = List.of(user);

        given(userService.getUsers()).willReturn(allUsers);

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

//    @Test
//    @WithMockUser(username = "user", authorities = "ADMIN")
//    public void postUserTest() throws Exception{
//        UserPostRequestDto userPostRequestDto = new UserPostRequestDto();
//        userPostRequestDto.setUsername("user");
//        userPostRequestDto.setPassword("P@ssw0rd");
//
//        Set<String> authorities = new HashSet<>();
//        authorities.add(WebSecurityConfig.ADMIN);
//        userPostRequestDto.setAuthorities(authorities);
//
//        User user = new User();
//        user.setUsername(userPostRequestDto.getUsername());
//
//        given(userService.createUser(any(UserPostRequestDto.class))).willReturn(user.getUsername());
//
//        mockMvc.perform(post("/users")
//                .content(mapper.writeValueAsString(userPostRequestDto))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(userPostRequestDto.getUsername()));
//                TODO figure out authorization in controller test
//    }

}