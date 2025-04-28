package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.QItem;
import com.example.jpa.entity.Item.Status;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void queryDslTest() {
        // where item_nm = 'item2'
        QItem item = QItem.item;
        // System.out.println(itemRepository.findAll(item.item_nm.eq("item2")));

        // where item_nm like 'item2%'
        System.out.println(itemRepository.findAll(item.item_nm.startsWith("item2")));

        // where item_nm like '%item2'
        System.out.println(itemRepository.findAll(item.item_nm.endsWith("item2")));

        // where item_nm like '%item2%'
        System.out.println(itemRepository.findAll(item.item_nm.contains("item2")));

        // where item_nm = 'item2' and price > 1000
        System.out.println(itemRepository.findAll(item.item_nm.eq("item2")
                .and(item.price.gt(1000L))));

        // where item_nm = 'item2' and price >= 1000
        System.out.println(itemRepository.findAll(item.item_nm.eq("item2")
                .and(item.price.goe(1000L))));

        // where item_nm like '%item2%' or itemSellStatus = SOLD_OUT
        System.out.println(itemRepository.findAll(item.item_nm.contains("item2")
                .or(item.item_sell_status.eq(Item.Status.SOLD_OUT))));

        // where stockNumber >= 30
        System.out.println(itemRepository.findAll(item.stock_number.goe(30)));

        // where price < 35000
        System.out.println(itemRepository.findAll(item.price.lt(35000)));

        // 조건 : BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();
        // where item_nm = 'item2' and price > 1000
        builder.and(item.item_nm.eq("item2"));
        builder.and(item.price.gt(1000));
        System.out.println(itemRepository.findAll(builder));
    }

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Item item = Item.builder()
                    .item_nm("item" + i)
                    .price(i * 2000)
                    .stock_number(i + 10)
                    .item_detail("Item_Detail" + i)
                    .item_sell_status(Status.SELL)
                    .build();
            itemRepository.save(item);
        });
    }

    @Test
    public void aggreateTest() {
        List<Object[]> result = itemRepository.aggreate();

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            System.out.println("아이템 수 : " + objects[0]);
            System.out.println("아이템 가격 합 : " + objects[1]);
            System.out.println("아이템 가격 평균 : " + objects[2]);
            System.out.println("아이템 가격 최대값 : " + objects[3]);
            System.out.println("아이템 가격 최소값 : " + objects[4]);
        }
    }
}
