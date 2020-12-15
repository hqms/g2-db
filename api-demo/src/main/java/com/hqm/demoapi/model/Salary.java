package com.hqm.demoapi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salary {

    @EmbeddedId
    private SalaryPk salaryPk;

    private  int salary;

}
