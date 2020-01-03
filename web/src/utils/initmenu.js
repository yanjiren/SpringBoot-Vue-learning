import {getRequest} from './api'

export const initMenu = (router, store)=> {

  //判断store中的数据是否存在，若存在则不必初始化菜单
  if (store.state.routes.length > 0) {
    return;
  }
  //获得json，转化为router需要的格式
  getRequest("/config/sysmenu").then(resp=> {
    if (resp && resp.status == 200) {
      var fmtRoutes = formatRoutes(resp.data);
      router.addRoutes(fmtRoutes);
      store.commit('initMenu', fmtRoutes);
      store.dispatch('connect');
    }
  })
}
export const formatRoutes = (routes)=> {
  let fmRoutes = [];
  routes.forEach(router=> {
    let {
      path,
      component,
      name,
      meta,
      iconCls,
      children
    } = router;
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }
    let fmRouter = {
      path: path,
      component(resolve){
        if (component.startsWith("Home")) {
          require(['../components/' + component + '.vue'], resolve)
        }else if (component.startsWith("Ent")) {
          require(['../components/enterprise/' + component + '.vue'], resolve)
        } else if (component.startsWith("Proj")) {
          require(['../components/project/' + component + '.vue'], resolve)
        } else if (component.startsWith("Prob")) {
          require(['../components/problem/' + component + '.vue'], resolve)
        } else if (component.startsWith("Op")) {
          require(['../components/operation/' + component + '.vue'], resolve)
        } else if (component.startsWith("Sys")) {
          require(['../components/system/' + component + '.vue'], resolve)
        }
      },
      name: name,
      iconCls: iconCls,
      meta: meta,
      children: children
    };
    fmRoutes.push(fmRouter);
  })
  return fmRoutes;
}
