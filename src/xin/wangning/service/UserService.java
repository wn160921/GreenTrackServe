package xin.wangning.service;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import xin.wangning.dao.UserDao;
import xin.wangning.domain.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(String id){
        User user = userDao.findById(id);
        if(user==null){
            return null;
        }else {
            return user;
        }
    }

    public User findByUserName(String username){
        User user = userDao.findByUsername(username);
        return user;
    }

    public void updateRewardPoints(String id,int up){
        userDao.updateRewardPoints(up,id);
    }

    public String add(User user){
        User checkUser = userDao.findByUsername(user.getUsername());
        if(checkUser == null) return "用户名重复";
        checkUser = userDao.findByMailAddress(user.getMailAddress());
        if(checkUser==null) return "邮箱重复";
        userDao.add(user);
        return JSON.toJSONString(user);
    }
}
