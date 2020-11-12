package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.TerminModel;

public class TerminDAO {

    public static void select() {

        List<TerminModel> result = new ArrayList<>();

        String SQL_SELECT = "Select * from termin";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/weekplaner", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (conn != null) {
                System.out.println("Connected to the database!");
            }

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("tDate");
                Time time = resultSet.getTime("tTime");
                String title = resultSet.getString("tTitle");
                String info = resultSet.getString("tInfo");

                TerminModel mod = new TerminModel();

                mod.setId(id);
                mod.setDatum(date);
                mod.setTime(time);
                mod.setTitle(title);
                mod.setInformation(info);

                result.add(mod);

                System.out.println(mod.toString());

            }
            conn.close();

        } catch (SQLException e) {

            System.err.format(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(TerminModel mod) {

        String SQL_INSERT = "INSERT termin SET + " + "id= "+ mod.getId() + " tDate= '" + mod.getDatum() + "', tTime= '" + mod.getTime() + "', tTitle= " + mod.getTitle()
                + ", tInfo= " + mod.getInformation();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/weekplaner", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            int resultSet = preparedStatement.executeUpdate();

            System.out.println(resultSet);
            conn.close();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String SQL_DELETE = "DELETE FROM teacher WHERE id=" + id + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shinobi", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)) {

            int resultSet = preparedStatement.executeUpdate();

            System.out.println(resultSet);
            conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

