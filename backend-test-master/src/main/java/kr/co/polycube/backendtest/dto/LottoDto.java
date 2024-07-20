package kr.co.polycube.backendtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LottoDto {

    private Long lottoId;
    private Long userId;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;

}
