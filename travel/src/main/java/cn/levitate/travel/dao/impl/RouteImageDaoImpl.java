package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.RouteImageDao;
import cn.levitate.travel.domain.RouteImg;
import cn.levitate.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImageDaoImpl implements RouteImageDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql="select * from tab_route_img where rid=? ";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
