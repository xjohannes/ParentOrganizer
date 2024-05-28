package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Pupil;
import com.axeweb.parentorganizr.repository.PupilRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PupilController.class)
class PupilControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PupilRepository pupilRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldFindAllPupils() throws Exception {
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil1 = new Pupil("Inga", "Stoesdotter", birthdate);

        List<Pupil> pupils = List.of(pupil1);
        Mockito.when(pupilRepository.findAll()).thenReturn(pupils);

        mockMvc.perform(get("/pupil"))
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(pupils))
                );
    }

    @Test
    void shouldUpdatePupil() throws Exception {
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil = new Pupil("Anna Maria", "Stoesdotter", birthdate);

        Mockito.when(pupilRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(put("/pupil/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pupil)))
                .andExpect(status().isOk());
    }

}
