package xyz.bexbe.dtos;

import lombok.Data;

@Data
public class CreateProjectResponse {
    private String accessToken;

    private UserDto owner;
}
