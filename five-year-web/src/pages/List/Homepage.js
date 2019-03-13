import React,{Component,Fragment} from 'react';
import {Form, Card, Select, List, Tag, Icon, Row, Col, Button,Pagination} from 'antd';
import router from 'umi/router';
import styles from "../List/Articles.less";
import ContentList from './ContentList';
import {serviceIP} from '@/config/serviceIP';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import { Input } from 'antd';

const pageSize = 5;
const baseUrl = serviceIP+'/five-service/blog-article';
const id = 0;

export default class TestFrom extends Component{

  state = {
      data:[],
      loading: false,
      pageNum:1,
      id:null
    };

componentDidMount(){
    this.getData((data)=>{
      this.setState({
        data: data,
        loading: false
      })
    })
  }

  //分页获取列表数据
  getData = (callback) => {
    const opts = {
      method:"post",
      body:JSON.stringify({
        pageNum: this.state.pageNum,
        pageSize: pageSize
      }),
      headers: {
        "Content-Type": "application/json;charset=UTF-8"
      }
    };
    fetch(baseUrl+'/search/page',opts).then(res=>{
      if(res.ok){
        res.json().then(data=>{
          callback(data)
        })
      }
    })
  };

  //点赞
  getPoint = (id) => {
    const opts = {
      method:"post",
      body:JSON.stringify({
        id:id
      }),
      headers: {
        "Content-Type": "application/json;charset=UTF-8"
      },
    };
    fetch(baseUrl+'/point',opts).then(res=>{
      if(res.ok){
        const data = [...this.state.data];
        for (let i=0;i<data.length;i++) {
          if(data[i].id === id){
            data[i].pointNum+=1;
          }
        }
        this.setState({data});
      }
    })
  };

  //加载更多项
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

  //跳转详情页
  getDetil = (item) =>{
    router.push({
        pathname: '/profile/BlogDetail',
        query: {
          id: item.id
        }
      })
  };

  render() {
    const IconText = ({ type, text }) => (
      <span>
        <Icon type={type} style={{ marginRight: 8 }} />
        {text}
      </span>
    );

    const IconPoint = ({ type, text , id }) => (
      <span onClick={()=>this.getPoint(id)}>
        <Icon type={type} style={{ marginRight: 8 }} />
        {text}
      </span>
    );

    const mainSearch = (
      <div style={{ textAlign: 'center' }}>
        <Input.Search
          placeholder="请输入"
          enterButton="搜索"
          size="large"
          onSearch={this.handleFormSubmit}
          style={{ width: 522 }}
        />
      </div>
    );

    const loadMore =
      this.state.data.length > 0 ? (
        <div style={{ textAlign: 'center', marginTop: 16 }}>
          <Button onClick={this.fetchMore} style={{ paddingLeft: 48, paddingRight: 48 }} >
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
      <PageHeaderWrapper
        content={mainSearch}
      >
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
                    <IconText type="read-o" text={item.readNum} />,
                    <IconPoint type="like-o" text={item.pointNum} id={item.id}/>
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
      </PageHeaderWrapper>
    );
  }
}
