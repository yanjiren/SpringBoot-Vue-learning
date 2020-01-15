
<template>
  <el-form :model="ent" status-icon :rules="rules" ref="ent"class="signup-container" >
    <h3 class="reg_title">注册</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="ent.username" auto-complete="off" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="ent.password" autocomplete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input type="password" v-model="ent.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ent')">提交</el-button>
        <el-button @click="resetForm('ent')">重置</el-button>
      </el-form-item>
  </el-form>

</template>

<script>
  import {isExistUsername,entSignup} from "../utils/signup";

  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ent.checkPass !== '') {
            this.$refs.ent.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ent.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      var validateName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入用户名'));
        } else {
          isExistUsername(value).then(resp =>{
            if(resp) return callback(new Error('用户名已存在'));
            else callback();
          });
        }
      };

      return {
        ent: {
          username:'',
          password: '',
          checkPass: ''
        },
        rules: {
          username: [
            { validator: validateName, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.entSignup(this.ent.username,this.ent.password).then(resp=>{
              if (resp && resp.status === 200){
                let data = resp.data;
                _this.$message({type: data.status, message: data.msg});
              }
            })
            this.$router.go(-1);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style >
  .reg_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .signup-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>
