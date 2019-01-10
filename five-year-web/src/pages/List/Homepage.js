import React,{Component,Fragment} from 'react';
import {Form, Card, Select, List, Tag, Icon, Row, Col, Button,Pagination} from 'antd';
import router from 'umi/router';
import styles from "../List/Articles.less";
import ContentList from './ContentList';

const pageSize = 5;
const baseUrl = 'http://127.0.0.1:8080/blog-article';


export default class TestFrom extends Component{
    state = {
      data:[],
      loading: false,
      pageNum:1
    };


componentDidMount(){
    this.getData((data)=>{
      this.setState({
        data: data,
        loading: false
      })
    })
  }

  getData = (callback) => {
    const opts = {
        method:"post",
        body:JSON.stringify({
          pageNum: this.state.pageNum,
          pageSize: pageSize,
        })
      };
      fetch(baseUrl,opts).then(res=>{
        if(res.ok){
          res.json().then(data=>{
            callback(data)
          })
        }
      })
  };

  fetchMore = () =>{
    this.setState({
      pageNum:this.state.pageNum + 1,
      loading:true
    },()=>{
      this.getData((v)=>{
        const { data } = this.state;
        this.setState({
          data: data.concat(v),
          loading: false
        })
      })
    })
  };

  getDetil = (item) =>{
    router.push(
      {
        pathname: '/profile/BlogDetail',
        query: {
          id: item.id
        },
      }
    )
  };


  render() {
    const IconText = ({ type, text }) => (
      <span>
        <Icon type={type} style={{ marginRight: 8 }} />
        {text}
      </span>
    );

    const loadMore =
      this.state.data.length > 0 ? (
        <div style={{ textAlign: 'center', marginTop: 16 }}>
          <Button onClick={this.fetchMore} style={{ paddingLeft: 48, paddingRight: 48 }}>
            {this.state.loading ? (
              <span>
                <Icon type="loading" /> 加载中...
              </span>
            ) : (
              '加载更多'
            )}
          </Button>
        </div>
      ) : null;



    return (
      <Fragment>
        <Card style={{ marginTop: 24 }} bordered={false} bodyStyle={{ padding: '8px 32px 32px 32px' }}>
          <List
            size="large"
            loading={this.state.data.length === 0 ? this.state.data : false}
            rowKey="id"
            itemLayout="vertical"
            loadMore={loadMore}
            dataSource={this.state.data}
            renderItem={item => (
              <List.Item
                key={item.id}
                actions={[
                  <IconText type="star-o" text={item.readNum} />,
                  <IconText type="like-o" text={item.pointNum} />
                ]}
                extra={<div className={styles.listItemExtra} />}
              >
                <List.Item.Meta
                  title={
                    <a className={styles.listItemMetaTitle} onClick={()=>this.getDetil(item)}>
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
                <ContentList data={item} />
              </List.Item>
            )}
          />
        </Card>
      </Fragment>
    );
  }
}
