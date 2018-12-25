/*博客-文章表*/
create table BLOG_ARTICLE
(
   ID                   VARCHAR2(40)         not null,
   TITLE                VARCHAR2(4000),
   CATEGORY             VARCHAR2(40),
   CONTEXT              VARCHAR2(4000),
   READ_NUM             NUMBER,
   POINT_NUM            NUMBER,
   OPENED               NUMBER(1),
   TOP                  NUMBER(1),
   ARTICLE_CAT          NUMBER(1),
   TS                   DATE,
   CREAT_ID             VARCHAR2(40),
   CREAT_TIME           DATE,
   constraint PK_BLOG_ARTICLE primary key (ID)
);

comment on table BLOG_ARTICLE is
'博客文章';

comment on column BLOG_ARTICLE.ID is
'ID';

comment on column BLOG_ARTICLE.TITLE is
'标题';

comment on column BLOG_ARTICLE.CATEGORY is
'个人分类';

comment on column BLOG_ARTICLE.CONTEXT is
'内容';

comment on column BLOG_ARTICLE.READ_NUM is
'阅读数量';

comment on column BLOG_ARTICLE.POINT_NUM is
'点赞数量';

comment on column BLOG_ARTICLE.OPENED is
'是否公开';

comment on column BLOG_ARTICLE.TOP is
'是否置顶';

comment on column BLOG_ARTICLE.ARTICLE_CAT is
'文章类型';

comment on column BLOG_ARTICLE.TS is
'时间戳';

comment on column BLOG_ARTICLE.CREAT_ID is
'创建人ID';

comment on column BLOG_ARTICLE.CREAT_TIME is
'创建时间';
