package br.com.mabs.controller;

import br.com.mabs.model.Task;
import br.com.mabs.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public void save(Task task) {
        String querySave = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Cria conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Peparando a query que será executada
            preparedStatement = connection.prepareStatement(querySave);
            preparedStatement.setInt(1, task.getIdProject());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setBoolean(4, task.isCompleted());
            preparedStatement.setString(5, task.getNotes());            
            preparedStatement.setDate(6, new Date(task.getDeadline().getTime()));
            preparedStatement.setDate(7, new Date(task.getCreatedAt().getTime()));
            preparedStatement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            //Executando a query
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to save the task. " + e.getMessage(), e);
        } finally {
            //Encerrando connection, o PreparedStatement e também quando ocorrer o ReturnSet.
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public void update(Task task) {
        String queryUpdate = "UPDATE tasks SET idProject = ?, name = ?, description = ?, "
                + "completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? "
                + "WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setInt(1, task.getIdProject());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setBoolean(4, task.isCompleted());
            preparedStatement.setString(5, task.getNotes());            
            preparedStatement.setDate(6, new Date(task.getDeadline().getTime()));
            preparedStatement.setDate(7, new Date(task.getCreatedAt().getTime()));
            preparedStatement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            preparedStatement.setInt(9, task.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to update the task. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public void removeTaskById(int taskId) {
        String queryDelete = "DELETE FROM tasks WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setInt(1, taskId);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error to delete the task. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public List<Task> getAllTasksByProject(int idProject) {
        String queryGetAllTasksByProject = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(queryGetAllTasksByProject);
            preparedStatement.setInt(1, idProject);
            
            //Valor retornado após a execução da query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                tasks.add(task);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error to get all tasks" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }
        return tasks;
    }
    
}
