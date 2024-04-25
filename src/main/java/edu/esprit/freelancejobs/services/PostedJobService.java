/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelancejobs.services;

import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.utils.MyDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rana
 */
public class PostedJobService implements IpostedJobsService {
    
    static Connection cnx = MyDatabase.getInstance().getCnx();

    public PostedJobService() {
        System.out.println("Connected successfully !");
    }
    
    @Override
    public void add(PostedJobs pj) {
        String q = "INSERT INTO posted_jobs (id, title, description, required_skills, budget_estimate, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, pj.getId());
            preparedStatement.setString(2, pj.getTitle());
            preparedStatement.setString(3, pj.getDescription());
            preparedStatement.setString(4, pj.getRequiredSkills());
            preparedStatement.setDouble(5, pj.getBudgetEstimate());
            preparedStatement.setDate(6, pj.getStartDate());
            preparedStatement.setDate(7, pj.getEndDate());
            preparedStatement.setString(8, pj.getStatus());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(PostedJobs pj) {            
        String q = "UPDATE posted_jobs SET title=?, description=?, required_skills=?, budget_estimate=?, start_date=?, end_date=?, status=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setString(1, pj.getTitle());
            preparedStatement.setString(2, pj.getDescription());
            preparedStatement.setString(3, pj.getRequiredSkills());
            preparedStatement.setDouble(4, pj.getBudgetEstimate());
            preparedStatement.setDate(5, pj.getStartDate());
            preparedStatement.setDate(6, pj.getEndDate());
            preparedStatement.setString(7, pj.getStatus());
            preparedStatement.setInt(8, pj.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String q = "DELETE FROM posted_jobs WHERE id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PostedJobs getOneById(int id) {
        try {
            String q = "SELECT * FROM posted_jobs WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PostedJobs(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("required_skills"),
                        resultSet.getDouble("budget_estimate"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("status")
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PostedJobs> getAll() {
        String q = "SELECT * FROM posted_jobs";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PostedJobs> postedJobs = new ArrayList<>();
            while (resultSet.next()) {
                postedJobs.add(new PostedJobs(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("required_skills"),
                        resultSet.getDouble("budget_estimate"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("status")
                ));
            }
            return postedJobs;
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
