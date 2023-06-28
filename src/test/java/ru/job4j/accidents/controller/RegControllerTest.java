package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.UserService;
import ru.job4j.accidents.util.AbstractControllerTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RegControllerTest extends AbstractControllerTest {

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void shouldGetRegAndReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login/reg"));
    }

    @Test
    @WithMockUser
    void shouldSaveUserAndReturnRedirection() throws Exception {
        var authority = new Authority(1, "ROLE_USER");
        var user = new User(0, "password", "username", authority, true);
        when(userService.save(user)).thenReturn(Optional.of(user));
        this.mockMvc.perform(post("/reg")
                        .param("username", "username")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        assertThat(argument.getValue().getUsername(), is("username"));
    }

    @Test
    @WithMockUser
    void shouldSaveUserAndReturnErrorRedirection() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "username")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/reg?error=true"));
    }
}
