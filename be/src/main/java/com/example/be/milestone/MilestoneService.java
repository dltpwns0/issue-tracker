package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import com.example.be.milestone.dto.MilestoneUpdateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public boolean createMilestone(MilestoneCreateFormDTO milestoneCreateFormDTO) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneCreateFormDTO.getName());
        if (optionalMilestone.isPresent()) {
            return false;
        }
        Milestone milestone = new Milestone(
                milestoneCreateFormDTO.getName(),
                milestoneCreateFormDTO.getScheduledCompletionDate(),
                milestoneCreateFormDTO.getDescriptionForLabel());
        milestoneRepository.save(milestone.createEntityForInsert());
        return true;
    }

    public boolean updateMilestone(MilestoneUpdateFormDTO milestoneUpdateFormDTO) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneUpdateFormDTO.getName());
        if (optionalMilestone.isEmpty()) {
            return false;
        }
        Milestone milestone = new Milestone(
                milestoneUpdateFormDTO.getName(),
                milestoneUpdateFormDTO.getScheduledCompletionDate(),
                milestoneUpdateFormDTO.getDescriptionForLabel());
        milestoneRepository.save(milestone.createEntityForUpdate());
        return true;
    }

}
