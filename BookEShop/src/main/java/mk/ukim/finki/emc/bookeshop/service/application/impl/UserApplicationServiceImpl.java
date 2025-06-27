package mk.ukim.finki.emc.bookeshop.service.application.impl;

import mk.ukim.finki.emc.bookeshop.dto.CreateUserDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayUserDto;
import mk.ukim.finki.emc.bookeshop.dto.LoginResponseDto;
import mk.ukim.finki.emc.bookeshop.dto.LoginUserDto;
import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.helpers.JwtHelper;
import mk.ukim.finki.emc.bookeshop.service.application.JwtLogApplicationService;
import mk.ukim.finki.emc.bookeshop.service.application.UserApplicationService;
import mk.ukim.finki.emc.bookeshop.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final JwtLogApplicationService jwtLogApplicationService;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, JwtLogApplicationService jwtLogApplicationService) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.jwtLogApplicationService = jwtLogApplicationService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password());

        String token = jwtHelper.generateToken(user);

        Instant issuedAt = jwtHelper.getIssuedAt(token);
        Instant expiresAt = jwtHelper.getExpiration(token);
        jwtLogApplicationService.logToken(user.getUsername(), token, issuedAt, expiresAt);
        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}
