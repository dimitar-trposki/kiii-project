package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Role;

import java.util.List;

public record DisplayUserDto(String username, String name, String surname, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getUsername(), user.getName(), user.getSurname(), user.getRole());
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }

    public static List<DisplayUserDto> from(List<User> users) {
        return users.stream().map(DisplayUserDto::from).toList();
    }

}
