package com.jcarter.jsmms.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter @Setter

@ToString
@Builder(toBuilder = true)
public class Material {
    @Id
    private int materialId;
    private String description;
    private String type;
}
