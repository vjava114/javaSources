-- �ǽ�ȯ��
-- mysql
-- ȸ���� ���� �Խ���

-- �����ڷ� ������
-- mysql�� �����ͺ��̽� ����
create database board;

-- ����� ���� ����
grant all privileges on board.* to 'spring'@'%' identified by 'spring' with grant option;

-- spring ����ڷ� ������
-- ȸ�� ���� ���̺�
create table member
(id varchar(100) primary key,
 password varchar(100),
 email varchar(200),
 authority varchar(200));

-- ���̺��� �����մϴ�.
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