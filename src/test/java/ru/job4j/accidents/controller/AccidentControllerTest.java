package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import ru.job4j.accidents.helper.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AccidentControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser
    public void shouldGetCreateAccidentAndReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    public void shouldGetUpdateAccidentAndReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                get("/formUpdateAccident")
                        .param("id", "6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/update"));
    }
}
