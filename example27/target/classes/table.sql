-- 실습환경
-- Oracle 11g Express 에디션을 디폴트 설치
-- 데이터베이스 서비스 이름 : xe
-- 포트 : 1521

-- 오라클 데이터베이스에 관리자로 접속하여 사용자 계정을 생성합니다.
sqlplus / as sysdba

create user user1 identified by 1234;
grant connect, resource to user1;

-- 생성한 계정으로 로그온합니다.
sqlplus user1/1234

-- 게시판의 글번호로 사용할 객체를 생성합니다.
create sequence board_no_seq
start with 1
increment by 1;

-- 테이블을 생성합니다.
create table board
(no number(10) constraint board_no_pk primary key,
 title varchar2(100),
 notice varchar2(4000),
 writer varchar2(20),
 password varchar2(20),
 wdate date,
 ref number(10));
