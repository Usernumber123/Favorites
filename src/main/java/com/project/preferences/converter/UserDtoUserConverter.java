package com.project.preferences.converter;

import com.project.preferences.model.User;
import com.project.preferences.model.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.project.preferences.model.Role.USER;

@Component
public class UserDtoUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setBirthday(userDto.getBirthday());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(USER);
        user.setEmail(userDto.getEmail());
        return user;
    }
}