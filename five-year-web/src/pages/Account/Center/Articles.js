import React, { PureComponent } from 'react';
import { List, Icon, Tag } from 'antd';
import ArticleListContent from '@/components/ArticleListContent/ArticlesList';
import styles from './Articles.less';
import {postRequest} from '@/utils/restfulUtils';
import {serviceIP} from '@/config/serviceIP';
import { Pagination } from 'antd';

const baseUrl = serviceIP+'/five-service/user-info';

class Center extends PureComponent {

  state = {
    total: 0,
    list:[],
    pageSize : 6,
    pageNum:1,
    currentPage:1
  };

  componentDidMount() {
    this.getDate(data=>{
      if(data != null){
        this.setState({
          pageNum:data.nextPage,
          list:data.list,
          total:data.total,
        })
      }
    })
  };

  getDate = (callback) => {
    const body = {
      pageNum: this.state.pageNum,
      pageSize: this.state.pageSize
    };
    postRequest(baseUrl+'/search/userPage',body,response =>{
      callback(response);
    })
  };

  onChange = (pageNum) =>{
    this.setState({
      pageNum:pageNum
    });
    this.getDate(data=>{
      if(data){
        this.setState({
          list:data.list,
        });
      }
    });
  };

  render() {
    const IconText = ({ type, text }) => (
      <span>
        <Icon type={type} style={{ marginRight: 8 }} />
        {text}
      </span>
    );

    return (
      <div>
        <List
          size="large"
          className={styles.articleList}
          rowKey="id"
          itemLayout="vertical"
          dataSource={this.state.list}
          renderItem={item => (
            <List.Item
              key={item.id}
              actions={[
                <IconText type="read" text={item.readNum} />,
                <IconText type="like-o" text={item.pointNum} />,
                <IconText type="message" text={item.message} />,
                <ArticleListContent data={item} />
              ]}
            >
              <List.Item.Meta
                title={
                  <a   className={styles.listItemMetaTitle} href={item.href}>
                    {item.title}
                  </a>
                }
              />
            </List.Item>
          )}
        />
        <div>
          <center>
            <Pagination
              total={this.state.total}
              pageSize={this.state.pageSize}
              hideOnSinglePage={true}
              onChange={this.onChange}
              defaultCurrent={1}
            />
          </center>
        </div>
      </div>

    );
  }
}

export default Center;
