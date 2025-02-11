package com.ClassExchange.uc.manter_campus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusRequest{
    private String name;
    private String sigla;
    private String endereco;


}
