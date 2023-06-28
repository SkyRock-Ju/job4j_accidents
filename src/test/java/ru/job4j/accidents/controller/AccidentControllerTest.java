package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;
import ru.job4j.accidents.util.AbstractControllerTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class AccidentControllerTest extends AbstractControllerTest {

    @MockBean
    private AccidentService accidentService;
    @MockBean
    private AccidentTypeService typeService;
    @MockBean
    private RuleService ruleService;

    @Test
    @WithMockUser
    public void shouldGetAccidentAndReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    void shouldGetAccidentAndReturnStatusOkWithModelAttributes() throws Exception {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(0, "Две машины"));
        types.add(new AccidentType(1, "Машина и человек"));
        types.add(new AccidentType(2, "Машина и велосипед"));
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(0, "Статья 1"));
        rules.add(new Rule(1, "Статья 2"));
        rules.add(new Rule(2, "Статья 3"));

        when(ruleService.findAllRules()).thenReturn(rules);
        when(typeService.findAllTypes()).thenReturn(types);
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(model().attribute("types", is(types)))
                .andExpect(model().attribute("rules", is(rules)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldSaveAccidentAndReturnStatusRedirect() throws Exception {
        var accidentType = new AccidentType(1, null);
        var accident = new Accident(0, "name", "text", "address",
                accidentType, Collections.emptySet());
        this.mockMvc.perform(post("/createAccident")
                .param("name", "name")
                .param("text", "text")
                .param("address", "address")
                .param("type.id", "1")
                .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        ArgumentCaptor<Accident> accidentArgumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).saveAccident(accidentArgumentCaptor.capture());
        assertThat(accidentArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(accident);
    }

    @Test
    @WithMockUser
    void shouldGetUpdateAccidentAndReturnView() throws Exception {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(0, "Две машины"));
        types.add(new AccidentType(1, "Машина и человек"));
        types.add(new AccidentType(2, "Машина и велосипед"));
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(0, "Статья 1"));
        rules.add(new Rule(1, "Статья 2"));
        rules.add(new Rule(2, "Статья 3"));
        Accident accident = new Accident(
                1,
                "name",
                "text",
                "address",
                types.get(0),
                Set.of(rules.get(0))
        );

        when(ruleService.findAllRules()).thenReturn(rules);
        when(typeService.findAllTypes()).thenReturn(types);
        when(accidentService.findAccidentById(accident.getId()))
                .thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("id", String.valueOf(accident.getId())))
                .andDo(print())
                .andExpect(model().attribute("accident", is(accident)))
                .andExpect(view().name("accident/update"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetUpdateAccidentAndReturnError() throws Exception {
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(view().name("error/404"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateAccidentAndReturnRedirect() throws Exception {
        Set<Rule> rules = new HashSet<>();
        rules.add(new Rule(0, "Статья 1"));
        rules.add(new Rule(1, "Статья 2"));
        rules.add(new Rule(2, "Статья 3"));
        var accidentType = new AccidentType(1, null);
        var accident = new Accident(0, "name", "text", "address",
                accidentType, rules);
        when(ruleService.findRulesByIds(new String[]{"1", "2", "3"})).thenReturn(rules);
        this.mockMvc.perform(post("/updateAccident")
                        .param("name", "name")
                        .param("text", "text")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        ArgumentCaptor<Accident> accidentArgumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).updateAccident(accidentArgumentCaptor.capture());
        assertThat(accidentArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(accident);
    }
}
