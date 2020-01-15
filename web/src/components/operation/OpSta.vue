<template>
  <div>
    <el-container>
      <el-header style="padding: 0px; display: flex; justify-content: space-between; align-items: center">
        <div >
          <el-date-picker
            unlink-panels
            size="mini"
            v-model="dateRange"
            value-format="yyyy-MM-dd"
            @change="loadDateRangeOp"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
          <el-button size="mini" type="success" @click="loadOperations" icon="el-icon-refresh">
          </el-button>
        </div>
        <div>
          <el-button type="success" size="mini" icon="el-icon-download" @click="exportOpSta">
            导出数据
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="showUpdateView">
            更新信息
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="operations"
            stripe
            border
            style="width: 100%"
            size="mini"
            show-summary
            v-loading="tableLoading">
            <el-table-column prop="reportDate" label="日期" width="200" align="center"></el-table-column>
            <el-table-column prop="productOutput" label="产量"  align="center"></el-table-column>
            <el-table-column prop="outputValue" label="产值"  align="center"></el-table-column>
            <el-table-column prop="tax" label="税收"  align="center"></el-table-column>
            <el-table-column prop="energyUsed" label="工业用电量"  align="center"></el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <el-form size="mini" :model="operation" ref="updateOpForm" style="margin: 0;padding: 0;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="30%">
          <el-row>
              <div class="block" style="padding-left: 50px">
                <el-form-item label="日期：">
                  <el-date-picker
                    v-model="dateMonth"
                    type="month"
                    size="mini"
                    style="width: 250px"
                    @change="setOpDate"
                    value-format="yyyy-MM"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
              </div>
          </el-row>
          <el-row>
            <div style="padding-left: 50px">
              <el-form-item label="产量：" prop="outputValue">
                <el-input prefix-icon="el-icon-edit" v-model="operation.productOutput" size="mini" style="width: 250px"
                          placeholder="请输入产量"></el-input>
              </el-form-item>
            </div>
          </el-row>
          <el-row>
            <div style="padding-left: 50px">
              <el-form-item label="产值：" prop="outputValue">
                <el-input prefix-icon="el-icon-edit" v-model="operation.outputValue" size="mini" style="width: 250px"
                          placeholder="请输入产值"></el-input>
              </el-form-item>
            </div>
          </el-row>
          <el-row>
            <div style="padding-left: 50px">
              <el-form-item label="税收：" prop="tax">
                <el-input prefix-icon="el-icon-edit" v-model="operation.tax" size="mini" style="width: 250px"
                          placeholder="请输入税收"></el-input>
              </el-form-item>
            </div>
          </el-row>
          <el-row>
            <div style="padding-left: 36px">
              <el-form-item label="用电量：" prop="energyUsed">
                <el-input prefix-icon="el-icon-edit" v-model="operation.energyUsed" size="mini" style="width: 250px"
                          placeholder="请输入用电量"></el-input>
              </el-form-item>
            </div>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addOpSta('updateOpForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        dateRange:'',
        operations:[],
        tableLoading: false,
        dialogVisible:false,
        dialogTitle:"",
        startDate:'1949-01-01',
        endDate:'2049-01-01',
        dateMonth:'',
        operation:{
          id:'',
          eid:'',
          productOutput:'',
          outputValue:'',
          tax:'',
          energyUsed:'',
          reportDate:''
        }
      };
    },
    mounted: function (){
      this.loadOperations();
    },
    methods:{
      loadOperations(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/operation/statistics/ent?username="+this.$store.state.user.username
          +"&startDate="+this.startDate+"&endDate="+this.endDate).then(resp=>{
          this.tableLoading = false;
          if (resp && resp.status === 200){
            _this.operations = resp.data;
          }
        })
      },
      loadDateRangeOp(){
        this.startDate=this.dateRange[0];
        this.endDate=this.dateRange[1];
        this.loadOperations();
      },
      showUpdateView(){
        this.getEid();
        this.dialogTitle = "更新信息";
        this.dialogVisible = true;
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.emptyOp();
      },
      getEid(){
        let _this = this;
        this.getRequest("/enterprise/basic/ent?username="+this.$store.state.user.username).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            _this.operation.eid = resp.data.id;
          }
        });
      },
      setOpDate(){
        this.operation.reportDate=this.dateMonth+"-02";
        console.log(this.operation.reportDate);
      },
      addOpSta(formName){
        this.$confirm('信息不可修改, 是否确定数据正确?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let _this = this;
          this.$refs[formName].validate((valid)=>{
            if(valid){
              this.postRequest("/operation/statistics/ent",this.operation).then(resp=>{
                if (resp && resp.status === 200) {
                  var data = resp.data;
                  _this.$message({type: data.status, message: data.msg});
                  _this.dialogVisible = false;
                  _this.emptyOp();
                  _this.loadOperations();
                }
              })
            }
            else return false;
          });
        }).catch(() => {
        });
      },
      emptyOp(){
        this.operation={
          productOutput:'',
          outputValue:'',
          tax:'',
          energyUsed:'',
          reportDate:''
        }
        this.dateMonth='';
      },
      exportOpSta(){

      }
    }
  }
</script>
