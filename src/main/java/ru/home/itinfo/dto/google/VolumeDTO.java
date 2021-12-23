package ru.home.itinfo.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VolumeDTO {
    @JsonProperty
    private List<ItemDTO> items;
}
