package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
}
