package se.lexicon;

import se.lexicon.dao.impl.EventDAOImpl;
import se.lexicon.dao.impl.MyCalendarDAOImpl;
import se.lexicon.dao.impl.PersonDAOImpl;
import se.lexicon.db.DatabaseManager;
import se.lexicon.model.Event;
import se.lexicon.model.MyCalendar;
import se.lexicon.model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(Connection connection = DatabaseManager.getConnection()){
            PersonDAOImpl personDAO = new PersonDAOImpl(connection);
            MyCalendarDAOImpl myCalendarDAO = new MyCalendarDAOImpl(connection);
            EventDAOImpl eventDAO = new EventDAOImpl(connection);

            //***************SAVE Person***********************
            //Person person = new Person("Katia", "katia@hotmail.com");
            //System.out.println(personDAO.save(person));

            //***************FIND BY ID Person **************
            //System.out.println(personDAO.findById(7));

            //***************UPDATE Person ****************
            /*Optional<Person> optionalPerson = personDAO.findById(7);
            Person person2 = null;
            if (optionalPerson.isPresent()){
                person2 = optionalPerson.get();
            }
            person2.setEmail("sebbe@gmail.com");
            System.out.println(person2.getId());
            personDAO.update(person2);    */

            //**************DELETE person *********************
            //personDAO.delete(3);

            //************** SAVE myCalendar *****************
            /* MyCalendar calendar1 = new MyCalendar(1, "work", "work calendar");
            MyCalendar calendar2 = new MyCalendar(1, "tennis", "To know when I have a match");
            MyCalendar calendar3 = new MyCalendar(7, "work", "work calendar");
            MyCalendar calendar4 = new MyCalendar(7, "dates", "When I have a date");
            MyCalendar calendar5 = new MyCalendar(8, "work", "work calendar");
            MyCalendar calendar6 = new MyCalendar(8, "travel", "To keep my travels");
            MyCalendar calendar7 = new MyCalendar(9, "work", "work calendar");
            MyCalendar calendar8 = new MyCalendar(9, "birthdays", "To remember important birthdays");


            System.out.println(myCalendarDAO.save(calendar1));
            System.out.println(myCalendarDAO.save(calendar2));
            System.out.println(myCalendarDAO.save(calendar3));
            System.out.println(myCalendarDAO.save(calendar4));
            System.out.println(myCalendarDAO.save(calendar5));
            System.out.println(myCalendarDAO.save(calendar6));
            System.out.println(myCalendarDAO.save(calendar7));
            System.out.println(myCalendarDAO.save(calendar8));  */

            //************** FIND BY ID my_calendar *********
            //System.out.println(myCalendarDAO.findById(13));

            //************** FIND BY OWNER ID my_calendar ***
            //List<MyCalendar> calendarList = myCalendarDAO.findAllByOwnerId(1);
            //calendarList.forEach(element -> System.out.println(element));

            //********** UPDATE my_calendar ******************
            /*Optional<MyCalendar> optionalMyCalendar = myCalendarDAO.findById(14);
            MyCalendar myCalendar = null;
            if (optionalMyCalendar.isPresent()){
                myCalendar = optionalMyCalendar.get();
            }
            myCalendar.setDescription("Only work things");
            System.out.println(myCalendar.getId());
            myCalendarDAO.update(myCalendar);    */



            //********** DELETE my_calender *****************
            /*myCalendarDAO.delete(2);
            myCalendarDAO.delete(3);
            myCalendarDAO.delete(4);
            myCalendarDAO.delete(5);
            myCalendarDAO.delete(6);
            myCalendarDAO.delete(7);
            myCalendarDAO.delete(8);
            myCalendarDAO.delete(9);  */

            //*************SAVE event ***********************
           /* Event event1 = new Event(10,"Meeting with customers", "Discussion about the needs", LocalDateTime.of(2025,4,17,9,15));
            Event event2 = new Event(11,"Match against JC", "Court 6", LocalDateTime.of(2025,4,17,17,00));
            Event event3 = new Event(11,"Match against Ricardo", "Court 5", LocalDateTime.of(2025,4,22,19,00));
            Event event4 = new Event(12,"Top secret", "Playing in the forest", LocalDateTime.of(2025,4,7,8,00));
            Event event5 = new Event(13,"Date with Lisa", "Asian from tinder", LocalDateTime.of(2025,4,5,19,45));
            Event event6 = new Event(15,"London", "Monthly meeting", LocalDateTime.of(2025,4,26,7,15));
            Event event7 = new Event(17,"Hernan birthday", "Big party party", LocalDateTime.of(2025,8,30,14,00));
            eventDAO.save(event1);
            eventDAO.save(event2);
            eventDAO.save(event3);
            eventDAO.save(event4);
            eventDAO.save(event5);
            eventDAO.save(event6);
            eventDAO.save(event7);  */

            //*************DELETE event ********************
            //eventDAO.delete(3);

            //************FIND ALL BY CALENDER EVENT *******
            List<Event> eventList = new ArrayList<>();
            eventList = eventDAO.findAllByCalendarId(11);
            eventList.forEach(element -> System.out.println(element.toString()));


            //***********UPDATE event **********************
            /*Event eventToUpdate = eventDAO.findById(9);
            eventToUpdate.setDescription("party");
            eventDAO.update(eventToUpdate);  */

        }catch (SQLException e) {
            System.out.println("Error occurred trying to access the database " + e.getMessage());
        }

    }
}