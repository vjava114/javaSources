-- �ǽ�ȯ��
-- mysql
-- ȸ���� �޸���

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

-- �޸� ���̺�
create table member_board_v1
(no int primary key auto_increment,
 content varchar(4000),
 wdate datetime,
 id varchar(100));

