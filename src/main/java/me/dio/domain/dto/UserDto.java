package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.User;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public interface View{
        public static interface RegistrationPost {}
    }

    private UUID id;

    @NotBlank(groups = View.RegistrationPost.class)
    @JsonView(View.RegistrationPost.class)
    private String name;

    @NotNull(groups = View.RegistrationPost.class)
    @JsonView(View.RegistrationPost.class)
    @Valid
    private AccountDto account;

    @NotNull(groups = View.RegistrationPost.class)
    @JsonView(View.RegistrationPost.class)
    @Valid
    private CardDto card;

    @JsonView(View.RegistrationPost.class)
    private List<FeatureDto> features;

    @JsonView(View.RegistrationPost.class)
    private List<NewsDto> news;

    public User toUserModel(){
        var userModel = new User();
        userModel.setName(this.getName());
        userModel.setAccount(this.getAccount().toAccountModel());
        userModel.setCard(this.getCard().toCardModel());
        userModel.setFeatures(this.getFeatures().stream().map(FeatureDto::toFeatureModel).toList());
        userModel.setNews(this.getNews().stream().map(NewsDto::toNewsModel).toList());
        return userModel;
    }

    public UserDto (User userModel){
        this.setId(userModel.getId());
        this.setName(userModel.getName());
        this.setAccount(new AccountDto(userModel.getAccount()));
        this.setCard(new CardDto(userModel.getCard()));
        this.setFeatures(userModel.getFeatures().stream().map(FeatureDto::new).toList());
        this.setNews(userModel.getNews().stream().map(NewsDto::new).toList());
    }

}
