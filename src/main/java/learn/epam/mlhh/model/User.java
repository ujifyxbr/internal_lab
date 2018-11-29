package learn.epam.mlhh.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    public User(Long id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }


    //getters and setters
}