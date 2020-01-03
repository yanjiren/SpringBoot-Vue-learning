// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
import 'font-awesome/css/font-awesome.min.css'
import {initMenu} from "./utils/initmenu";

Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

/* eslint-disable no-new */


router.beforeEach((to, from, next)=> {

    //login页面不需要菜单数据，跳过
    if (to.name == 'Login') {
      next();
      return;
    }
    var name = store.state.user.name;
    if (name == '未登录') {
      //若要跳转到某页面，需判断该页面是否要求登录才能访问
      if (to.meta.requireAuth || to.name == null) {
        next({path: '/', query: {redirect: to.path}})
      } else {
        next();
      }
    } else {
      initMenu(router,store);
      next();
    }
  }
)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
