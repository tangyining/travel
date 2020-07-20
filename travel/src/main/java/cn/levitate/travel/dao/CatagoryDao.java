package cn.levitate.travel.dao;

import cn.levitate.travel.domain.Category;

import java.util.List;

public interface CatagoryDao {
    public List<Category> findAll();
}
