show databases;
use issue_tracker_database;

desc USER;
insert into USER(id, password, nickname, img_url)
values ('1234', 'codesquad', 'BE', 'https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg'),
       ('ghkdgus29', 'codesquad1', 'hyun', 'https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cute_cat.jpg'),
       ('cire', 'codesquad2', 'cire', 'https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/img.jpg');
select *
from USER;

desc MILESTONE;
insert into MILESTONE(name, scheduled_completion_date, description_for_label)
values ('BE STEP1', '2023-05-20 00:00:00', 'BE 1주차 이슈들');
select *
from MILESTONE;

desc ISSUE;
insert into ISSUE(number, contents, state, created_date, last_updated_date, title, user_id, milestone_name)
values (1, '첫 번째 이슈 내용', true, '2023-5-15 19:37:47', '2023-05-15 19:37:47', '제목 1', 1234, 'BE STEP1'),
       (2, '두 번째 이슈 내용', false, '2023-5-15 19:37:47', '2023-05-15 19:37:47', '제목 2', 1234, 'BE STEP1'),
       (3, '세 번째 이슈 내용', true, '2023-5-15 19:37:47', '2023-05-15 19:37:47', '제목 3', 1234, 'BE STEP1');
select *
from ISSUE;

desc ASSIGNS;
insert into ASSIGNS(user_id, issue_number)
values ('ghkdgus29', 1),
       ('cire', 2),
       ('ghkdgus29', 3),
       ('cire', 3);
select *
from ASSIGNS;

desc LABEL;
insert into LABEL(name, description, background_color, text_color)
values ('feature', '기능을 만들었슴둥', '#000000', '#004DE3'),
       ('fix', '버그를 고쳤음', '#123456', '#654321');
select *
from LABEL;

desc ISSUE_LABEL_RELATION;
insert into ISSUE_LABEL_RELATION(issue_number, label_name)
values (1, 'feature'),
       (1, 'fix'),
       (2, 'fix');
select *
from ISSUE_LABEL_RELATION;