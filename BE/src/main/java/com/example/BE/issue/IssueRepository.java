package com.example.BE.issue;

import com.example.BE.assignee.Assignee;
import com.example.BE.issue.dto.CountDTO;
import com.example.BE.issue.dto.IssueNumberWithLabelDTO;
import com.example.BE.issue.mapper.AssigneeRowMapper;
import com.example.BE.issue.mapper.CountRowMapper;
import com.example.BE.issue.mapper.IssueLabelMapRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IssueRepository extends CrudRepository<Issue, Integer>, IssueRepositoryCustom {

    @Query(value = "select ir.issue_number, l.name, l.description, l.background_color, l.text_color " +
            "from ISSUE_LABEL_RELATION ir " +
            "left outer join LABEL l on ir.label_name = l.name " +
            "where ir.issue_number in (:issueNumbers)",
            rowMapperClass = IssueLabelMapRowMapper.class)
    List<IssueNumberWithLabelDTO> findIssueLabelMapsBy(@Param("issueNumbers") Set<Integer> issueNumbers);

    @Query(value = "select a.issue_number, u.id, u.password, u.nickname, u.img_url " +
            "from ASSIGNS a " +
            "left join USER u on a.user_id = u.id " +
            "where a.issue_number in (:issueNumbers)",
            rowMapperClass = AssigneeRowMapper.class)
    List<Assignee> findAssigneesBy(@Param("issueNumbers") Set<Integer> issueNumbers);

    @Query(value = "select " +
            "(select count(number) from ISSUE where state=true) as openedIssuesCount, " +
            "(select count(number) from ISSUE where state=false) as closedIssuesCount, " +
            "(select count(name) from LABEL) as labelsCount, " +
            "(select count(name) from MILESTONE) as milestoneCount",
            rowMapperClass = CountRowMapper.class)
    CountDTO countEntities();
}
