package se.lexicon.dao.impl;

import se.lexicon.dao.EventDAO;
import se.lexicon.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
    private final Connection connection;

    public EventDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Event save(Event event) {
        String sql = "INSERT INTO event (calendar_id, title, description, date_time) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, event.getCalendarId());
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(event.getDateTime()));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    event.setId(resultSet.getInt(1));
                }
            }
        }catch (SQLException e){
            System.out.println("Error trying to save the event " + e.getMessage());
        }
        return event;
    }

    @Override
    public List<Event> findAllByCalendarId(int calendarId) {
        List<Event>eventList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event WHERE calendar_id = ?")){
            preparedStatement.setInt(1, calendarId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Event event = new Event(
                            resultSet.getInt("id"),
                            resultSet.getInt("calendarId"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("date_time").toLocalDateTime()
                    );
                    eventList.add(event);
                }
            }
        } catch (SQLException e){
            System.out.println("Error trying to get calender events " + e.getMessage());
        }
        return eventList;
    }

    @Override
    public void update(Event event) {
        String sql = "UPDATE event set id= ?, calendar_id= ?, title= ?, description= ?, date_time= ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, event.getId());
            preparedStatement.setInt(2, event.getCalendarId());
            preparedStatement.setString(3, event.getTitle());
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(event.getDateTime()));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 ){
                System.out.println("Update done ");
            }
        } catch (SQLException e){
            System.out.println("Error trying to update the event " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE id = ?")){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 ){
                System.out.println("The event has been deleted ");
            }
        } catch (SQLException e){
            System.out.println("Error trying to delete the event " + e.getMessage());
        }
    }
}