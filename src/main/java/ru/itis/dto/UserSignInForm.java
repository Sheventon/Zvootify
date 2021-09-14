package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInForm {
    @NotEmpty(message = "Заполните это поле")
    @Email(message = "Неправильная почта")
    private String email;
    @ValidPassword(message = "Неправильный пароль")
    private String password;
}
