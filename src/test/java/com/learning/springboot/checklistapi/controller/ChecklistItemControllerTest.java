package com.learning.springboot.checklistapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.springboot.checklistapi.dto.CategoryDTO;
import com.learning.springboot.checklistapi.dto.ChecklistItemDTO;
import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.service.ChecklistItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChecklistItemController.class)
public class ChecklistItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChecklistItemService checklistItemService;

    @Autowired
    private ObjectMapper objectMapper;

    private ChecklistItemDTO getChecklistItemDTO(String description, Boolean isCompleted, LocalDate deadline,
                                                 String categoryName){
        return ChecklistItemDTO.builder()
                .guid(UUID.randomUUID().toString())
                .description(description)
                .isCompleted(isCompleted)
                .deadline(deadline)
                .category(CategoryDTO.builder()
                        .guid(UUID.randomUUID().toString())
                        .name(categoryName)
                .build())
                .postedDate(LocalDate.now())
                .build();
    }

    private ChecklistItemEntity getChecklistItemEntity(Long id, String description, Boolean isCompleted, LocalDate deadline,
                                                       Long categoryId, String categoryName){
        ChecklistItemEntity entity = new ChecklistItemEntity();
        entity.setIsCompleted(isCompleted);
        entity.setGuid(UUID.randomUUID().toString());
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryId);
        categoryEntity.setGuid(UUID.randomUUID().toString());
        categoryEntity.setName(categoryName);
        entity.setCategory(categoryEntity);
        entity.setDeadline(deadline);
        entity.setDescription(description);
        entity.setChecklistItemId(id);
        entity.setPostedDate(LocalDate.now());
        return entity;
    }

    @Test
    public void shouldCallGetAllChecklistItemsAndReturn200() throws Exception {

        List<ChecklistItemEntity> findAllData = Arrays.asList(
                getChecklistItemEntity(1L, "Item 1", false, LocalDate.of(2021, 10, 01), 1L, "Cat 1"),
                getChecklistItemEntity(2L, "Item 2", true, LocalDate.of(2021, 10, 02), 2L, "Cat 2")
        );

        when(checklistItemService.findAllChecklistItems()).thenReturn(findAllData);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/checklist-items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].guid").isNotEmpty())
                .andExpect(jsonPath("$[0].isCompleted").value(false))
                .andExpect(jsonPath("$[0].description").value("Item 1"))
                .andExpect(jsonPath("$[0].deadline").value("2021-10-01"))
                .andExpect(jsonPath("$[0].postedDate").isNotEmpty())
                .andExpect(jsonPath("$[0].category").isNotEmpty())
                .andExpect(jsonPath("$[0].category.guid").isNotEmpty())
                .andExpect(jsonPath("$[0].category.name").value("Cat 1"))
                .andExpect(jsonPath("$[1].guid").isNotEmpty())
                .andExpect(jsonPath("$[1].isCompleted").value(true))
                .andExpect(jsonPath("$[1].description").value("Item 2"))
                .andExpect(jsonPath("$[1].deadline").value("2021-10-02"))
                .andExpect(jsonPath("$[1].postedDate").isNotEmpty())
                .andExpect(jsonPath("$[1].category").isNotEmpty())
                .andExpect(jsonPath("$[1].category.guid").isNotEmpty())
                .andExpect(jsonPath("$[1].category.name").value("Cat 2"));
    }

    @Test
    public void shouldCallEndpointAndAddNewChecklistItemAndReturn201() throws Exception {

        //having
        when(this.checklistItemService.addNewChecklistItem(anyString(),
                anyBoolean(), any(LocalDate.class), anyString())).thenReturn(
                getChecklistItemEntity(1L, "Item 1", false,
                        LocalDate.of(2021, 10, 01), 1L, "Test Cat")
        );

        ChecklistItemDTO requestPayload = getChecklistItemDTO("Test",
                true, LocalDate.now(),"Test Cat");

        //when - then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/checklist-items")
                .content(objectMapper.writeValueAsString(
                        requestPayload
                ))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.guid").isNotEmpty());

        verify(checklistItemService, times(1)).addNewChecklistItem(
                anyString(),anyBoolean(), any(LocalDate.class), anyString());
    }

    @Test
    public void shouldCallEndpointAndAddUpdateChecklistItemAndReturn204() throws Exception {

        //having
        when(this.checklistItemService.addNewChecklistItem(anyString(),
                anyBoolean(), any(LocalDate.class), anyString())).thenReturn(
                getChecklistItemEntity(1L, "Item 1", false,
                        LocalDate.of(2021, 10, 01), 1L, "Test Cat")
        );

        //when - then
        this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/api/checklist-items")
                .content(objectMapper.writeValueAsString(
                        getChecklistItemDTO("Test",
                                true, LocalDate.now(),"Test Cat")
                ))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldCallEndpointAndAddDeleteChecklistItemAndReturn204() throws Exception {

        //having
        String checklistItemGuid = UUID.randomUUID().toString();

         //when - then
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/v1/api/checklist-items/{guid}", checklistItemGuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(checklistItemService, times(1)).deleteChecklistItem(checklistItemGuid);
    }

}
