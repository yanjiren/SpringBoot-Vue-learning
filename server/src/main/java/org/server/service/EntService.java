package org.server.service;

import org.server.bean.Ent;
import org.server.bean.RespBean;
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

    public List<Ent> getEntByPage(int page, int size, String keywords) {
        int start = (page - 1) * size;
        return entMapper.getEntByPage(start, size, keywords);
    }

    public Ent getEntBasicByUsername(String username){
        return entMapper.getEntBasicByUsername(username);
    }

    public int getCountByKeywords(String keywords) {
        return entMapper.getCountByKeywords(keywords);
    }

    public int isExistUsername(String username) {return entMapper.isExistUsername(username);}

    public int addRolesForEnt(int eid){
        return entMapper.addRolesForEnt(eid);
    }

    public int entSignUp(String username,String password){
        if (entMapper.loadUserByUsername(username) != null) {
            return -1;
        }
        int flag1,flag2=0,eid;
        flag1 = entMapper.entSignUp(username,password);
        if(flag1==1){
            eid = entMapper.getEntBasicByUsername(username).getId();
            flag2 = addRolesForEnt(eid);
        }
        return (flag1==1&&flag2==1)?1:0;
    }

    public int updateEnt(Ent ent){
        return entMapper.updateEnt(ent);
    }

    public boolean deleteEntById(String ids){
        String[] Eids = ids.split(",");
        List<String> pids = entMapper.getPidByEid(Eids);
        String[] Pids = new String[pids.size()];
        for(int i=0;i<pids.size();i++){
            Pids[i]=pids.get(i);
        }
        return entMapper.deleteStageByPid(Pids) == Pids.length&&entMapper.deleteProjByEid(Eids)==Eids.length
                && entMapper.deleteProbByEid(Eids) == Eids.length&&entMapper.deleteOpByEid(Eids) == Eids.length
                && entMapper.deleteERoleByEid(Eids) == Eids.length&&entMapper.deleteEntById(Eids) == Eids.length;
    }
}
