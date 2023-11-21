package com.scuec.fruit.dao.impl;

import com.scuec.fruit.dao.FruitDAO;
import com.scuec.fruit.pojo.Fruit;
import com.scuec.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
