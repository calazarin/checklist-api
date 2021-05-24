package com.learning.springboot.checklistapi.dto;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
public class ChecklistItemDTO {

    private String guid;

    @NotBlank(message = "Checklist item description cannot be either null or empty")
    private String description;

    @NotNull(message = "Is completed is mandatory")
    private Boolean isCompleted;

    @NotNull(message = "Deadline is mandatory")
    private LocalDate deadline;

    private LocalDate postedDate;

    private CategoryDTO category;

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return ChecklistItemDTO.builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .deadline(checklistItemEntity.getDeadline())
                .postedDate(checklistItemEntity.getPostedDate())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .category(checklistItemEntity.getCategory() != null ?
                        CategoryDTO.builder()
                                .guid(checklistItemEntity.getCategory().getGuid())
                                .name(checklistItemEntity.getCategory().getName())
                        .build() :
                        null)
                .build();
    }
}
