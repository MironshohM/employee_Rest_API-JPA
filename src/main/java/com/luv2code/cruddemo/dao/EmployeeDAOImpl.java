package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> typedQuery=entityManager.createQuery("from Employee",Employee.class);
        List<Employee> employees=typedQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee=entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee theEmployee=entityManager.merge(employee);
        return theEmployee;
    }

    @Override
    public void deleteByID(int id) {
        Employee employee=entityManager.find(Employee.class,id);
        entityManager.remove(employee);


    }
}
