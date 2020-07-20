package cn.levitate.travel.dao;

import cn.levitate.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite isFavorite(int rid, int uid);

    int findCountByRid(int rid);

    void addFavoriate(int i, int uid);
}
