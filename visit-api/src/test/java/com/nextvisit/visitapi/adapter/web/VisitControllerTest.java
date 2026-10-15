package com.nextvisit.visitapi.adapter.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void savesAVisitAndReadsItBack() throws Exception {
        MvcResult created = mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerName":"Okitha","visitDate":"2026-10-14","serviceType":"Cleaning"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("STORED"))
                .andReturn();

        String id = created.getResponse().getContentAsString().split("\"id\":\"")[1].split("\"")[0];

        mockMvc.perform(get("/visits/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Okitha"));
    }

    @Test
    void rejectsAVisitWithNoServiceType() throws Exception {
        mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerName":"Okitha","visitDate":"2026-10-14","serviceType":" "}
                                """))
                .andExpect(status().isBadRequest());
    }
}
