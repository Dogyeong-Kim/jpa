package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.Memo;
import com.example.jpa.entity.QBoard;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryDslTest() {

        // Q 파일 사용
        QBoard board = QBoard.board;

        // where b.title = 'title1'
        // System.out.println(boardRepository.findAll(board.title.eq("board title1")));

        // where b.title like 'title1%'
        // System.out.println(boardRepository.findAll(board.title.startsWith("board")));

        // where b.title like '%title1'
        // System.out.println(boardRepository.findAll(board.title.endsWith("title1")));

        // where b.title like '%title1%'
        // System.out.println(boardRepository.findAll(board.title.contains("title1")));

        // where b.title like '%title1%' and b.bno > 0 order by bno desc
        // Iterable<Board> boards =
        // boardRepository.findAll(board.title.contains("title1")
        // .and(board.bno.gt(0L)), Sort.by("bno").descending());
        // System.out.println(boards);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(board.bno.gt(0L), pageable);

        System.out.println("page size " + result.getSize());
        System.out.println("page TotalPage " + result.getTotalPages());
        System.out.println("page TotalElememts " + result.getTotalElements());
        System.out.println("page content " + result.getContent());
    }

    @Test
    public void queryMethodTest() {

        // System.out.println(boardRepository.findByWriter("writer4"));
        // System.out.println(boardRepository.findByTitle("title1"));
        // System.out.println(boardRepository.findByWriterStartingWith("writer"));
        // writer%
        // System.out.println(boardRepository.findByWriterEndingWith("writer"));
        // %writer
        // System.out.println(boardRepository.findByWriterContaining("writer"));
        // %writer%

        // System.out.println(boardRepository.findByWriterContainingOrContentContaining("5",
        // "9"));
        // System.out.println(boardRepository.findByWriterContainingAndContentContaining("5",
        // "9"));
        // System.out.println(boardRepository.findByBnoGreaterThan(5L));
        // System.out.println(boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L));
        // System.out.println(boardRepository.findByBnoBetween(5L, 10L));

        List<Object[]> result = boardRepository.findByTitle2("title");
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            String title = (String) objects[0];
            String writer = (String) objects[1];
            System.out.println("title : " + title + " writer : " + writer);
            System.out.println("=========================");
        }

    }

    // CRUD
    @Test
    public void insertTest() {
        LongStream.rangeClosed(0, 100).forEach(i -> {
            Board board = Board.builder().writer("writer" + i).title("title" + i)
                    .content("content" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {
        Board board = boardRepository.findById(1L).get();
        board.setWriter("writer Update");
        board.setTitle("title Update");
        board.setContent("content Update");
        boardRepository.save(board);
    }

    @Test
    public void readTest() {
        Board board = boardRepository.findById(1L).get();
        System.out.println(board);
    }

    @Test
    public void listTest() {
        // boardRepository.findAll().forEach(board -> System.out.println(board));

        // pageNumber : 0 => 1page
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.findAll(pageable).forEach(board -> System.out.println(board));
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(11L);
    }
}
