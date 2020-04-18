<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="会员卡号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mbCardcode', validatorRules.mbCardcode]" placeholder="请输入会员卡号"></a-input>
        </a-form-item>
        <a-form-item label="会员姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mbCardname', validatorRules.mbCardname]" placeholder="请输入会员姓名"></a-input>
        </a-form-item>
        <a-form-item label="账单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'zdCode', validatorRules.zdCode]" placeholder="请输入账单号"></a-input>
        </a-form-item>
        <a-form-item label="消费金额(消费单子上的金额)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xfdzMoney', validatorRules.xfdzMoney]" placeholder="请输入消费金额(消费单子上的金额)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="实际消费" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'sjMoney', validatorRules.sjMoney]" placeholder="请输入实际消费" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="优惠率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'yhRate', validatorRules.yhRate]" placeholder="请输入优惠率" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消费时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择消费时间" v-decorator="[ 'xfTime', validatorRules.xfTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消费类型(已结，还款，反冲)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'xfType', validatorRules.xfType]" placeholder="请输入消费类型(已结，还款，反冲)"></a-input>
        </a-form-item>
        <a-form-item label="收银员" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'syOprator', validatorRules.syOprator]" placeholder="请输入收银员"></a-input>
        </a-form-item>
        <a-form-item label="消费余额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xfreMoney', validatorRules.xfreMoney]" placeholder="请输入消费余额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="分店编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fdCode', validatorRules.fdCode]" placeholder="请输入分店编码"></a-input>
        </a-form-item>
        <a-form-item label="消费分店" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'xffdName', validatorRules.xffdName]" placeholder="请输入消费分店"></a-input>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "XfrecordModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          mbCardcode: {rules: [
          ]},
          mbCardname: {rules: [
          ]},
          zdCode: {rules: [
          ]},
          xfdzMoney: {rules: [
          ]},
          sjMoney: {rules: [
          ]},
          yhRate: {rules: [
          ]},
          xfTime: {rules: [
          ]},
          xfType: {rules: [
          ]},
          syOprator: {rules: [
          ]},
          xfreMoney: {rules: [
          ]},
          fdCode: {rules: [
          ]},
          xffdName: {rules: [
          ]},
        },
        url: {
          add: "/xfrecord/xfrecord/add",
          edit: "/xfrecord/xfrecord/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'mbCardcode','mbCardname','zdCode','xfdzMoney','sjMoney','yhRate','xfTime','xfType','syOprator','xfreMoney','fdCode','xffdName'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'mbCardcode','mbCardname','zdCode','xfdzMoney','sjMoney','yhRate','xfTime','xfType','syOprator','xfreMoney','fdCode','xffdName'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>