package com.test.springDataJPA.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long certId;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    @Column(name = "training_name")
    private String  trainingName;
    @Column(name = "type_cert")
    private String  typeCert;
    @Column(name = "award")
    private String  award;
    @Column(name = "subject")
    private String  subject;
    @Column(name = "time")
    private int  time;
    @Column(name = "remarks")
    private String remarks;

    public Long getCertId() {
        return certId;
    }

    public void setCertId(Long certId) {
        this.certId = certId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTypeCert() {
        return typeCert;
    }

    public void setTypeCert(String typeCert) {
        this.typeCert = typeCert;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
