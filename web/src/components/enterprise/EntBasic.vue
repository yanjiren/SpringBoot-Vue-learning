<template>
  <div>
    <el-container>
      <el-header style="padding: 0px; display: flex; justify-content: flex-start; align-items: center">
        <div>
          <div>
            <el-button type="primary" size="mini" icon="el-icon-refresh" @click="showUpdateEntView">
              更新修改
            </el-button>
          </div>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div >
          <el-row :gutter="10" >
            <el-col :span="3"><div class="prop" >企业名称：</div></el-col>
            <el-col :span="8"><div class="value" >{{ent.name}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop">法人代表：</div></el-col>
            <el-col :span="4"><div class="value">{{ent.rep}}</div></el-col>
            <el-col :span="3"><div class="prop">统一信用代码：</div></el-col>
            <el-col :span="6"><div class="value">{{ent.code}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop">联系方式：</div></el-col>
            <el-col :span="4"><div class="value">{{ent.phone}}</div></el-col>
            <el-col :span="3"><div class="prop">基本地位：</div></el-col>
            <el-col :span="6"><div class="value">{{ent.pos}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop">企业性质：</div></el-col>
            <el-col :span="4"><div class="value">{{ent.nature}}</div></el-col>
            <el-col :span="3"><div class="prop">通讯地址：</div></el-col>
            <el-col :span="8"><div class="value">{{ent.address}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop" >主要经营范围：</div></el-col>
            <el-col :span="12"><div class="value"  style="border:1px solid">{{ent.scope}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop">现有生产线情况：</div></el-col>
            <el-col :span="12"><div class="value" style="border:1px solid">{{ent.pls}}</div></el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="3"><div class="prop">主要工艺路线：</div></el-col>
            <el-col :span="12"><div class="value" style="border:1px solid">{{ent.routing}}</div></el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
    <el-form size="small" :model="ent" ref="updateEntForm" style="margin: 0;padding: 0;">
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
            <el-button size="mini" type="primary" @click="updateEnt('updateEntForm')">确 定</el-button>
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
        tableLoading: false,
        dialogVisible: false,
        dialogTitle:''
      };
    },
    mounted: function () {
      this.emptyEntData();
      this.loadEnt();
    },
    methods:{
      loadEnt(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/enterprise/basic/ent?username="+this.$store.state.user.username).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status === 200) {
            _this.ent = resp.data;
          }
        })
      },
      showUpdateEntView(){
        this.dialogTitle = "更新修改";
        this.dialogVisible = true;
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.emptyEntData();
        this.loadEnt();
      },
      updateEnt(formName){
        let _this = this;
        this.$refs[formName].validate((valid) =>{
          if(valid) {
            _this.tableLoading = true;
            this.putRequest("/enterprise/basic/ent?id="+this.ent.id+"&name="+this.ent.name+"&rep="+this.ent.rep+"&code="+this.ent.code+"&phone="+this.ent.phone+"&pos="+this.ent.pos
              +"&nature="+this.ent.nature+"&address="+this.ent.address+"&pls="+this.ent.pls+"&scope="+this.ent.scope+"&routing="+this.ent.routing).then(resp=>{
              _this.tableLoading = false;
              if (resp && resp.status === 200){
                var data = resp.data;
                _this.$message({type: data.status, message: data.msg});
                _this.dialogVisible = false;
                _this.emptyEntData();
                _this.loadEnt();
              }
            })
          }
          else return false;
        });
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
<style>
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .prop {
     border-radius: 4px;
     min-height: 36px;
     line-height: 36px;
     text-align: right;
     padding-right:5px;
   }
  .value {
    border-radius: 4px;
    min-height: 36px;
    line-height: 36px;
    text-align: justify;
    padding-left:10px;
    padding-right:10px;
  }
</style>
