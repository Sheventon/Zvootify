package ru.itis.services;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    User getById(Long id);

    User getByEmail(String username);

    boolean existUser(String email);

    String generateSecurePassword(String password);

    void save(UserSignUpForm userForm);

    void banUser(Long id);

    void buyAlbum(User user, Long id);

    String buySong(User user, Long id);

    String signUp(UserSignUpForm userForm, BindingResult bindingResult, Model model);
}
