package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.Card;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

public record CardDto(
        @NotNull(groups = {UserDto.View.UserPut.class})
        @JsonView({UserDto.View.UserPut.class})
        UUID id,
        @NotBlank(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String number,

        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        BigDecimal limit) {

    public CardDto(Card card){
        this(card.getId(), card.getNumber(), card.getLimit());
    }

    public Card toCardModel(){
        Card cardModel = new Card();
        BeanUtils.copyProperties(this, cardModel);
        return cardModel;
    }
}
