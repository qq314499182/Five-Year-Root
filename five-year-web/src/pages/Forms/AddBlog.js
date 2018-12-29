import React, { PureComponent } from 'react';
import { connect } from 'dva';
import router from 'umi/router';
import { formatMessage, FormattedMessage } from 'umi/locale';
import {Form,Input, DatePicker, Select, Button, Card, InputNumber, Radio, Icon, Tooltip,} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

const FormItem = Form.Item;
const { TextArea } = Input;

const baseUrl = 'http://127.0.0.1:8080/blog-article/create';

@Form.create()
class AddBlog extends PureComponent {
  handleSubmit = () => {
    const { form: { validateFields } } = this.props;
    console.log(this.props);
    validateFields((err, values) => {
      if (!err) {
        const opts = {
          headers: {
            'content-type': 'application/json;charset=UTF-8'
          },
          method:"post",
          body:JSON.stringify(values)
        };
        fetch(baseUrl,opts).then(res=>{
          if(res.ok){
            router.push('/result/success')
          }else {
            router.push('/result/fail')
          }
        })
      }
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 },
        md: { span: 10 },
      },
    };

    const submitFormLayout = {
      wrapperCol: {
        xs: { span: 24, offset: 0 },
        sm: { span: 10, offset: 7 },
      },
    };

    return (
      <PageHeaderWrapper
        title={<FormattedMessage id="新增文章" />}
        content={<FormattedMessage id="在这里你可以把你想写的记录下来" />}
      >
        <Card bordered={false}>
          <Form  hideRequiredMark style={{ marginTop: 8 }}>
            <FormItem {...formItemLayout} label={<FormattedMessage id="标题" />}>
              {getFieldDecorator('title', {
                rules: [
                  {
                    required: true,
                    message: formatMessage({ id: '标题不能为空' }),
                  },
                ],
              })(<Input placeholder={formatMessage({ id: '填写文章标题' })} />)}
            </FormItem>
            <FormItem {...formItemLayout} label={<FormattedMessage id="内容" />}>
              {getFieldDecorator('content', {
                rules: [
                  {
                    required: true,
                    message: formatMessage({ id: '内容不能为空' }),
                  },
                ],
              })(
                <TextArea
                  style={{ minHeight: 32 }}
                  placeholder={formatMessage({ id: '填写文章内容' })}
                  rows={4}
                />
              )}
            </FormItem>
            <FormItem
              {...formItemLayout}
              label={<FormattedMessage id="是否公开" />}
            >
              <div>
                {getFieldDecorator('opened', {
                  initialValue: '1',
                })(
                  <Radio.Group>
                    <Radio value="1">
                      <FormattedMessage id="公开" />
                    </Radio>
                    <Radio value="3">
                    <FormattedMessage id="私有" />
                  </Radio>
                  </Radio.Group>
                )}
              </div>
            </FormItem>
            <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
              <Button type="primary" onClick={this.handleSubmit} >
                <FormattedMessage id="提交" />
              </Button>
              <Button style={{ marginLeft: 8 }}>
                <FormattedMessage id="取消" />
              </Button>
            </FormItem>
          </Form>
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default AddBlog;
