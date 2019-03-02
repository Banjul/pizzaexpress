package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.WareHouse;
import com.springboot.pizzaexpress.dao.WareHouseDao;
import com.springboot.pizzaexpress.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WareHouseServiceImp implements WarehouseService{
    @Autowired
    private WareHouseDao wareHouseDao;

}
