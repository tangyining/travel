package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.SellerDao;
import cn.levitate.travel.domain.Seller;
import cn.levitate.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(int id) {
        String sql="select * from tab_seller where sid=? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
