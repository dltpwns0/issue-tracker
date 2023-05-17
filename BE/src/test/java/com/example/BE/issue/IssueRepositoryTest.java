package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Nested
    @DisplayName("List<Labels> 를 채우지 않고 DB로 부터 데이터를 불러와 List<Issue> 목록을 필터링해 반환한다.")
    class FindAllIssuesWithoutLabels {

        @Test
        @DisplayName("열린 이슈들로만 이루어진 목록을 반환한다. (IssueSearchCondition 의 state 기본값은 true로 열린 이슈목록들을 필터링한다.)")
        void openIssues() {
            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            List<Issue> issuesWithoutLabels = issueRepository.findAllIssuesWithoutLabelsBy(new IssueSearchCondition());
            assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().contains(simpleOpenedIssue1, simpleOpenedIssue3);
        }

        @Test
        @DisplayName("닫힌 이슈들로만 이루어진 목록을 반환한다.")
        void closeIssues() {
            Issue simpleClosedIssue = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());


            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setState(false);

            List<Issue> issuesWithoutLabels = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().contains(simpleClosedIssue);
        }

        @Test
        @DisplayName("글 작성자 (닉네임)를 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByAuthor() {

            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setAuthor("BE");

            List<Issue> issuesWithoutLabels1 = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().contains(simpleOpenedIssue1, simpleOpenedIssue3);

            issueSearchCondition.setAuthor("FE");
            List<Issue> issuesWithoutLabels2 = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels2).isEmpty();
        }

        @Test
        @DisplayName("마일스톤 이름울 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByMilestone() {
            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"),
                    new ArrayList<Label>());

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setMilestoneName("BE STEP1");

            List<Issue> issuesWithoutLabels1 = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().contains(simpleOpenedIssue1, simpleOpenedIssue3);

            issueSearchCondition.setMilestoneName("FE STEP1");
            List<Issue> issuesWithoutLabels2 = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels2).isEmpty();
        }
    }

    @Test
    @DisplayName("Issue의 PK값과 대응되는 Label 객체를 가지는 List<IssueLabelMap> 목록을 반환한다.")
    void findAllIssueLabelMaps() {
        IssueLabelMap issueLabelMap1 = new IssueLabelMap(1, new Label("feature", "기능을 만들었슴둥", "#000000", "#004DE3"));
        IssueLabelMap issueLabelMap2 = new IssueLabelMap(1, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));
        IssueLabelMap issueLabelMap3 = new IssueLabelMap(2, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));

        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap(Set.of(1, 2));

        assertThat(issueLabelMaps).usingRecursiveFieldByFieldElementComparator().contains(issueLabelMap1, issueLabelMap2, issueLabelMap3);
    }

    @Test
    @DisplayName("열린 Issue 개수, 닫힌 Issue 개수, Label 개수, Milestone 개수를 Count 클래스에 담아 반환한다.")
    void calculateCount() {

        Count count = issueRepository.countEntities();

        assertThat(count.getOpenedIssuesCount()).isEqualTo(2);
        assertThat(count.getClosedIssuesCount()).isEqualTo(1);
        assertThat(count.getLabelsCount()).isEqualTo(2);
        assertThat(count.getMilestoneCount()).isEqualTo(1);
    }

}
