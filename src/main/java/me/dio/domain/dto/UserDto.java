package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.User;


import java.util.List;
import java.util.UUID;

public record UserDto(

        @NotNull(groups = {View.UserPut.class})
        @JsonView({View.UserPut.class})
        UUID id,
        @NotBlank(groups = {View.RegistrationPost.class})
        @JsonView({View.RegistrationPost.class, View.UserPut.class})
        String name,
        @NotNull(groups = {View.RegistrationPost.class})
        @JsonView({View.RegistrationPost.class, View.UserPut.class})
        @Valid
        AccountDto account,

        @NotNull(groups = {View.RegistrationPost.class})
        @JsonView({View.RegistrationPost.class, View.UserPut.class})
        @Valid
        CardDto card,

        @JsonView({View.RegistrationPost.class, View.UserPut.class})
        @Valid
        List<FeatureDto> features,

        @JsonView({View.RegistrationPost.class, View.UserPut.class})
        @Valid
        List<NewsDto> news) {

    public interface View{
        interface RegistrationPost {}
        interface UserPut{}
    }

    public UserDto(User userModel){
        this(
                userModel.getId(),
                userModel.getName(),
                new AccountDto(userModel.getAccount()),
                new CardDto(userModel.getCard()),
                userModel.getFeatures().stream().map(FeatureDto::new).toList(),
                userModel.getNews().stream().map(NewsDto::new).toList()
        );
    }

    public User toUserModel(){
        User userModel = new User();
        userModel.setName(this.name);
        userModel.setAccount(this.account.toAccountModel());
        userModel.setCard(this.card.toCardModel());
        userModel.setFeatures(this.features.stream().map(FeatureDto::toFeatureModel).toList());
        userModel.setNews(this.news.stream().map(NewsDto::toNewsModel).toList());
        return userModel;
    }
}
