create table EDU_STUDENT.COUNTRY (
    COU_NO CHAR(8) primary key,
    COU_NM NVARCHAR2(20),
    COU_ADD NVARCHAR2(40),
    COU_TEL VARCHAR2(11)
);

create table EDU_STUDENT.POLICE (
    POL_NO CHAR(8) primary key,
    POL_NM NVARCHAR2(20),
    POL_ADD NVARCHAR2(40),
    POL_TEL VARCHAR2(11)
);

create table EDU_STUDENT.CATEGORY (
    CATE_NO CHAR(8) primary key,
    CATE_NM NVARCHAR2(20)
);

create table EDU_STUDENT.DEPARTMENT (
    DE_NO CHAR(8) primary key,
    DE_CATE CHAR(8),
    COU_NO CHAR(8),
    DE_ADD NVARCHAR2(40),
    DE_PEOPLE INTEGER,
    DE_FLOOR INTEGER,
    POL_NO CHAR(8)
);

insert into EDU_STUDENT.COUNTRY values ('C001','�j�H��','�˫n���q��1035��','03 758 1072');
insert into COUNTRY values ('C002','�˫n��','�˫n��˫n�����s��103��','03 747 2735');
insert into COUNTRY values ('C003','�s�Ψ�','�˫n��s�Ψ������14��','03 761 4186');
insert into COUNTRY values ('C004','�H����','���s��H����������136-1��','03 772 4839');
insert into COUNTRY values ('C005','��]��','�]�ߥ���]��������766��','03 733 3240');
insert into COUNTRY values ('C006','���ڨ�','���ڨ����ڸ�96��','03 766 0001');
insert into COUNTRY values ('C007','������','���������j��82��','03 766 1145');
insert into COUNTRY values ('C008','�H�q��','�H�q���H�q��53��1��','03 761 6072');
delete from COUNTRY;

insert into POLICE values ('M001','�˫n����','�]�߿��˫n����ڵ�72��','03 747 4796');
insert into POLICE values ('M002','�]�ߤ���','�]�߿��]�ߥ������109��','03 732 0059');
insert into POLICE values ('M003','�Y������','�]�߿��Y����������503��','03 766 3004');
commit;

insert into CATEGORY values ('G001','���J');
insert into CATEGORY values ('G002','�j��');
insert into CATEGORY values ('G003','���@�]�I');
insert into CATEGORY values ('G004','�p����');
commit;

insert into DEPARTMENT values ('D001','G001','C001','�]�߿��˫n���H��20��','100','1','M001');
insert into DEPARTMENT values ('D002','G002','C002','�]�߿��˫n��M����79��','3142','1','M001');
insert into DEPARTMENT values ('D003','G002','C003','�]�߿��˫n���s�s���T�q142��','1072','1','M001');
insert into DEPARTMENT values ('D004','G003','C004','�]�߿����s���ظ�1498��','32','1','M001');
insert into DEPARTMENT values ('D005','G001','C005','�]�߿��]�ߥ��̥���80��','106','1','M002');
insert into DEPARTMENT values ('D006','G001','C005','�]�߿��]�ߥ����_��117��','26','1','M002');
insert into DEPARTMENT values ('D007','G002','C005','�]�߿��]�ߥ��շR��109��','2038','2','M002');
insert into DEPARTMENT values ('D008','G002','C005','�]�߿��]�ߥ��j�P��53��','128','2','M002');
insert into DEPARTMENT values ('D009','G003','C006','�]�߿��Y�������ڨ��M����102��','353','1','M003');
insert into DEPARTMENT values ('D010','G004','C007','�]�߿��Y�������������@��69��','501','1','M003');
insert into DEPARTMENT values ('D011','G001','C008','�]�߿��Y�����H�q��������65��','194','1','M003');
insert into DEPARTMENT values ('D012','G004','C008','�]�߿��Y�����H�q��������116��','78','1','M003');
commit;


select distinct
    PO.POL_NM,PO.POL_TEL,
    count(DE.POL_NO) as DE_SUM
from DEPARTMENT DE
left join POLICE PO on PO.POL_NO = DE.POL_NO
where DE.DE_PEOPLE > 1000
group by PO.POL_NM,PO.POL_TEL
;