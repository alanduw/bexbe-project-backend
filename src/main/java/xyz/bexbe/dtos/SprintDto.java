package xyz.bexbe.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SprintDto {
    private Long id;

    private String name;

    private String start;

    private String end;

    private String goal;

    private List<Long> issues;
}
