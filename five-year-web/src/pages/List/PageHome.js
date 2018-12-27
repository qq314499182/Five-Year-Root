import React,{Component,Fragment} from 'react';
import {Form, Card, Select, List, Tag, Icon, Row, Col, Button} from 'antd';
import styles from "../List/Articles.less";
import ArticleListContent from '@/components/ArticleListContent';

export default class TestFrom extends Component{
  constructor(props) {
    super(props);
    this.state = {data:[]};
  }

componentDidMount(){
    const opts = {
        method:"GET"
    };
    const url = 'http://127.0.0.1:8080/blog-article/findAll';
    fetch(url,opts).then(res=>{
      if(res.ok){
        res.json().then(data=>{
          this.setState({data})
        })
      }
    })
  }

  render() {
    const IconText = ({ type, text }) => (
      <span>
        <Icon type={type} style={{ marginRight: 8 }} />
        {text}
      </span>
    );

    return (
      <Fragment>
        <Card style={{ marginTop: 24 }} bordered={false} bodyStyle={{ padding: '8px 32px 32px 32px' }}>
          <List
            size="large"
            loading={this.state.data.length === 0 ? this.state.data : false}
            rowKey="id"
            itemLayout="vertical"
            // loadMore={loadMore}
            dataSource={this.state.data}
            renderItem={item => (
              <List.Item
                key={item.id}
                actions={[
                  <IconText type="star-o" text={item.readNum} />,
                  <IconText type="like-o" text={item.pointNum} />,
                  //<IconText type="message" text={item.message} />,
                ]}
                extra={<div className={styles.listItemExtra} />}
              >
                <List.Item.Meta
                  title={
                    <a className={styles.listItemMetaTitle} href={item.href}>
                      {item.title}
                    </a>
                  }
                  description={
                    <span>
                      <Tag>Ant Design</Tag>
                      <Tag>设计语言</Tag>
                      <Tag>蚂蚁金服</Tag>
                    </span>
                  }
                />
                <ArticleListContent data={item} />
              </List.Item>
            )}
          />
        </Card>
      </Fragment>
    );
  }
}