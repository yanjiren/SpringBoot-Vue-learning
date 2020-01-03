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
            <el-table-column type="selection" align="center" width="35" ></el-table-column>
            <el-table-column prop="name" label="企业名称" width="240" align="center" fixed></el-table-column>
            <el-table-column prop="code" label="统一信用代码" width="150" align="center"></el-table-column>
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
              width="200">
              <template slot-scope="scope">
                <el-button @click="showEditEntView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button style="padding: 3px 4px 3px 4px;margin: 2px" type="primary"
                           size="mini" @click="showDeatils">查看详细资料
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEnt(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex; justify-content: space-between; margin: 2px;">
            <el-button type="danger" size="mini" v-if="ents.length>0"
                       :disabled="multipleSelection.length==0" @click="deleteManyEnts">
              批量删除
            </el-button>
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
        tableLoading:"false",
        ent: {
          name:'',
          code:'',
          username:'',
          password:'',
          rep:'',
          phone:'',
          nature:'',
          pos:'',
          address:''
        },
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
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.ents = data.ents;
            _this.totalCount = data.count;
          }
        })
      },
      searchEnt(){

      },
      exportEnts(){

      },
      addEnt(){

      },
      deleteManyEnts(){

      },
      showEditEntView(row){

      },
      deleteEnt(row){

      },
      showDeatils(){

      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEnts();
      }
    }
  };
</script>
