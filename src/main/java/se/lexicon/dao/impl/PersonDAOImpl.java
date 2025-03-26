package se.lexicon.dao.impl;

import se.lexicon.dao.PersonDAO;
import se.lexicon.model.Person;

import java.sql.*;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {
    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Person save(Person person) {
        String sql = "INSERT INTO person (name, email) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        int id = resultSet.getInt(1);
                        person.setId(id);
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error trying to save person to the database " + e.getMessage());
        }
        return person;
    }

    @Override
    public Optional<Person> findById(int id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?")){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    );
                    return Optional.of(person);
                }
            }

        } catch (SQLException e){
            System.out.println("Error trying to access the table person " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET name = ?, email = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, person.getId());
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println((affectedRows > 0) ? "Correct updated" : "Error when updating");
        } catch (SQLException e){
            System.out.println("Error trying to update person table " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM person where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println((affectedRows > 0) ? "The row has been deleted" : "Error trying to delete the row.");
        } catch (SQLException e){
            System.out.println("Error trying to delete " + e.getMessage());
        }
    }
}
