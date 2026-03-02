package xyz.bexbe.dtos;

import lombok.Data;

@Data
public class CreateProjectRequest {
    private boolean useSeed;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerRole;
}
