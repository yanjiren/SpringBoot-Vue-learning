package org.server.service;

import org.server.bean.Ent;
import org.server.bean.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.server.mapper.EntMapper;

import java.util.List;

@Service
@Transactional
public class EntService implements UserDetailsService {
    @Autowired
    EntMapper entMapper;

    //通过用户名获得企业的所有信息
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Ent ent = entMapper.loadUserByUsername(s);
        if (ent == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return ent;
    }

    public List<Ent> getEntByPage(Integer page, Integer size, String keywords) {
        int start = (page - 1) * size;
        return entMapper.getEntByPage(start, size, keywords);
    }

    public Ent getEntBasicById(Integer id){
        return entMapper.getEntBasicById(id);
    }

    public int getCountByKeywords(String keywords) {
        return entMapper.getCountByKeywords(keywords);
    }
}
