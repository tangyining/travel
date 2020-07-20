package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.UserDao;
import cn.levitate.travel.domain.User;
import cn.levitate.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        String sql="select * from tab_user where username=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql="insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) " +
                "values (?,?,?,?,?,?,?,?,?)";
        int update = template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());

    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String  sql="select * from tab_user where code=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateState(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        User u = null;
        try {
            String  sql="select * from tab_user where username=? and password=? ";
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }
}
