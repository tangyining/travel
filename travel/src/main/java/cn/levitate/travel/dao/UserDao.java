package cn.levitate.travel.dao;

import cn.levitate.travel.domain.User;

public interface UserDao {
    public User findByUsername(String name);

    public void save(User user);

    User findByCode(String code);

    void updateState(User user);

    User findByUsernameAndPassword(User user);
}
