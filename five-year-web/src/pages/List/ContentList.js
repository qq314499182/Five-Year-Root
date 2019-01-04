import React from 'react';
import moment from 'moment';
import { Avatar } from 'antd';
import styles from '@/components/ArticleListContent/index.less';

const ContentList = ({ data: { contentList, createTime, avatar, createId, href } }) => (
  <div className={styles.listContent}>
    <div className={styles.description}>{contentList}</div>
    <div className={styles.extra}>
      <Avatar src={avatar} size="small" />
      <a href={href}>{createId}</a> 发布在 <a href={href}>{href}</a>
      <em>{moment(createTime).format('YYYY-MM-DD HH:mm')}</em>
    </div>
  </div>
);

export default ContentList;
