package cn.levitate.travel.dao.impl;

import cn.levitate.travel.dao.FavoriteDao;
import cn.levitate.travel.domain.Favorite;
import cn.levitate.travel.domain.Route;
import cn.levitate.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite isFavorite(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql="select * from tab_favorite where rid=? and uid=? ";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql="select count(*) from tab_favorite where rid=? ";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void addFavoriate(int rid, int uid) {
        String sql=" insert into tab_favorite  values(?,?,?) ";
        template.update(sql,rid,new Date(),uid);
    }
}
