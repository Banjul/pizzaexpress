package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Formula;
import com.springboot.pizzaexpress.dao.FormulaDao;
import com.springboot.pizzaexpress.service.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormulaServiceImp implements FormulaService{
    @Autowired
    private FormulaDao formulaDao;

}
