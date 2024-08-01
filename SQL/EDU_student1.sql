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

insert into EDU_STUDENT.COUNTRY values ('C001','大埔里','竹南鎮公義路1035號','03 758 1072');
insert into COUNTRY values ('C002','竹南里','竹南鎮竹南里中山路103號','03 747 2735');
insert into COUNTRY values ('C003','山佳里','竹南鎮山佳里國光街14號','03 761 4186');
insert into COUNTRY values ('C004','埔頂里','後龍鎮埔頂里中興路136-1號','03 772 4839');
insert into COUNTRY values ('C005','綠苗里','苗栗市綠苗里中正路766號','03 733 3240');
insert into COUNTRY values ('C006','民族里','民族里民族路96號','03 766 0001');
insert into COUNTRY values ('C007','忠孝里','忠孝里光大街82號','03 766 1145');
insert into COUNTRY values ('C008','信義里','信義里信義路53巷1號','03 761 6072');
delete from COUNTRY;

insert into POLICE values ('M001','竹南分局','苗栗縣竹南鎮民族街72號','03 747 4796');
insert into POLICE values ('M002','苗栗分局','苗栗縣苗栗市金鳳街109號','03 732 0059');
insert into POLICE values ('M003','頭份分局','苗栗縣頭份市中興路503號','03 766 3004');
commit;

insert into CATEGORY values ('G001','公寓');
insert into CATEGORY values ('G002','大樓');
insert into CATEGORY values ('G003','公共設施');
insert into CATEGORY values ('G004','私營單位');
commit;

insert into DEPARTMENT values ('D001','G001','C001','苗栗縣竹南鎮中埔街20號','100','1','M001');
insert into DEPARTMENT values ('D002','G002','C002','苗栗縣竹南鎮和平街79號','3142','1','M001');
insert into DEPARTMENT values ('D003','G002','C003','苗栗縣竹南鎮龍山路三段142號','1072','1','M001');
insert into DEPARTMENT values ('D004','G003','C004','苗栗縣後龍鎮中華路1498號','32','1','M001');
insert into DEPARTMENT values ('D005','G001','C005','苗栗縣苗栗市米市街80號','106','1','M002');
insert into DEPARTMENT values ('D006','G001','C005','苗栗縣苗栗市光復路117號','26','1','M002');
insert into DEPARTMENT values ('D007','G002','C005','苗栗縣苗栗市博愛街109號','2038','2','M002');
insert into DEPARTMENT values ('D008','G002','C005','苗栗縣苗栗市大同路53號','128','2','M002');
insert into DEPARTMENT values ('D009','G003','C006','苗栗縣頭份市民族里和平路102號','353','1','M003');
insert into DEPARTMENT values ('D010','G004','C007','苗栗縣頭份市忠孝忠孝一路69號','501','1','M003');
insert into DEPARTMENT values ('D011','G001','C008','苗栗縣頭份市信義里中正路65號','194','1','M003');
insert into DEPARTMENT values ('D012','G004','C008','苗栗縣頭份市信義里中正路116號','78','1','M003');
commit;


select distinct
    PO.POL_NM,PO.POL_TEL,
    count(DE.POL_NO) as DE_SUM
from DEPARTMENT DE
left join POLICE PO on PO.POL_NO = DE.POL_NO
where DE.DE_PEOPLE > 1000
group by PO.POL_NM,PO.POL_TEL
;