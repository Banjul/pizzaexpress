package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
@Repository
public interface FormulaDao extends JpaRepository<Formula,String> {

    /**
     * 查询pizza配方
     * @param formulaId
     * @return
     */
    @Query(value = "select * from formula where  formula_id = ?1",nativeQuery = true)
    Formula queryFormulaById(int formulaId);

    @Transactional
    @Modifying
    @Query(value = "insert into formula(flour_quantity,egg_quantity,cheese_quantity,vegetable_quantity,meat_quantity) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    void insertFormula(double flour, double egg,double cheese,double vegetable,double meat);

    @Query(value = "select formula_id from formula order by formula_id desc limit 1",nativeQuery = true)
    int queryLastId();
}
