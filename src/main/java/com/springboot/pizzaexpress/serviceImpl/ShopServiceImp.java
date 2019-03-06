package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImp implements ShopService{
    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop adminLogin(String adminAccount, String admingPassword) {
        return shopDao.getAdminByAccountAndPassword(adminAccount,admingPassword);
    }
}
