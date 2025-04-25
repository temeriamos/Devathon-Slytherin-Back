package com.devathon.slytherin.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MagicObjectPaginatorResponseDto(
        @JsonProperty("magic_objects")
        List<MagicObjectResponseDto> magicObjectResponseDtos,
        int page,
        int size
) {
}
