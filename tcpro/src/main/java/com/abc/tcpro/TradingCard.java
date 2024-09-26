package com.abc.tcpro;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tradingCards")
public class TradingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cardNo", columnDefinition = "BINARY(16)")
    private UUID cardNo;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private float value;

    // Getters and setters

    public UUID getCardNo() {
        return cardNo;
    }

    public void setCardNo(UUID cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}