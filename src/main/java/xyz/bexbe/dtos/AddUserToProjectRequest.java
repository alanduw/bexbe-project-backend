package xyz.bexbe.dtos;

import lombok.Data;

@Data
public class AddUserToProjectRequest {
    private String accessToken;

    private String userFirstName;

    private String userLastName;

    private String userRole;
}
