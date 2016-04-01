package com.haiqiu.nature.rest.impl;

import com.haiqiu.nature.dao.AnimalMapper;
import com.haiqiu.nature.entity.Animal;
import com.haiqiu.nature.rest.INature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
@Component("NatureImpl")
public class NatureImpl implements INature {

    @Autowired
    private AnimalMapper dao;

    @Override
    public void test() {
        Animal animal = dao.selectByPrimaryKey(1);
        System.out.println(animal.getName());
    }
}
