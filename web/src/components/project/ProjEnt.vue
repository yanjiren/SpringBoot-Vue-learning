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
            @keyup.enter.native="searchEntProj"
            prefix-icon="el-icon-search"
            v-model="keywords"
          >
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEntProj">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="success" size="mini" icon="el-icon-download" @click="exportProjects">
            导出数据
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="showAddProjectView">
            新增项目
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
            v-loading="tableLoading">
            <el-table-column prop="name" label="项目名称" width="240" align="center" fixed></el-table-column>
            <el-table-column prop="pCode" label="项目代码" width="160" align="center"></el-table-column>
            <el-table-column prop="principal" label="负责人" width="100" align="center"></el-table-column>
            <el-table-column prop="scale" label="投资规模" width="100" align="center"></el-table-column>
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
                           @click="updateProgress">汇报进度
                </el-button>
                <el-button type="success" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="viewProgress">查看进度
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <el-form :model="project"  ref="addProjectForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="projectDialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="projectDialogVisible"
          width="75%">
          <el-row>
            <el-col :span="12">
              <div>
                <el-form-item label="项目名称：" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="project.name" size="mini" style="width: 400px;"
                            clearable placeholder="请输入项目名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <div>
                <el-form-item label="项目代码：" prop="pCode">
                  <el-input prefix-icon="el-icon-edit" v-model="project.pCode" size="mini" style="width: 200px"
                            clearable placeholder="请输入项目代码"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div >
                <el-form-item label="项目时间:" prop="dateRange">
                  <el-date-picker
                    clearable
                    unlink-panels
                    size="mini"
                    v-model="dateRange"
                    value-format="yyyy-MM-dd"
                    @change="setProjectDate"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                  </el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <div>
                <el-form-item label="负责人：" prop="principal" style="padding-left: 15px">
                  <el-input prefix-icon="el-icon-edit" v-model="project.principal" size="mini" style="width: 200px"
                            clearable placeholder="请输入负责人"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="所属省:" prop="province">
                  <el-select v-model="province" size="mini" placeholder="请选择" @change="provinceChanged" style="width: 160px">
                    <el-option
                      v-for="item in provinces"
                      :key="item.code"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="市:" prop="city">
                  <el-select v-model="city" :loading="loadingCity" size="mini" placeholder="请选择" style="width: 200px;"
                              @change="setLocation">
                    <el-option
                      v-for="item in cities"
                      :key="item.code"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="2">
              <div>
                <el-button @click="resetProvinceCity" type="info" size="mini" style="margin-left:20px; margin-top: 7px;">重置</el-button>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <div>
                <el-form-item label="投资规模：" prop="scale">
                  <el-input prefix-icon="el-icon-edit" v-model="project.scale" size="mini" style="width: 200px"
                            clearable placeholder="请输入投资规模（万）"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-form-item label="所属行业:" prop="industry">
                  <el-input prefix-icon="el-icon-edit" v-model="project.industry" size="mini" style="width: 470px"
                            clearable placeholder="请输入所属行业" type="textarea" autosize></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidtProject">取 消</el-button>
            <el-button size="mini" type="primary" @click="addProject('addProjectForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>

<script>
  import provinceCity from '../../../static/data/provinceCity.js';
  export default {
    data() {
      return {
        projects:[],
        tableLoading: false,
        keywords: '',
        project:{
          eid:'',
          name:'',
          pCode:'',
          principal: '',
          scale:'',
          industry: '',
          location:'',
          startDate:'',
          endDate:'',
        },
        projectDialogVisible: false,
        reportDialogVisible: false,
        projectDialogTitle:'',
        reportDialogTitle:'',
        dateRange:'',
        province: '',
        city: '',
        provinces:'',
        cities: [],
        loadingCity:false
      };
    },
    created(){
      this.provinces = provinceCity[0].provinces;
    },
    mounted: function () {
      this.loadEntProjects();

    },
    methods: {
      searchEntProj(){
        this.loadEntProjects();
      },
      loadEntProjects(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/project/entpm?username="+this.$store.state.user.username+"&keywords="+this.keywords).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            _this.projects = resp.data;
          }
        })
      },
      showAddProjectView(){
        this.getProjectEid();
        this.projectDialogTitle= "新增项目";
        this.projectDialogVisible = true;
      },
      getProjectEid(){
        let _this = this;
        this.getRequest("/enterprise/basic/ent?username="+this.$store.state.user.username).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            _this.project.eid = resp.data.id;
          }
        });
      },
      addProject(formName){
        let _this = this;
        this.$refs[formName].validate((valid) =>{
          if(valid) {
            this.postRequest("/project/entpm",this.project).then(resp=>{
              if (resp && resp.status === 200){
                var data = resp.data;
                _this.$message({type: data.status, message: data.msg});
                _this.projectDialogVisible = false;
                _this.emptyProject();
                _this.loadEntProjects();
              }
            })
          }
        })
      },
      updateProgress(){
      },
      viewProgress(){
      },
      setProjectDate(){
        this.project.startDate=this.dateRange[0];
        this.project.endDate=this.dateRange[1];
      },
      provinceChanged(value){
        this.loadingCity = true;
        for(let item of this.provinces){
          if(item.name === value){
            this.loadingCity=false;
            this.cities = item.cities;
            this.city = ''
            break;
          }
        }
      },
      resetProvinceCity(){
        this.cities=[];
        this.city='';
        this.province='';
      },
      setLocation(){
        if(this.province===this.city) this.project.location=this.province;
        else this.project.location=this.province+""+this.city;
      },
      cancelEidtProject(){
        this.projectDialogVisible = false;
        this.project='';
        this.dateRange='';
        this.resetProvinceCity();
      },
      emptyProject(){
        this.project={
          eid:'',
          name:'',
          pCode:'',
          principal: '',
          scale:'',
          industry: '',
          location:'',
          startDate:'',
          endDate:'',
          entName:''
        }
      }
    }
  };
</script>
