package com.learning.springboot.checklistapi.dto;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ChecklistItemDTO {

    private String guid;

    private String description;

    private Boolean isCompleted;

    private LocalDate deadline;

    private LocalDate postedDate;

    private String categoryGuid;

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return ChecklistItemDTO.builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .deadline(checklistItemEntity.getDeadline())
                .postedDate(checklistItemEntity.getPostedDate())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .categoryGuid(checklistItemEntity.getCategory().getGuid())
                .build();
    }
}
