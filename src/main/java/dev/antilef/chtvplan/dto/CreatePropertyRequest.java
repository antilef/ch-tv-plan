package dev.antilef.chtvplan.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePropertyRequest {
    private String key;
    private String value;
}
