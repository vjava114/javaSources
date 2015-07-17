-- 실습환경
-- mysql
-- 회원용 공지 게시판

-- 관리자로 접속함
-- mysql에 데이터베이스 생성
create database board;

-- 사용자 계정 생성
grant all privileges on board.* to 'spring'@'%' identified by 'spring' with grant option;

-- spring 사용자로 접속함
-- 회원 정보 테이블
create table member
(id varchar(100) primary key,
 password varchar(100),
 email varchar(200),
 authority varchar(200));

-- 테이블을 생성합니다.
create table member_board_v3
(no int primary key auto_increment,
 title varchar(4000),
 content varchar(4000),
 wdate datetime,
 read_count int,
 group_no int,
 sequence_in_group int,
 indent_in_group int,
 ref_no int,
 id varchar(100));

create index board_v3_idx
on member_board_v3(group_no desc, sequence_in_group asc);