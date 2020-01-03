package org.server.service;

import org.server.bean.EntUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.server.bean.Menu;
import org.server.mapper.MenuMapper;


import java.util.List;

@Service
@Transactional
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    public List<Menu> getMenusByEntId() {
        return menuMapper.getMenusByEntId(EntUtils.getCurrentEnt().getId());
    }

    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }

    public List<Long> getMenusByRid(int rid) {
        return menuMapper.getMenusByRid(rid);
    }

}
