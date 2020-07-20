package cn.levitate.travel.service.impl;

import cn.levitate.travel.dao.FavoriteDao;
import cn.levitate.travel.dao.RouteDao;
import cn.levitate.travel.dao.RouteImageDao;
import cn.levitate.travel.dao.SellerDao;
import cn.levitate.travel.dao.impl.FavoriteDaoImpl;
import cn.levitate.travel.dao.impl.RouteDaoImpl;
import cn.levitate.travel.dao.impl.RouteImageDaoImpl;
import cn.levitate.travel.dao.impl.SellerDaoImpl;
import cn.levitate.travel.domain.PageBean;
import cn.levitate.travel.domain.Route;
import cn.levitate.travel.domain.RouteImg;
import cn.levitate.travel.domain.Seller;
import cn.levitate.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private RouteImageDao imgDao=new RouteImageDaoImpl();
    private FavoriteDao fdao=new FavoriteDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<>();
        int start=(currentPage-1)*pageSize;
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount = dao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pb.setTotalPage(totalPage);
        pb.setList(dao.findByPage(cid,start,pageSize,rname));
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = dao.findOne(Integer.parseInt(rid));
        List<RouteImg> routeImg = imgDao.findByRid(Integer.parseInt(rid));
        route.setRouteImgList(routeImg);
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
       int count= fdao.findCountByRid(route.getRid());
       route.setCount(count);
        return route;
    }
}
