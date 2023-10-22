package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.Feature;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public record FeatureDto(

        @NotNull(groups = {UserDto.View.UserPut.class})
        @JsonView({UserDto.View.UserPut.class})
        UUID id,
        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String icon,

        @NotNull(groups = {UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        @JsonView({UserDto.View.RegistrationPost.class, UserDto.View.UserPut.class})
        String description) {

    public FeatureDto(Feature feature){
        this(feature.getId(), feature.getIcon(), feature.getDescription());
    }

    public Feature toFeatureModel(){
        Feature featureModel = new Feature();
        BeanUtils.copyProperties(this, featureModel);
        return featureModel;
    }
}
