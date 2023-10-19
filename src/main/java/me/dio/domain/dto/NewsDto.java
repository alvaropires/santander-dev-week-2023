package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.News;
import org.springframework.beans.BeanUtils;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private UUID id;

    @JsonView(UserDto.View.RegistrationPost.class)
    private String icon;

    @JsonView(UserDto.View.RegistrationPost.class)
    private String description;

    public News toNewsModel(){
        var newsModel = new News();
        BeanUtils.copyProperties(this, newsModel);
        return newsModel;
    }

    public NewsDto(News news){
        BeanUtils.copyProperties(news, this);
    }
}
