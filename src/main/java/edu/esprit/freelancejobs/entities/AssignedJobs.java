/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelancejobs.entities;

import java.sql.Date;

/**
 *
 * @author Rana
 */
public class AssignedJobs {
    private int id;
    private Date startDate;
    private Date endDate;
    private String status;
    private int noId;

    public AssignedJobs() {
    }

    public AssignedJobs(int id, Date startDate, Date endDate, String status, int noId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.noId = noId;
    }
    
    public AssignedJobs(Date startDate, Date endDate, String status, int noId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.noId = noId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoId() {
        return noId;
    }

    public void setNoId(int noId) {
        this.noId = noId;
    }

    @Override
    public String toString() {
        return "assignedJobs{ startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", noId=" + noId + '}';
    }
    
}
