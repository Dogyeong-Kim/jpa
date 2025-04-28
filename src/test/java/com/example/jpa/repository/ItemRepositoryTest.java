package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.Item.Status;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

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
