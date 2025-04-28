package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("Select count(j), sum(j.price), avg(j.price), max(j.price), min(j.price) from Item j")
    List<Object[]> aggreate();
}
