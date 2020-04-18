<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">

      <a-button type="primary" icon="download" @click="handleExportXls('消费记录')">导出</a-button>

    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"

        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>



      </a-table>
    </div>

    <xfrecord-modal ref="modalForm" @ok="modalFormOk"></xfrecord-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import XfrecordModal from './modules/XfrecordModal'

  export default {
    name: "XfrecordList",
    mixins:[JeecgListMixin],
    components: {
      XfrecordModal
    },
    data () {
      return {
        description: '消费记录管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'会员卡号',
            align:"center",
            dataIndex: 'mbCardcode'
          },
          {
            title:'会员姓名',
            align:"center",
            dataIndex: 'mbCardname'
          },
          {
            title:'账单号',
            align:"center",
            dataIndex: 'zdCode'
          },
          {
            title:'消费金额',
            align:"center",
            dataIndex: 'xfdzMoney'
          },
          {
            title:'实际消费',
            align:"center",
            dataIndex: 'sjMoney'
          },
          {
            title:'优惠率',
            align:"center",
            dataIndex: 'yhRate'
          },
          {
            title:'消费时间',
            align:"center",
            dataIndex: 'xfTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'消费类型',
            align:"center",
            dataIndex: 'xfType'
          },
          {
            title:'收银员',
            align:"center",
            dataIndex: 'syOprator'
          },
          {
            title:'消费余额',
            align:"center",
            dataIndex: 'xfreMoney'
          },
          {
            title:'分店编码',
            align:"center",
            dataIndex: 'fdCode'
          },
          {
            title:'消费分店',
            align:"center",
            dataIndex: 'xffdName'
          }
        ],
        url: {
          list: "/xfrecord/xfrecord/list",
          delete: "/xfrecord/xfrecord/delete",
          deleteBatch: "/xfrecord/xfrecord/deleteBatch",
          exportXlsUrl: "/xfrecord/xfrecord/exportXls",
          importExcelUrl: "xfrecord/xfrecord/importExcel",
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>