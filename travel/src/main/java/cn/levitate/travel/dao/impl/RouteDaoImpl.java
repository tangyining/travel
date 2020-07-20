package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.RouteDao;
import cn.levitate.travel.domain.Route;
import cn.levitate.travel.util.JDBCUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
   private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rname) {
//        String sql="select count(*) from tab_route where cid=? ";
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<>();
        if(cid!=0){
            sb.append(" and cid=? ");
            list.add(cid);
        }
        if(StringUtils.isNotBlank(rname)&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        sql=sb.toString();
        return template.queryForObject(sql,Integer.class,list.toArray());

    }

    @Override
    public List<Route> findByPage(int cid, int currentPage, int pageSize, String rname) {
//        String sql="select * from tab_route where cid= ? limit ?,? ";
        String sql="select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<>();
        if(cid!=0){
            sb.append(" and cid=? ");
            list.add(cid);
        }
        if(StringUtils.isNotBlank(rname)&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        sb.append(" limit ?,? ");
        list.add(currentPage);
        list.add(pageSize);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql="select * from tab_route where rid=? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
