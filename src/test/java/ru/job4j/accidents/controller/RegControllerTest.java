package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.job4j.accidents.util.AbstractControllerTest;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityService;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegControllerTest extends AbstractControllerTest {

    @MockBean
    private AuthorityService authorities;
    @MockBean
    private UserService users;

    @Test
    @WithMockUser
    public void shouldGetRegAndReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login/reg"));
    }

    @Test
    void thenPostRegSaveUserThenReturnRegPageErrorAndArgumentCaptureEquals() throws Exception {
        var authority = new Authority(1, "ROLE_USER");
        var user = new User(0, "password", "username", authority, true);
        when(authorities.findAuthorityByName(authority.getAuthority())).thenReturn(authority);
        when(users.save(user)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/reg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/reg?error=true"));
    }
}
