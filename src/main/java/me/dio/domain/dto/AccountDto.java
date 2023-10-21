package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.Account;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        @NotNull(groups = {UserDto.View.UserPut.class})
        @JsonView({UserDto.View.UserPut.class})
        UUID id,
        @NotBlank(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String number,

        @NotBlank(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String agency,

        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        BigDecimal balance,

        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        BigDecimal limit) {

    public AccountDto(Account account){
        this(account.getId(),account.getNumber(),account.getAgency(),account.getBalance(),account.getLimit());
    }

    public Account toAccountModel(){
        Account accountModel = new Account();
        BeanUtils.copyProperties(this, accountModel);
        return accountModel;
    }
}
