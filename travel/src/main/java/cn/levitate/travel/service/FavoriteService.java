package cn.levitate.travel.service;

public interface FavoriteService {
    public boolean isFavorite(String rid,int uid);

    void addFavoriate(String rid, int uid);
}

