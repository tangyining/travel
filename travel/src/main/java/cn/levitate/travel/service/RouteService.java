package cn.levitate.travel.service;

import cn.levitate.travel.domain.PageBean;
import cn.levitate.travel.domain.Route;

public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);
}
