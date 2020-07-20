package cn.levitate.travel.dao;

import cn.levitate.travel.domain.RouteImg;

import java.util.List;

public interface RouteImageDao {
    public List<RouteImg> findByRid(int rid);
}
