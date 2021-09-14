package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.ValidNames;
import ru.itis.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidNames(message = "Введите разные имена",
            name = "firstName",
            surname = "lastName")
public class UserSignUpForm {
    @NotEmpty(message = "Заполните это поле")
    private String firstName;
    @NotEmpty(message = "Заполните это поле")
    private String lastName;
    @NotEmpty(message = "Заполните это поле")
    @Email(message = "Неправильная почта")
    private String email;
    @ValidPassword(message = "Неправильный пароль")
    private String password;
}
