package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.Card;
import org.springframework.beans.BeanUtils;


import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private UUID id;

    @NotBlank(groups = UserDto.View.RegistrationPost.class)
    @JsonView(UserDto.View.RegistrationPost.class)
    private String number;

    @NotNull(groups = UserDto.View.RegistrationPost.class)
    @JsonView(UserDto.View.RegistrationPost.class)
    private BigDecimal limit;

    public Card toCardModel(){
        var cardModel = new Card();
        BeanUtils.copyProperties(this, cardModel);
        return cardModel;
    }

    public CardDto(Card card){
        BeanUtils.copyProperties(card, this);
    }
}
