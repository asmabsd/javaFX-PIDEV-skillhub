/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.esprit.freelancejobs.services;

import edu.esprit.freelancejobs.entities.PostedJobs;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Rana
 */
public interface IpostedJobsService {
    public void add(PostedJobs pj);
    public void update(PostedJobs pj);
    public void delete(int id);
    public PostedJobs getOneById(int id);
    public List<PostedJobs> getAll();
}
