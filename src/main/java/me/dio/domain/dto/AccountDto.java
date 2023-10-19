package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.Account;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto{
    private UUID id;

    @NotBlank(groups = {UserDto.View.RegistrationPost.class})
    @JsonView(UserDto.View.RegistrationPost.class)
    private String number;

    @NotBlank(groups = UserDto.View.RegistrationPost.class)
    @JsonView(UserDto.View.RegistrationPost.class)
    private String agency;

    @NotNull(groups = UserDto.View.RegistrationPost.class)
    @JsonView(UserDto.View.RegistrationPost.class)
    private BigDecimal balance;

    @NotNull(groups = UserDto.View.RegistrationPost.class)
    @JsonView(UserDto.View.RegistrationPost.class)
    private BigDecimal limit;

    public AccountDto(Account account){
        BeanUtils.copyProperties(account, this);
    }

    public Account toAccountModel(){
        var accountModel = new Account();
        BeanUtils.copyProperties(this, accountModel);
        return accountModel;
    }
}

