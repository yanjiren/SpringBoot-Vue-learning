package org.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.server.bean.Menu;
import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByEntId(int EntId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(int rid);
}
