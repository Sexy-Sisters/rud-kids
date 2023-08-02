create table tbl_mystery_product
(
    mystery_product_id binary(16)   not null
        primary key,
    back_path          varchar(255) null,
    back_url           varchar(255) null,
    banner_path        varchar(255) null,
    banner_url         varchar(255) null,
    mobile_banner_path varchar(255) null,
    mobile_banner_url  varchar(255) null,
    bio                varchar(255) null,
    front_path         varchar(255) null,
    front_url          varchar(255) null,
    title              varchar(255) null,
    constraint UK_a3510lsxyycp2ygw47k8hjkyn
        unique (title)
);

drop table tbl_product_banner_image

alter table tbl_product add column banner_path varchar(255) null
alter table tbl_product add column banner_url varchar(255) null

alter table tbl_product rename mobile_path to mobile_banner_path varchar(255) null
alter table tbl_product rename mobile_url to mobile_banner_url varchar(255) null

alter table tbl_product drop column mystery
alter table tbl_product drop column product_status

alter table tbl_item drop column mystery

alter table tbl_item add column mystery_product_id binary(16) null,
add constraint FKtju2cxa4jdl3g2jbp61wuhy4m
        foreign key (mystery_product_id) references tbl_mystery_product (mystery_product_id)
