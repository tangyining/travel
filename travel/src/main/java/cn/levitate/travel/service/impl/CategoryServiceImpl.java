package cn.levitate.travel.service.impl;

import cn.levitate.travel.dao.CatagoryDao;
import cn.levitate.travel.dao.impl.CatagoryDaoImpl;
import cn.levitate.travel.domain.Category;
import cn.levitate.travel.service.CategoryService;
import cn.levitate.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CatagoryDao dao=new CatagoryDaoImpl();
    @Override
    public List<Category> findAll() {
      /*  Jedis jedis = JedisUtil.getJedis();
//        Set<String> category = jedis.zrange("category", 0, -1);
        Set<Tuple> tuples = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs=null;
        if(category==null||category.size()==0){
            cs=dao.findAll();
            for (int i = 0; i <cs.size() ; i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            cs=new ArrayList<Category>();
//            for (String name : category) {
//                Category category1 = new Category();
//                category1.setCname(name);
//                cs.add(category1);
//            }
            for (Tuple tuple : tuples) {
                Category category1 = new Category();
                category1.setCname(tuple.getElement());
                category1.setCid((int) tuple.getScore());
                cs.add(category1);
            }
        }
        return cs;*/
        return dao.findAll();
    }
}
