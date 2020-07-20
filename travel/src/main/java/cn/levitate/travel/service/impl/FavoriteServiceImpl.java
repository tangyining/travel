package cn.levitate.travel.service.impl;

import cn.levitate.travel.dao.FavoriteDao;
import cn.levitate.travel.dao.impl.FavoriteDaoImpl;
import cn.levitate.travel.domain.Favorite;
import cn.levitate.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao=new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.isFavorite(Integer.parseInt(rid), uid);
        return favorite!=null;
    }

    @Override
    public void addFavoriate(String rid, int uid) {
        dao.addFavoriate(Integer.parseInt(rid), uid);
    }
}
