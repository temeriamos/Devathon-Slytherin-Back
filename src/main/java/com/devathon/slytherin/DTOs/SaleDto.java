package com.devathon.slytherin.DTOs;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for the creation of a sale")
public class SaleDto {
    private String user_id;
    private Date date;
    private Integer total_galeon;
    private Integer total_sickle;   
    private Integer total_knut;
}
