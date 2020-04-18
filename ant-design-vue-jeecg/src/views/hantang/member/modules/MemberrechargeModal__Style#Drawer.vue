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
        <a-form-item label="充值分店" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'czfd', validatorRules.czfd]" placeholder="请输入充值分店"></a-input>
        </a-form-item>
        <a-form-item label="充值分店编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'czfdCode', validatorRules.czfdCode]" placeholder="请输入充值分店编码"></a-input>
        </a-form-item>
        <a-form-item label="充值金额（本金+赠额）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'ccMoney', validatorRules.ccMoney]" placeholder="请输入充值金额（本金+赠额）" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="充值本金" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'syMoney', validatorRules.syMoney]" placeholder="请输入充值本金" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="赠额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'caMoney', validatorRules.caMoney]" placeholder="请输入赠额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="余额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'kqMoney', validatorRules.kqMoney]" placeholder="请输入余额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="付款类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fkType', validatorRules.fkType]" placeholder="请输入付款类型"></a-input>
        </a-form-item>
        <a-form-item label="收银员" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'syMen', validatorRules.syMen]" placeholder="请输入收银员"></a-input>
        </a-form-item>
        <a-form-item label="收银时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择收银时间" v-decorator="[ 'syTime', validatorRules.syTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="操作类型(发卡、充值）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'opType', validatorRules.opType]" placeholder="请输入操作类型(发卡、充值）"></a-input>
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
    name: "MemberrechargeModal",
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
          czfd: {rules: [
          ]},
          czfdCode: {rules: [
          ]},
          ccMoney: {rules: [
          ]},
          syMoney: {rules: [
          ]},
          caMoney: {rules: [
          ]},
          kqMoney: {rules: [
          ]},
          fkType: {rules: [
          ]},
          syMen: {rules: [
          ]},
          syTime: {rules: [
          ]},
          opType: {rules: [
          ]},
        },
        url: {
          add: "/member/memberrecharge/add",
          edit: "/member/memberrecharge/edit",
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
          this.form.setFieldsValue(pick(this.model,'mbCardcode','mbCardname','czfd','czfdCode','ccMoney','syMoney','caMoney','kqMoney','fkType','syMen','syTime','opType'))
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
        this.form.setFieldsValue(pick(row,'mbCardcode','mbCardname','czfd','czfdCode','ccMoney','syMoney','caMoney','kqMoney','fkType','syMen','syTime','opType'))
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