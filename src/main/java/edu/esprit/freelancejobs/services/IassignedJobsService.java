/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelancejobs.services;

import edu.esprit.freelancejobs.entities.AssignedJobs;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Rana
 */
public interface IassignedJobsService {
    public void add(AssignedJobs aj);
    public void update(AssignedJobs aj);
    public void delete(int id);
    public AssignedJobs getOneById(int id);
    public List<AssignedJobs> getAll();
}
