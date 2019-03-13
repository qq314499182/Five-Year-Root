import React from 'react';
import moment from 'moment';

const ArticleListContent = ({ data: { createTime } }) => (
    <em>{moment(createTime).format('YYYY-MM-DD HH:mm')}</em>
);
export default ArticleListContent;
