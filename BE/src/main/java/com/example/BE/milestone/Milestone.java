package com.example.BE.milestone;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("MILESTONE")
public class Milestone {

    @Id
    private String name;

    private LocalDateTime scheduledCompletionDate;
    private String descriptionForLabel;

    public Milestone() {
    }

    public Milestone(String name, LocalDateTime scheduledCompletionDate, String descriptionForLabel) {
        this.name = name;
        this.scheduledCompletionDate = scheduledCompletionDate;
        this.descriptionForLabel = descriptionForLabel;
    }

    public boolean isEmpty() {
        return name == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getScheduledCompletionDate() {
        return scheduledCompletionDate;
    }

    public void setScheduledCompletionDate(LocalDateTime scheduledCompletionDate) {
        this.scheduledCompletionDate = scheduledCompletionDate;
    }

    public String getDescriptionForLabel() {
        return descriptionForLabel;
    }

    public void setDescriptionForLabel(String descriptionForLabel) {
        this.descriptionForLabel = descriptionForLabel;
    }

}
