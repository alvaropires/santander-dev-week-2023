package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.News;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public record NewsDto(

        @NotNull(groups = {UserDto.View.UserPut.class})
        @JsonView({UserDto.View.UserPut.class})
        UUID id,
        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String icon,

        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String description) {

    public NewsDto(News news){
        this(news.getId(), news.getIcon(), news.getDescription());
    }

    public News toNewsModel(){
        News newsModel = new News();
        BeanUtils.copyProperties(this, newsModel);
        return newsModel;
    }
}
