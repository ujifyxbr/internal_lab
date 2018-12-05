package learn.epam.mlhh.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;


public class Database {
    private static String url;
    private static String username;
    private static String password;
    private static Connection connection = null;
    private static Statement statement = null;

    private int candidate_id;
    private String name;
    private int age;
    private String gender;
    private String region;
    private int salary;
    private String developer;
    private int experience;
    private String keyword;


    public int getId_candidate() {
        return candidate_id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    public int getSalary() {
        return salary;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getExperience() {
        return experience;
    }

    public String getKeyword() {
        return keyword;
    }


    private Database(int candidate_id, int age, String developer, int experience, String gender,
                     String keyword, String name,  String region, int salary) {
        this.candidate_id = candidate_id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.salary = salary;
        this.developer = developer;
        this.experience = experience;
        this.keyword = keyword;
    }

    public static List<Database> databaseToArray() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            url = property.getProperty("spring.datasource.url");
            username = property.getProperty("spring.datasource.username");
            password = property.getProperty("spring.datasource.password");
        }catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

    Database dbOfCandidats;
        List<Database> candidats = new ArrayList<Database>();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.candidate");
            while (rs.next()) {
                dbOfCandidats = new Database(
                        rs.getInt("candidate_id"),
                        rs.getInt("age"),
                        rs.getString("developer"),
                        rs.getInt("experience"),
                        rs.getString("gender"),
                        rs.getString("keyword"),
                        rs.getString("name"),
                        rs.getString("region"),
                        rs.getInt("salary"));
                candidats.add(dbOfCandidats);
            }

        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
        }
        return candidats;
    }

}
