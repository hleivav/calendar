package se.lexicon.dao.impl;

import se.lexicon.dao.MyCalendarDAO;
import se.lexicon.model.MyCalendar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyCalendarDAOImpl implements MyCalendarDAO {
    final private Connection connection;

    public MyCalendarDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MyCalendar save(MyCalendar calendar) {
        String sql = "INSERT INTO my_calendar (person_id, name, description) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, calendar.getPersonId());
            preparedStatement.setString(2, calendar.getName());
            preparedStatement.setString(3, calendar.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        calendar.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error trying to save calendar " + e.getMessage());
        }
        return calendar;
    }

    @Override
    public Optional<MyCalendar> findById(int id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM my_calendar where id = ?")){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    MyCalendar calendar = new MyCalendar(
                            resultSet.getInt("id"),
                            resultSet.getInt("person_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );

                    return Optional.of(calendar);
                }
            }
        }catch (SQLException e){
            System.out.println("Error trying to find calendar " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<MyCalendar> findAllByOwnerId(int ownerId) {
        List<MyCalendar> myCalendarList = new ArrayList<>();
        String sql = "SELECT * FROM my_calendar WHERE person_id = ?" ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, ownerId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    MyCalendar myCalendar = new MyCalendar(
                            resultSet.getInt("id"),
                            resultSet.getInt("person_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );
                    myCalendarList.add(myCalendar);
                }
            }

        }catch (SQLException e){
            System.out.println("Error trying to find calendar " + e.getMessage());
        }

        return myCalendarList;
    }

    @Override
    public void update(MyCalendar calendar) {
        String sql = "UPDATE my_calendar SET person_id = ?, name = ?, description = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, calendar.getPersonId());
            preparedStatement.setString(2, calendar.getName());
            preparedStatement.setString(3, calendar.getDescription());
            preparedStatement.setInt(4, calendar.getId());
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println((affectedRows > 0) ? "Update done" : "Error updating");
        } catch (SQLException e){
            System.out.println("Error trying to update calendar " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM my_calendar WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println((affectedRows > 0) ? "The calendar har been deleted" : "Error trying to delete calendar.");
        } catch (SQLException e){
            System.out.println("Error trying to delete calendar " + e.getMessage());
        }
    }
}
