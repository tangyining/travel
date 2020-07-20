package cn.levitate.travel.dao;

import cn.levitate.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid, String rname);
    public List<Route> findByPage(int cid, int currentPage, int pageSize, String rname);

    Route findOne(int rid);
}
