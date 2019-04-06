package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface ShopDao extends JpaRepository<Shop,String> {

    /**
     * 查询用户名和密码以验证登录
     * @param adminAccount
     * @param adminPassword
     * @return
     */
    @Query(value = "select * from shop where account =?1 and password =?2",nativeQuery = true)
    Shop getAdminByAccountAndPassword(String adminAccount,String adminPassword);


    /**
     * 查询商家
     * @param shopId
     * @return
     */
    @Query(value = "select * from shop where shop_id = ?1",nativeQuery = true)
    Shop queryShop(int shopId);


    /**
     * 查询商家
     * @param shopName
     * @return
     */
    @Query(value = "select * from shop where shop_name = ?1",nativeQuery = true)
    List<Shop> queryShopByName(String shopName);

    /**
     * 查询销量前五的工厂
     * @return
     */
    @Query(value = "select * from shop order by sales_volume desc limit 5;",nativeQuery = true)
    List<Shop> getTop5shops();

    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set flour_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateFlourFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set cheese_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateCheeseFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set egg_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateEggFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set vegetable_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateVegetableFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set meat_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateMeatFormulaCount (int shopId,int newCount);

    @Query(value = "select * from shop",nativeQuery = true)
    List<Shop> queryAllShop();

    /**
     * 新增店铺
     */
    @Transactional
    @Modifying
    @Query(value = "insert into shop (shop_name, pos_x, pos_y, pos_string,pic_url, account, password, phone, start_time,end_time) " +
            "values (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery = true)
    int insertShop (String shopName,double posX,double posY,String posString,String picUrl, String account,String password,String phone,String startTime, String endTime);

    /**
     * 删除店铺
     */
    @Transactional
    @Modifying
    @Query(value = "delete from shop where shop_id = ?1",nativeQuery = true)
    int deleteShop(int shopId);

    /**
     * 修改店铺信息
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set shop_name = ?2, pos_x=?3, pos_y=?4, pos_string=?5, pic_url=?6, " +
               "phone=?7, start_time=?8, end_time=?9 where shop_id = ?1 ", nativeQuery = true)
    int updateShop (int shopID, String shopName, double posX, double posY, String shopAddress,
                    String shopPicUrl, String shopPhone, String shopStartTime, String shopEndTime);

    /**
     * 更新店铺原料
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set flour_quantity = ?2, egg_quantity = ?3, cheese_quantity = ?4,vegetable_quantity = ?5, meat_quantity = ?6 where shop_id = ?1",nativeQuery = true)
    int updateAllFormulaCount(int shopId,int restFlourCount,int restEggCount,int restCheeseCount,int restVegetableCount,int restMeatCount);
}
