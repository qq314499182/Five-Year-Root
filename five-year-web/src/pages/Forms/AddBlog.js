import React, { PureComponent } from 'react';
import { connect } from 'dva';
import router from 'umi/router';
import { formatMessage, FormattedMessage } from 'umi/locale';
import {Form,Input, DatePicker, Select, Button, Card, InputNumber, Radio, Icon, Tooltip,} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import Edit from 'wangeditor';

const FormItem = Form.Item;
const { TextArea } = Input;

const baseUrl = 'http://127.0.0.1:8080/blog-article/create';

@Form.create()
class AddBlog extends PureComponent {

  constructor(props) {
    super(props);
    this.state = {
      editorHtml: '',
      editorText: '',
    }
  }

  componentDidMount() {
    const elem = this.node;
    const editor = new Edit(elem);
    editor.customConfig.uploadImgShowBase64 = true;
    editor.customConfig.zIndex = 100;
    editor.customConfig.onchange = (html) => {
      this.setState({
        editorHtml: html,
        editorText: editor.txt.text()
      });

      this.props.form.setFieldsValue({
        'content': html
      });
    }
    editor.create();
  }

  handleSubmit = () => {
    const { form: { validateFields } } = this.props;
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

  //自定义表单验证规则
  validateEditorFrom = (rule, value, callback) => {
    //此处根据富文本框的text值进行验证，但注意富文本框中输入空格，使用‘&nbsp‘表示，此方法不能处理只输入空格的验证。
    if (this.state.editorText.trim() === '' || this.state.editorText === '&nbsp') {
      callback('内容不能为空');
    }
    callback();
  }

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;

    const formItemLayout = {
      labelCol: {
        xs: { span: 30 },
        sm: { span: 7 },
      },
      wrapperCol: {
        xs: { span: 30 },
        sm: { span: 12 },
        md: { span: 10 },
      },
    };

    const submitFormLayout = {
      wrapperCol: {
        xs: { span: 30, offset: 0 },
        sm: { span: 10, offset: 7 },
      },
    };

    return (
      <PageHeaderWrapper title="新增文章" content="在这里你可以把你想写的记录下来">
        <Card bordered={false}>
          <Form  hideRequiredMark style={{ marginTop: 8 }}>
            <FormItem {...formItemLayout} label="标题">
              {getFieldDecorator('title', {
                rules: [
                  {
                    required: true,
                    message: '标题不能为空',
                  },
                ],
              })(<Input style={{ width: 700 }} placeholder="填写文章标题" />)}
            </FormItem>
            <FormItem {...formItemLayout} label="内容">
              {getFieldDecorator('content', {
                rules: [
                  {
                    required: true,
                    message: "内容不能为空"
                  },
                  {
                    validator: this.validateEditorFrom
                  }
                ],
              })(
                <div style={{ width: 700 }} ref={node => this.node = node}></div>
              )}
            </FormItem>
            <FormItem {...formItemLayout} label="是否公开">
            <div>
              {getFieldDecorator('opened', {
                initialValue: '0',
              })(
                <Radio.Group>
                  <Radio value="0">公开</Radio>
                  <Radio value="1">私有</Radio>
                </Radio.Group>
              )}
            </div>
          </FormItem>
            <FormItem
              {...formItemLayout} label="是否置顶">
              <div>
                {getFieldDecorator('top', {
                  initialValue: '0',
                })(
                  <Radio.Group>
                    <Radio value="0">普通</Radio>
                    <Radio value="1">置顶</Radio>
                  </Radio.Group>
                )}
              </div>
            </FormItem>
            <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
              <Button type="primary" onClick={this.handleSubmit} >
                <FormattedMessage id="提交" />
              </Button>
              <Button style={{ marginLeft: 20 }}>
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
