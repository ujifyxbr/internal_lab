package learn.epam.mlhh.entity;

import javax.persistence.*;

/**
 * Database entity classes for user.
 * @author
 * @version 1.1.3
 */
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(columnDefinition="varchar")
    private String userName;

    @Column(columnDefinition="varchar")
    private String userPassword;

    @Column(columnDefinition="varchar")
    private String userRole;

    @Column
    private boolean userLock;

    public Users() {
        userLock = false;
    }

    public Users(String name, String password,  String role) {
        this.userName = name;
        this.userPassword = password;
        this.userRole = role;
        this.userLock = false;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean getUserLock() {
        return userLock;
    }

    public void setUserLock(boolean userLock) {
        this.userLock = userLock;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword=" + userPassword + '\'' +
                ", userRole=" + userRole + '\'' +
                ", userLock=" + userLock +
                '}';
    }
}
