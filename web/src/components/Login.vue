<template>
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <!--<el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>-->
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
    </el-form-item>
    <p style="text-align: right">
       <router-link class="login_reg" to="#">忘记密码</router-link>
       <router-link class="login_reg" to="/signup">企业注册</router-link>
    </p>
  </el-form>
</template>

<script>
  export default{
    data(){
      var validatePass = (rule, value, callback) => {
        if (this.loginForm.password === '') {
          callback(new Error('请输入密码'));
        } else callback();
      };
      var validateName = (rule, value, callback) => {
        if (this.loginForm.username === '') {
          callback(new Error('请输入用户名'));
         } else callback();
      };

      return {
        rules: {
          account: [{ validator: validateName, trigger: 'blur' }],
          checkPass: [{ validator: validatePass, trigger: 'blur' }]
        },
        checked: true,
        loginForm: {
          username: '',
          password: ''
        },
        loading: false
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        this.loading = true;
        this.postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp=> {
          _this.loading = false;
          if (resp && resp.status === 200) {
            var data = resp.data;
            _this.$store.commit('login', data.msg);
            var path = _this.$route.query.redirect;
            _this.$router.replace({path: path === '/' || path === undefined ? '/home' : path});
          }
        });
      }
    }
  }
</script>

<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }

  .login_reg {
      text-decoration: none;
      color:#999999;
      font-size: 12px;
      margin-left: 8px;
  }
</style>
