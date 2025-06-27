package mk.ukim.finki.emc.bookeshop.service.application;

import mk.ukim.finki.emc.bookeshop.dto.CreateUserDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayUserDto;
import mk.ukim.finki.emc.bookeshop.dto.LoginResponseDto;
import mk.ukim.finki.emc.bookeshop.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
