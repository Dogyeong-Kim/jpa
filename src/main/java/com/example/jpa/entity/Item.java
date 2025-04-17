package com.example.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(value = AuditingEntityListener.class)

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {
    // id, item_nm, price, stock_number, item_detail, item_sell_status, reg_time,
    // update_time
    // 상품아이디, 상풉명, 가격, 재고수량, 상세설명, 판매상태, 등록시간, 수정시간
    // 판매상태 : SELL, SOLD_OUT 만 가능
    // 상품아이디는 자동증가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item_nm;

    private int price;

    private int stock_number;

    private String item_detail;

    private Status item_sell_status;

    @CreatedDate
    private LocalDateTime reg_time;

    @UpdateTimestamp
    private LocalDateTime update_time;

    public enum Status {
        SELL, SOLD_OUT
    }
}
