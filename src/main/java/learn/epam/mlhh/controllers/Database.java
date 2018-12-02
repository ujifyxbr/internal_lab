package learn.epam.mlhh.controllers;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Database {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    private static Connection connection = null;
    private static Statement statement = null;

    private int id_candidate;
    private String name;
    private int age;
    private String gender;
    private String region;
    private int salary;
    private int developer;
    private int experience;
    private String keyword;

    public int getId_candidate() {
        return id_candidate;
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

    public int getDeveloper() {
        return developer;
    }

    public int getExperience() {
        return experience;
    }

    public String getKeyword() {
        return keyword;
    }


    private Database(int id_candidate, String name, int age, String gender, String region,
                     int salary, int developer, int experience, String keyword) {
        this.id_candidate = id_candidate;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.salary = salary;
        this.developer = developer;
        this.experience = experience;
        this.keyword = keyword;
    }

    public static void connectDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Database Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL Database Driver successfully connected");
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    public static List<Database> databaseToArray() {
        Database dbOfCandidats;
        List<Database> candidats = new ArrayList<Database>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM public.\"Candidate\"");
            while (rs.next()) {
                dbOfCandidats = new Database(
                        rs.getInt("id_candidate"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("region"),
                        rs.getInt("salary"),
                        rs.getInt("developer"),
                        rs.getInt("experience"),
                        rs.getString("keyword"));
                candidats.add(dbOfCandidats);
            }

        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
        }
        return candidats;
    }


    public static void oldConnectDB () {

    }
}
