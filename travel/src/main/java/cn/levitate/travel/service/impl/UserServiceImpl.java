package cn.levitate.travel.service.impl;

import cn.levitate.travel.dao.UserDao;
import cn.levitate.travel.dao.impl.UserDaoImpl;
import cn.levitate.travel.domain.User;
import cn.levitate.travel.service.UserService;
import cn.levitate.travel.util.MailUtils;
import cn.levitate.travel.util.UuidUtil;

public class UserServiceImpl implements UserService{
   private UserDao userdao=new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User u = userdao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userdao.save(user);
        String content="<a href='http://localhost:8080/travel/user/activeUser?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        User user=userdao.findByCode(code);
        if(user!=null){
            userdao.updateState(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        User u=userdao.findByUsernameAndPassword(user);
        return u;
    }
}
