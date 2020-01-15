<template>
  <div>
    <el-container>
      <el-header style="padding: 0px; display: flex; justify-content: space-between; align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过项目名称搜索"
            clearable
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            @keyup.enter.native="searchProj"
            prefix-icon="el-icon-search"
            v-model="keywords"
          >
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchProj">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="success" size="mini" icon="el-icon-download" @click="exportEnts">
            导出数据
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="projects"
            stripe
            border
            style="width: 100%"
            size="mini"
            v-loading="tableLoading"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection"  width="40" ></el-table-column>
            <el-table-column prop="name" label="项目名称" width="240" align="center" fixed></el-table-column>
            <el-table-column prop="entName" label="单位名称" width="240" align="center" ></el-table-column>
            <el-table-column prop="pCode" label="项目代码" width="160" align="center"></el-table-column>
            <el-table-column prop="principal" label="负责人" width="100" align="center"></el-table-column>
            <el-table-column prop="scale" label="投资规模（万）" width="130" align="center"></el-table-column>
            <el-table-column prop="industry" label="所属行业" width="200" align="center"></el-table-column>
            <el-table-column prop="location" label="所在市州" width="160" align="center"></el-table-column>
            <el-table-column prop="startDate" label="开始日期" width="100" align="center"></el-table-column>
            <el-table-column prop="endDate" label="结束日期" width="100" align="center"></el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              align="center"
              width="200">
              <template slot-scope="scope">
                <el-button type="primary" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="viewProgress">查看项目进度
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteProject(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex; justify-content: space-between; margin: 2px;">
            <el-button type="danger" size="mini" v-if="projects.length>0"
                       :disabled="multipleSelection.length===0" @click="deleteManyProjects">
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
        projects:[],
        tableLoading:"false",
        keywords: '',
        currentPage: 1,
        totalCount: -1,
        multipleSelection: []
      };
    },
    mounted: function () {
      this.loadProjects();
    },
    methods: {
      searchProj(){
        this.loadProjects();
      },
      loadProjects(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/project/all?page="+this.currentPage + "&size=10&keywords=" + this.keywords).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            var data = resp.data;
            _this.projects = data.projects;
            _this.totalCount = data.count;
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadProjects();
      },
      deleteProject(row){
        this.$confirm('此操作将永久删除[' + row.name + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
        });
      },
      deleteManyProjects(){
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
        this.deleteRequest("/project/all/"+ids).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status === 200){
            let data = resp.data;
            _this.$message({type: data.status, message: data.msg});
            _this.loadProjects();
          }
        })
      }
    },
    viewProgress(){
    }
  };
</script>

