package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    // This method takes user as a parameter
    // returns 'true if the user's password meets the minimum  password strength
    // A password should have at least 1 special character, 1 letter and 1 digit
    // This method uses three regular expressions to check password strength
    // hasLetter = check if password has at least 1 letter
    // hasDigit = check if password has at least 1 digit
    // hasSpecialChar = check if password has at least 1 special character
    // Method returns true only if all three regular expressions result in a match
    public boolean checkPasswordStrength(User user) {
        String hasLetter = "^.*[a-zA-Z].*$";
        String hasDigit = "^.*[0-9].*$";
        // List of special characters used below are from the OWASP standards
        // Refer: https://owasp.org/www-community/password-special-characters
        String hasSpecialChar = "^.*[\\ \\!\\\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\\\\\]\\^\\_\\`\\{\\|\\}\\~].*$";
        return user.getPassword().matches(hasLetter) && user.getPassword().matches(hasDigit) && user.getPassword().matches(hasSpecialChar);
    }

}
