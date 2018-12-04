package learn.epam.mlhh.entity;

import javax.persistence.*;

/**
 * Database entity classes for user.
 * @author
 * @version 1.1.2
 */
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition="text")
    private String userName;

    @Column(columnDefinition="text")
    private String userPassword;

    public Users() {
    }

    public Users(String name, String password) {
        this.userName = name;
        this.userPassword = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword=" + userPassword +
                '}';
    }
}
