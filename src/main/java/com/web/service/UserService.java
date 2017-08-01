package com.web.service;

import com.web.dao.UserDAO;
import com.web.entity.BasicVo;
import com.web.entity.User;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoyou.wyz on 2017/7/30.
 */
@Service("UserService")
public class UserService {
    @Autowired
    private  UserDAO userDAO;
    public  int login(String email,String username,String password){
        User user=new User();
        if(email == null) {
            user.setEmail(email);
        } else {
            user.setUsername(username);
        }
        List<BasicVo> userList=userDAO.listBatch(user,0,1);
        if(userList.size()==0){
            return -1;  //用户不存在
        }
        User user1=(User)userList.get(0);
        if(!user1.getPassword().equals(password)){
            return -2;//密码错误
        }else{
            return  0; //成功
        }
    }
    public int register(String username,String useremail, String userpass)
    {
        User user = new User();
        user.setUsername(username);
        user.setEmail(useremail);
        user.setPassword(userpass);
        int flag = userDAO.save(user);
        if(flag==1)
            return 1;
        return 0;
    }
    public boolean isEmailExist(String email)
    {
        boolean flag = false;
        User user = new User();
        user.setEmail(email);
        List<BasicVo> lists = userDAO.listBatch(user,0,1);
        if(lists.size()!=0) {
            flag = true;
        }
        return flag;
    }
    public boolean isUsernameExist(String username)
    {
        boolean flag = false;
        User user = new User();
        user.setUsername(username);
        List<BasicVo> lists = userDAO.listBatch(user,0,1);
        if(lists.size()!=0) {
            flag = true;
        }
        return flag;
    }
    public User getUserByUsername(String username)
    {
        User user =null;
        if(username!=null && username!="") {
            user = new User();
            user.setUsername(username);
            List<BasicVo> users=userDAO.listBatch(user,0,1);
            if(users.size()!=0) {
                user = (User)users.get(0);
            }
        }
        return user;
    }
    public int updataUser(User user)
    {
        int flag = userDAO.update(user);
        return flag;
    }
}
