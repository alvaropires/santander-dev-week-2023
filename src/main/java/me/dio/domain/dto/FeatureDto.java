package me.dio.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.Feature;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDto {
    private UUID id;

    @JsonView(UserDto.View.RegistrationPost.class)
    private String icon;

    @JsonView(UserDto.View.RegistrationPost.class)
    private String description;

    public Feature toFeatureModel(){
        var feature = new Feature();
        BeanUtils.copyProperties(this, feature);
        return feature;
    }
    public FeatureDto(Feature feature){
        BeanUtils.copyProperties(feature, this);
    }
}
