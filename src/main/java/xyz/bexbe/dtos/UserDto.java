package xyz.bexbe.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String abrName;

    private String role;
}
