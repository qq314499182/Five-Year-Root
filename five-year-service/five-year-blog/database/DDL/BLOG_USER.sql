create table BLOG_USER
(
    ID varchar(40) not null comment '主键',
    USER_NAME varchar(20) null comment '用户名',
    USER_PASSWORD varchar(40) null comment '用户密码',
    USER_PHONE varchar(11) null comment '手机号',
    USER_EMAIL varchar(40) null comment '邮箱',
    CREATE_TIME timestamp null comment '创建时间',
    ENABLE smallint null comment '是否启用',
    CREATE_ID varchar(40) null comment '创建人',
    TS timestamp null comment '时间戳',
    constraint BLOG_USER_pk
        primary key (ID)
)
    comment '博客用户';

