<!-- from 7 5 2 7 1 8 9 2 0 -->
<template>
  <a-row :gutter="24">
    <a-col :md="24">
      <a-card :style="cardStyle" :bordered="false">
        <!-- 查询区域 -->
        <div class="table-page-search-wrapper">
          <a-form layout="inline" @keyup.enter.native="searchQuery">
            <a-row :gutter="24">
              <a-col :md="4" :sm="24">
                <a-form-item label="施工状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
                  <a-select placeholder="请选择施工状态" allow-clear v-model="queryParam.status">
                    <a-select-option value="0">未施工</a-select-option>
                    <a-select-option value="1">施工中</a-select-option>
                    <a-select-option value="2">已施工</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="4" :sm="24">
                <a-form-item label="设计师" :labelCol="labelCol" :wrapperCol="wrapperCol">
                  <a-select placeholder="请选择设计师" showSearch allow-clear optionFilterProp="children" v-model="queryParam.designer">
                    <a-select-option v-for="(item,index) in designerList" :key="index" :value="item">
                      {{ item }}
                    </a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="4" :sm="24">
                <a-form-item label="客户" :labelCol="labelCol" :wrapperCol="wrapperCol">
                  <a-select placeholder="请选择客户" v-model="queryParam.organId"
                            :dropdownMatchSelectWidth="false" showSearch allow-clear optionFilterProp="children">
                    <a-select-option v-for="(item,index) in cusList" :key="index" :value="item.id">
                      {{ item.supplier }}
                    </a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="5" :sm="24">
                <a-form-item label="单据日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-range-picker
                      style="width:100%"
                      v-model="queryParam.createTimeRange"
                      format="YYYY-MM-DD"
                      :placeholder="['开始时间', '结束时间']"
                      @change="onDateChange"
                      @ok="onDateOk"
                    />
                  </a-form-item>
              </a-col>
              <a-col :md="5" :sm="24">
                <span class="table-page-search-submitButtons">
                  <a-button type="primary" @click="searchQuery">查询</a-button>
                  <a-button style="margin-left: 8px" v-print="'#reportPrint'" icon="printer">打印</a-button>
                  <a-button style="margin-left: 8px" @click="exportExcel" icon="download">导出</a-button>
                </span>
              </a-col>
            </a-row>
          </a-form>
        </div>
        <!-- table区域-begin -->
        <section ref="print" id="reportPrint">
          <a-table
            bordered
            ref="table"
            size="middle"
            rowKey="id"
            :columns="columns"
            :dataSource="dataSource"
            :components="handleDrag(columns)"
            :pagination="false"
            :scroll="scroll"
            :loading="loading"
            @change="handleTableChange">
            <span slot="customTitle">
              <a-popover trigger="click" placement="right">
                <template slot="content">
                  <a-checkbox-group @change="onColChange" v-model="settingDataIndex" :defaultValue="settingDataIndex">
                    <a-row style="width: 600px">
                      <template v-for="(item,index) in defColumns">
                        <template>
                          <a-col :span="6">
                            <a-checkbox :value="item.dataIndex" v-if="item.dataIndex==='rowIndex'" disabled></a-checkbox>
                            <a-checkbox :value="item.dataIndex" v-if="item.dataIndex!=='rowIndex'">
                              <j-ellipsis :value="item.title" :length="10"></j-ellipsis>
                            </a-checkbox>
                          </a-col>
                        </template>
                      </template>
                    </a-row>
                    <a-row style="padding-top: 10px;">
                      <a-col>
                        恢复默认列配置：<a-button @click="handleRestDefault" type="link" size="small">恢复默认</a-button>
                      </a-col>
                    </a-row>
                  </a-checkbox-group>
                </template>
                <a-icon type="setting" />
              </a-popover>
            </span>
            <span slot="action" slot-scope="text, record">
              <a @click="showAccountInOutList(record)">{{record.id?'流水':''}}</a>
            </span>
          </a-table>
          <a-row :gutter="24" style="margin-top: 8px;text-align:right;">
            <a-col :md="24" :sm="24">
              <a-pagination @change="paginationChange" @showSizeChange="paginationShowSizeChange"
                size="small"
                show-size-changer
                :showQuickJumper="true"
                :current="ipagination.current"
                :page-size="ipagination.pageSize"
                :page-size-options="ipagination.pageSizeOptions"
                :total="ipagination.total"
                :show-total="(total, range) => `共 ${total-Math.ceil(total/ipagination.pageSize)} 条`">
                <template slot="buildOptionText" slot-scope="props">
                  <span>{{ props.value-1 }}条/页</span>
                </template>
              </a-pagination>
            </a-col>
          </a-row>
        </section>
        <!-- table区域-end -->
      </a-card>
    </a-col>
  </a-row>
</template>
<script>
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import JEllipsis from '@/components/jeecg/JEllipsis'
  import {getAction} from '@/api/manage'
  import {getMaterialNames,findBySelectOrgan} from '@/api/api'
  export default {
    name: "OrderStatusReport",
    mixins:[JeecgListMixin],
    components: {
      JEllipsis
    },
    data () {
      return {
        labelCol: {
          span: 5
        },
        wrapperCol: {
          span: 18,
          offset: 1
        },
        // 查询条件
        queryParam: {
          name:'',
          serialNo:''
        },
        ipagination:{
          pageSize: 11,
          pageSizeOptions: ['11', '21', '31', '101', '201']
        },
        allMonthAmount: '',
        allCurrentAmount: '',
        tabKey: "1",
        pageName: 'OrderStatusReport',
        // 默认索引
        defDataIndex:['rowIndex','organName','designer','paied','unPaied','totalPrice','status'],
        // 默认列
        defColumns: [
          {
            dataIndex: 'rowIndex', width:60, align:"center", slots: { title: 'customTitle' },
            customRender:function (t,r,index) {
              return (t !== '合计') ? (parseInt(index) + 1) : t
            }
          },
          { title: '客户', dataIndex: 'organName', width: 150},
          { title: '设计师', dataIndex: 'designer', width: 150},
          { title: '签约金额', dataIndex: 'totalPrice', width: 150},
          { title: '已收款项', dataIndex: 'paied', width: 100},
          { title: '未收款项', dataIndex: 'unPaied', width: 100},
          { title: '施工状态', dataIndex: 'status', width: 100}
        ],
        url: {
          list: "/order/list"
        },
        cusList: [],
        designerList: []
      }
    },
    created () {
      this.initColumnsSetting()
      this.initDesigner()
      this.initOrgan()
    },
    methods: {
      initDesigner() {
        getMaterialNames({}).then((res)=>{
          if(res && res.code === 200) {
            this.designerList = res.data;
          }
        });
      },
      initOrgan() {
        findBySelectOrgan({}).then((res)=>{
          if(res) {
            this.cusList = res;
          }
        });
      },
      searchQuery() {
        this.loadData(1);
      },
      exportExcel() {
        let list = []
        let head = '名称,编号,期初金额,本月发生额,当前余额'
        for (let i = 0; i < this.dataSource.length; i++) {
          let item = []
          let ds = this.dataSource[i]
          item.push(ds.name, ds.serialNo, ds.initialAmount, ds.thisMonthAmount, ds.currentAmount)
          list.push(item)
        }
        let tip = '账户统计查询'
        this.handleExportXlsPost('账户统计', '账户统计', head, tip, list)
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>