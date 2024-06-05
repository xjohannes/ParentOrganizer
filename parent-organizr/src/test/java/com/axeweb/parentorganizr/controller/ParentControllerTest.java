//package com.axeweb.parentorganizr.controller;
//
//import com.axeweb.parentorganizr.controller.exception.ParentNotFoundException;
//import com.axeweb.parentorganizr.model.Parent;
//import com.axeweb.parentorganizr.repository.ParentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ParentController.class)
//@AutoConfigureMockMvc
//class ParentControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    ParentRepository parentRepository;
//
//
//    List<Parent> parents = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() {
//        parents = List.of(
//                new Parent(1,"John", "Doe", "123456789"),
//                new Parent(2,"Jane", "Doe", "987654321")
//        );
//    }
//
//    @Test
//    void shouldFindAllPosts() throws Exception {
//        String jsonResponse = """
//                [
//                    {
//                        // Since this is a webControllerUnit test and the repository is mocked,
//                        // the id and version will not be set and therefor be null
//                        "id": 1,
//                        "firstName": "John",
//                        "lastName": "Doe",
//                        "children": [],
//                        "email": null,
//                        "phoneNumber": "123456789",
//                        "version": null
//                    },
//                    {
//                        "id": 2,
//                        "firstName": "Jane",
//                        "lastName": "Doe",
//                        "children": [],
//                        "email": null,
//                        "phoneNumber": "987654321",
//                        "version": null
//                    }
//                ]
//                """;
//        when(parentRepository.findAll()).thenReturn(parents);
//        mockMvc.perform(get("/parent"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonResponse));
//
//    }
//
//    @Test
//    void shouldFindAParentWhenGivenValidID () throws Exception {
//        var parent = parents.getFirst();
//
//        var json = STR."""
//                {
//                    "id":\{parent.getId()},
//                    "firstName":"\{parent.getFirstName()}",
//                    "lastName": \{parent.getLastName()},
//                    "children":\{parent.getChildren()},
//                    "email": \{parent.getEmail()},
//                    "phoneNumber": "\{parent.getPhoneNumber()}",
//                    "version": \{parent.getVersion()}
//                }
//                """;
//        Parent parentResult = parents.getFirst();
//        when(parentRepository.findById(1)).thenReturn(Optional.of(parentResult));
//        mockMvc.perform(get("/parent/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(json));
//    }
//
//    @Test
//    void shouldNotFindAParentWhenGivenInvalidID() throws Exception {
//        when(parentRepository.findById(999)).thenThrow(ParentNotFoundException.class);
//        mockMvc.perform(get("/parent/999"))
//                .andExpect(status().isNotFound());
//    }
//    @Test
//    void shouldCreateNewParent() throws Exception {
//        var parent = new Parent(3, "Johnny", "Doe", "54326789");
//        when(parentRepository.save(parent)).thenReturn(parent);
//
//        var json = STR."""
//                {
//                    "id":\{parent.getId()},
//                    "firstName":"\{parent.getFirstName()}",
//                    "lastName": "\{parent.getLastName()}",
//                    "children":\{parent.getChildren()},
//                    "email": "\{parent.getEmail()}",
//                    "phoneNumber": "\{parent.getPhoneNumber()}",
//                    "version": \{parent.getVersion()}
//                }
//                """;
//
//        mockMvc.perform(post("/parent")
//                        .contentType("application/json")
//                        .content(json)
//                        )
//                        .andExpect(status().isCreated());
//    }
//
//    @Test
//    void shouldNotCreateNewParentWhenGivenInvalidData() throws Exception {
//        var parent = new Parent(3, "Johnny", "Doe", "54326789");
//        when(parentRepository.save(parent)).thenReturn(parent);
//
//        var json = STR."""
//                {
//                    "firstName":"\{parent.getFirstName()}",
//                    "lastName": "\{parent.getLastName()}",
//                    "children":\{parent.getChildren()},
//                    "email": "\{parent.getEmail()}",
//
//                    "version": \{parent.getVersion()}
//                }
//                """;
//
//        mockMvc.perform(post("/parent")
//                        .contentType("application/json")
//                        .content(json)
//                        )
//                        .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldUpdateParent() throws Exception {
//        Parent updated = new Parent(1, "Fredrik", "Doe", "54326789");
//        String newFirstName = "Lars";
//        String newPhoneNumber = "99887766";
//        when(parentRepository.findById(1)).thenReturn(Optional.of(updated));
//        when(parentRepository.save(updated)).thenReturn(updated);
//
//        var json = STR."""
//                {
//                    "firstName":"\{newFirstName}",
//                    "lastName": "\{updated.getLastName()}",
//                    "phoneNumber": "\{newPhoneNumber}",
//                    "version": \{updated.getVersion()}
//                }
//                """;
//       mockMvc.perform(put("/parent/1")
//                        .contentType("application/json")
//                        .content(json)
//                )
//                .andExpect(status().isCreated());
//
//    }
//
//    @Test
//    void shouldNotUpdateParentWhenGivenInvalidData() throws Exception {
//        Parent updated = new Parent(1, "Fredrik", "Doe", "54326789");
//        when(parentRepository.findById(1)).thenReturn(Optional.of(updated));
//        when(parentRepository.save(updated)).thenReturn(updated);
//
//        var json = STR."""
//                {
//                    "id":\{updated.getId()},
//                    "firstName":"\{updated.getFirstName()}",
//                    "lastName": "\{updated.getLastName()}",
//                    "version": \{updated.getVersion()}
//                }
//                """;
//        mockMvc.perform(post("/parent")
//                        .contentType("application/json")
//                        .content(json)
//                )
//                .andExpect(status().isBadRequest());
//
//    }
//
//    @Test
//    void shouldDeleteParent() throws Exception {
//       doNothing().when(parentRepository).deleteById(1);
//        mockMvc.perform(delete("/parent/1"))
//                .andExpect(status().isNoContent());
//
//        verify(parentRepository, times(1)).deleteById(1);
//    }
// }
