package br.com.mabs.controller;

import br.com.mabs.model.Project;
import br.com.mabs.model.Task;
import br.com.mabs.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save(Project project) {
        String querySave = "INSERT INTO projects (name, description, createdAt, updatedAt)"
                + " VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();

            //Peparando a query que será executada
            preparedStatement = connection.prepareStatement(querySave);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, new Date(project.getCreatedAt().getTime()));
            preparedStatement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to save the project. " + e.getMessage(), e);
        } finally {
            //Encerrando connection, o PreparedStatement e também quando ocorrer o ReturnSet.
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public void update(Project project) {
        String queryUpdate = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, new Date(project.getCreatedAt().getTime()));
            preparedStatement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            preparedStatement.setInt(5, project.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to update the project. " + e.getMessage(), e);
        } finally {
            //Encerrando connection, o PreparedStatement e também quando ocorrer o ReturnSet.
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public void removeProjectById(int idProject) {
        String queryDelete = "DELETE FROM projects WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setInt(1, idProject);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to delete the project. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public List<Project> getAllProjects() {
        String queryGetAllProjects = "SELECT * FROM projects";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Project> projects = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryGetAllProjects);

            //Valor retornado após a execução da query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                projects.add(project);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error to get all projects. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }
        return projects;
    }

}
