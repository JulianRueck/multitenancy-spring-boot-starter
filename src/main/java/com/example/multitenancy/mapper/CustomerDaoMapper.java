package com.example.multitenancy.mapper;

import com.example.multitenancy.customer.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int arg1) throws SQLException {

        return new Customer(rs.getInt("id"), rs.getString("name"));

    }
}