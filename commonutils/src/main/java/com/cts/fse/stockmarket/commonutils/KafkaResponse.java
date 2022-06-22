package com.cts.fse.stockmarket.commonutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaResponse<T> {

    private String message;
    private Object result; 
}
