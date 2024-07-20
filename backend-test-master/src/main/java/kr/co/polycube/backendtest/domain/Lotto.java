package kr.co.polycube.backendtest.domain;

import jakarta.persistence.*;
import lombok.Data;

import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "lotto")
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LottoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "number_1")
    private int number1;

    @Column(name = "number_2")
    private int number2;

    @Column(name = "number_3")
    private int number3;

    @Column(name = "number_4")
    private int number4;

    @Column(name = "number_5")
    private int number5;

    @Column(name = "number_6")
    private int number6;

}
