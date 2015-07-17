-- �ǽ�ȯ��
-- Oracle 11g Express ������� ����Ʈ ��ġ
-- �����ͺ��̽� ���� �̸� : xe
-- ��Ʈ : 1521

-- ����Ŭ �����ͺ��̽��� �����ڷ� �����Ͽ� ����� ������ �����մϴ�.
sqlplus / as sysdba

create user user1 identified by 1234;
grant connect, resource to user1;

-- ������ �������� �α׿��մϴ�.
sqlplus user1/1234

-- �Խ����� �۹�ȣ�� ����� ��ü�� �����մϴ�.
create sequence board_no_seq
start with 1
increment by 1;

-- ���̺��� �����մϴ�.
create table board
(no number(10) constraint board_no_pk primary key,
 title varchar2(100),
 notice varchar2(4000),
 writer varchar2(20),
 password varchar2(20),
 wdate date,
 ref number(10));
