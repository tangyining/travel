package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.CatagoryDao;
import cn.levitate.travel.domain.Category;
import cn.levitate.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CatagoryDaoImpl implements CatagoryDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));

    }
}
