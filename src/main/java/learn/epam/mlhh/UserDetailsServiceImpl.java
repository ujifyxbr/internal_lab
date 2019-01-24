package learn.epam.mlhh;

import learn.epam.mlhh.entity.Users;
import learn.epam.mlhh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authentication with a Database-backed UserDetailsService
 * @author
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    /**
     * Locates the user based on the username.
     * @param username user name
     */

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userService.findByName(username);

        User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getUserPassword()));
            builder.roles(user.getUserRole());
            builder.accountLocked(user.getUserLock());
        } else {
            logger.info("User not found.");
            throw new UsernameNotFoundException("User not found.");
        }

        logger.info(user.getUserName() + " has successfully logged in ");
        return builder.build();
    }
}
