import React, { Fragment,Component } from 'react';
import { formatMessage, FormattedMessage } from 'umi/locale';
import { Button, Row, Col, Icon, Steps, Card } from 'antd';
import Result from '@/components/Result';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import router from 'umi/router';


  export default class Success extends Component{

  returnHome=()=>{
    router.push("/list/search/homepage")
  };

  render(){

    const actions = (
      <Fragment>
        <Button type="primary" onClick={this.returnHome}>
          <FormattedMessage id="返回首页" />
        </Button>
      </Fragment>
    );

    return(
      <PageHeaderWrapper>
        <Card bordered={false}>
          <Result
            type="success"
            title={formatMessage({ id: 'app.result.success.title' })}
            actions={actions}
            style={{ marginTop: 48, marginBottom: 16 }}
          />
        </Card>
      </PageHeaderWrapper>
    );
  }
}



