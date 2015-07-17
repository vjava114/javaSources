-- 실습환경
-- mysql
-- 회원형 메모장

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

-- 메모 테이블
create table member_board_v1
(no int primary key auto_increment,
 content varchar(4000),
 wdate datetime,
 id varchar(100));

