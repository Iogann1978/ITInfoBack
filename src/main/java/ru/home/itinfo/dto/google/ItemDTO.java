package ru.home.itinfo.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {
    @JsonProperty
    private VolumeInfoDTO volumeInfo;
}
