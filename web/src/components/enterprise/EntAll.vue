<template>
  <div>
    <el-container>
      <el-header style="padding: 0px; display: flex; justify-content: space-between; align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过企业名搜索企业"
            clearable
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            @keyup.enter.native="searchEnt"
            prefix-icon="el-icon-search"
            v-model="keywords"
          >
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEnt">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="success" size="mini" icon="el-icon-download" @click="exportEnts">
            导出数据
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="addEnt">
            添加企业
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="ents"
            stripe
            border
            style="width: 100%"
            size="mini"
            v-loading="tableLoading"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection"  width="40" ></el-table-column>
            <el-table-column prop="name" label="企业名称" width="240" align="center" fixed></el-table-column>
            <el-table-column prop="code" label="统一信用代码" width="160" align="center"></el-table-column>
            <el-table-column prop="username" label="用户名" width="100" align="center"></el-table-column>
            <el-table-column prop="password" label="密码" width="100" align="center"></el-table-column>
            <el-table-column prop="rep" label="法人代表" width="100" align="center"></el-table-column>
            <el-table-column prop="phone" label="手机号" width="100" align="center"></el-table-column>
            <el-table-column prop="nature" label="企业性质" width="100" align="center"></el-table-column>
            <el-table-column prop="pos" label="市场地位" width="100" align="center"></el-table-column>
            <el-table-column prop="address" label="联系地址" width="280" align="left"></el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              align="center"
              width="150">
              <template slot-scope="scope">
                <el-button @click="showEditEntView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <!--<el-button style="padding: 3px 4px 3px 4px;margin: 2px" type="primary"
                           size="mini" @click="showDeatils">查看详细资料
                </el-button>-->
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEnt(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex; justify-content: space-between; margin: 2px;">
            <el-button type="danger" size="mini" v-if="ents.length>0"
                       :disabled="multipleSelection.length===0" @click="deleteManyEnts">
              批量删除
            </el-button>
            <div></div>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>
    <el-form size="small" :model="ent" ref="editEntForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="75%">
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="企业名称：" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="ent.name" size="mini" style="width: 400px"
                            placeholder="请输入企业名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="法人代表："  prop="rep">
                  <el-input prefix-icon="el-icon-edit" v-model="ent.rep" size="mini" style="width: 150px"
                            placeholder="请输入法人代表"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="企业性质：" prop="nature">
                  <el-select v-model="ent.nature" style="width: 150px" size="mini" placeholder="请选择">
                    <el-option
                      v-for="item in entNature"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="8">
              <div>
                <el-form-item label="信用代码：" prop="code">
                  <el-input prefix-icon="el-icon-edit" v-model="ent.code" size="mini" style="width: 200px"
                            placeholder="请输入统一信用代码"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="联系方式：" prop="phone">
                  <el-input prefix-icon="el-icon-phone" v-model="ent.phone" size="mini" style="width: 150px"
                            placeholder="请输入联系方式"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="基本地位：" prop="pos">
                  <el-select v-model="ent.pos" style="width: 150px" size="mini" placeholder="请选择">
                    <el-option
                      v-for="item in entPos"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="10">
              <div>
                <el-form-item label="通讯地址：" prop="address">
                  <el-input prefix-icon="el-icon-edit" v-model="ent.address" size="mini" style="width: 350px"
                            placeholder="请输入通讯地址"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="18">
              <div>
                <el-form-item label="主要经营范围：" prop="scope">
                  <el-input type="textarea" autosize v-model="ent.scope" size="mini"
                            style="width: 615px" placeholder="请输入主要经营范围">
                  </el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="18">
              <div>
                <el-form-item label="现有生产线情况：" prop="pls">
                  <el-input type="textarea" autosize v-model="ent.pls" size="mini"
                            style="width: 600px" placeholder="请输入现有生产线情况">
                  </el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="18">
              <div>
                <el-form-item label="主要工艺路线：" prop="routing">
                  <el-input type="textarea" autosize v-model="ent.routing" size="mini"
                            style="width: 615px" placeholder="请输入主要工艺路线">
                  </el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="updateEnt('editEntForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        ents: [],
        projects: [],
        keywords: '',
        multipleSelection: [],
        currentPage: 1,
        totalCount: -1,
        tableLoading: false,
        dialogVisible: false,
        dialogTitle:'',
        ent:{
          id:'',
          name:'',
          rep:'',
          code:'',
          phone:'',
          pos:'',
          nature:'',
          address:'',
          pls:'',
          scope:'',
          routing:''
        },
        entNature: [{id:1,name:'国有企业'},{id:2,name:'私营企业'},{id:3,name:'联营企业'},
          {id:4,name:'股份制企业'},{id:5,name:'有限合伙企业'},{id:6,name:'集体所有制企业'}],
        entPos: [{id:1,name:'市场领导者'},{id:2,name:'市场挑战者'},{id:3,name:'市场追随者'},{id:4,name:'市场补缺者'}],
      };
    },
    mounted: function () {
      this.loadEnts();
    },
    methods: {
      loadEnts(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/enterprise/all/ent?page=" + this.currentPage + "&size=10&keywords=" + this.keywords).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            var data = resp.data;
            _this.ents = data.ents;
            _this.totalCount = data.count;
          }
        })
      },
      searchEnt(){
        this.loadEnts();
      },
      deleteEnt(row){
        this.$confirm('此操作将永久删除[' + row.name + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
        });
      },
      deleteManyEnts(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = '';
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDelete(ids);
        }).catch(() => {
        });
      },
      doDelete(ids){
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/enterprise/all/ent/"+ids).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status === 200){
            let data = resp.data;
            _this.$message({type: data.status, message: data.msg});
            _this.loadEnts();
          }
        })
      },
      showEditEntView(row){
        this.dialogTitle="编辑企业";
        this.ent = row;
        this.dialogVisible = true;
      },
      updateEnt(formName){
        let _this = this;
        this.$refs[formName].validate((valid) =>{
          if(valid) {
            _this.tableLoading = true;
            this.putRequest("/enterprise/all/ent?id="+this.ent.id+"&name="+this.ent.name+"&rep="+this.ent.rep+"&code="+this.ent.code+"&phone="+this.ent.phone+"&pos="+this.ent.pos
              +"&nature="+this.ent.nature+"&address="+this.ent.address+"&pls="+this.ent.pls+"&scope="+this.ent.scope+"&routing="+this.ent.routing).then(resp=>{
              _this.tableLoading = false;
              if (resp && resp.status === 200){
                var data = resp.data;
                _this.$message({type: data.status, message: data.msg});
                _this.dialogVisible = false;
                _this.loadEnts();
              }
            })
          }
          else return false;
        });
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.loadEnts();
      },
      addEnt(){

      },
      showDeatils(){

      },
      exportEnts(){

      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEnts();
      },
      emptyEntData(){
        this.ent = {
          name:'',
          rep:'',
          code:'',
          phone:'',
          pos:'',
          nature:'',
          address:'',
          pls:'',
          scope:'',
          routing:''
        }
      }
    }
  };
</script>
