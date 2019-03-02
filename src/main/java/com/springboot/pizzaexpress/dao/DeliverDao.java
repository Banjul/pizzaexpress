package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface DeliverDao extends JpaRepository<Deliver,String>{

}
