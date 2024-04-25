/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelancejobs.services;

import edu.esprit.freelancejobs.entities.AssignedJobs;
import edu.esprit.freelancejobs.utils.MyDatabase;
import java.sql.Connection;
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
public class AssignedJobsService implements IassignedJobsService {
    
    static Connection cnx = MyDatabase.getInstance().getCnx();

    public AssignedJobsService() {
        System.out.println("Connected successfully !");
    }

    @Override
    public void add(AssignedJobs aj) {            
        String q = "INSERT INTO assigned_jobs (id, start_date, end_date, status, no_id) VALUES (?, ?, ?, ?, ?)";       
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, aj.getId());
            preparedStatement.setDate(2, aj.getStartDate());
            preparedStatement.setDate(3, aj.getEndDate());
            preparedStatement.setString(4, aj.getStatus());
            preparedStatement.setInt(5, aj.getNoId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(AssignedJobs aj) {
        String q = "UPDATE assigned_jobs SET start_date=?, end_date=?, status=?, no_id=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setDate(1, aj.getStartDate());
            preparedStatement.setDate(2, aj.getEndDate());
            preparedStatement.setString(3, aj.getStatus());
            preparedStatement.setInt(4, aj.getNoId());
            preparedStatement.setInt(5, aj.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String q = "DELETE FROM assigned_jobs WHERE id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AssignedJobs getOneById(int id) {
        String q = "SELECT * FROM assigned_jobs WHERE id=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new AssignedJobs(
                        resultSet.getInt("id"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("status"),
                        resultSet.getInt("no_id")
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AssignedJobs> getAll() {
        String q = "SELECT * FROM assigned_jobs";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(q);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<AssignedJobs> assignedJobs = new ArrayList<>();
            while (resultSet.next()) {
                assignedJobs.add(new AssignedJobs(
                        resultSet.getInt("id"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("status"),
                        resultSet.getInt("no_id")
                ));
            }
            return assignedJobs;
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobService.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    
}
