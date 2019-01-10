import React, { Component } from 'react';
import { connect } from 'dva';
import { Col, Row, Card, Badge, Table, Divider } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import tittle from './Tittle.less';

const baseUrl = 'http://127.0.0.1:8080/blog-article/findOne';
class BasicProfile extends Component {
  state = {
    data:[],
  };

  componentDidMount() {
    this.getData((data)=>{
      this.setState({
        data: data
      })
    });
  }

  //获取后台数据
  getData = (callback) => {
    const getUrl = baseUrl+"?id="+this.props.location.query.id;
    const opts = {
      method:"get",
    };
    fetch(getUrl,opts).then(res=>{
      if(res.ok){
        res.json().then(data=>{
          callback(data)
        })
      }
    })
  };

  render() {
    //标题
    const pageHeaderContent =
        <div className={tittle.contentTitle}>
          {this.state.data.title}
        </div>
    ;
    //文章其他信息(阅读数,活跃度,排行)
    const extraContent = (
      <div className={tittle.extraContent}>
        <div className={tittle.statItem}>
          <p>阅读数</p>
          <p>{this.state.data.readNum}</p>
        </div>
        <div className={tittle.statItem}>
          <p>排行榜</p>
          <p>
            8<span> / 24</span>
          </p>
        </div>
        <div className={tittle.statItem}>
          <p>活跃度</p>
          <p>2,223</p>
        </div>
      </div>
    );

    return (
      <PageHeaderWrapper
        content={pageHeaderContent}
        extraContent={extraContent}
      >
        <Row gutter={24}>
          <Col xl={18} lg={24} md={24} sm={24} xs={24}>
            <Card bordered={false}>
              <div dangerouslySetInnerHTML={{__html: this.state.data.content}}></div>
            </Card>
          </Col>
          <Col xl={6} lg={24} md={24} sm={24} xs={24}>
            <Card bordered={false}>
              广告
            </Card>
          </Col>
        </Row>
      </PageHeaderWrapper>
    );
  }
}

export default BasicProfile;
