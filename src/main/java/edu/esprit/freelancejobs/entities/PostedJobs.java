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
public class PostedJobs {
    private int id;
    private String title;
    private String description;
    private String requiredSkills;
    private double budgetEstimate;
    private Date startDate;
    private Date endDate;
    private String status;

    public PostedJobs() {
    }

    public PostedJobs(int id, String title, String description, String requiredSkills, double budgetEstimate, Date startDate, Date endDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.budgetEstimate = budgetEstimate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public PostedJobs(String title, String description, String requiredSkills, double budgetEstimate, Date startDate, Date endDate, String status) {
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.budgetEstimate = budgetEstimate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public double getBudgetEstimate() {
        return budgetEstimate;
    }

    public void setBudgetEstimate(double budgetEstimate) {
        this.budgetEstimate = budgetEstimate;
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

    @Override
    public String toString() {
        return "postedJobs{" + "id=" + id + ", title=" + title + ", description=" + description + ", requiredSkills=" + requiredSkills + ", budgetEstimate=" + budgetEstimate + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + '}';
    }
    
    
}
