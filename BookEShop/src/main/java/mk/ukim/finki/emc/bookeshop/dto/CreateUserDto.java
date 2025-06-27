package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Role;

import java.util.List;

public record CreateUserDto(String username,
                            String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role
) {

    public static CreateUserDto from(User user) {
        return new CreateUserDto(user.getUsername(), user.getPassword(), user.getPassword(), user.getName(), user.getSurname(), user.getRole());
    }

    public static List<CreateUserDto> from(List<User> users) {
        return users.stream().map(CreateUserDto::from).toList();
    }

}
