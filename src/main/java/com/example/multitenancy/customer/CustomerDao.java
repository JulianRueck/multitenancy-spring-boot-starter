package com.example.multitenancy.customer;

import com.example.multitenancy.mapper.CustomerDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers() {

        List<Customer> allCustomers = new ArrayList<>();

        String sql = "select * from customer";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            allCustomers.add(new Customer((Integer) row.get("id"), row.get("name").toString()));
        }
        return allCustomers;
    }

    public Customer getCustomer(int id) {

        String sql = "select * from customer where id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerDaoMapper(), new Object[]{id});
    }
}
