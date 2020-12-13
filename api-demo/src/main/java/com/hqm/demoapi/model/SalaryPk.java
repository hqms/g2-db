package com.hqm.demoapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class SalaryPk implements Serializable {

    @Column(name = "emp_no")
    protected long emp_no;

    @Column(name = "from_date")
    protected LocalDate from_date;

    @Column(name = "to_date")
    protected LocalDate to_date;

    public SalaryPk(){

    }

    public SalaryPk(long emp_no, LocalDate from_date, LocalDate to_date){
        this.emp_no = emp_no;
        this.from_date = from_date;
        this.to_date = to_date;

    }

    public long getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(long emp_no) {
        this.emp_no = emp_no;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }
}
