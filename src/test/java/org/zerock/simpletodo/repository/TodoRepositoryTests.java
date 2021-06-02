package org.zerock.simpletodo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.simpletodo.v1.dto.PageRequestDTO;
import org.zerock.simpletodo.v1.dto.PageResultDTO;
import org.zerock.simpletodo.v1.dto.TodoDTO;
import org.zerock.simpletodo.v1.entity.Todo;
import org.zerock.simpletodo.v1.repository.TodoRepository;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {


    @Autowired
    private TodoRepository repository;


    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {

            //log.info("Todo..." + i);

            Todo todo = Todo.builder().title("Sample..." +i).content("Content..."+i).build();

            repository.save(todo);

            log.info("TODO: " + i);

        });
    }

    @Test
    public void testRead() {

        Long tno = 100L;

        Todo todo = repository.findById(tno).get();

        log.info(todo);

    }

    @Test
    public void testModify() {

        Todo todo = repository.findById(100L).get();

        todo.changeTitle("Update 100 Title");
        todo.changeContent("Update 100 Content...");

        repository.save(todo);

    }

    @Test
    public void testRemove(){

        repository.deleteById(1L);

    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());

        Page<Todo> result = repository.findAll(pageable);

        result.get().forEach(todo -> log.info(todo));
    }

    /////////////////////////////////////////Search Querydsl

    @Test
    public void testSearch1() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();


        Page<Todo> result = repository.searchPage(pageRequestDTO);

        result.get().forEach(todo -> {
            log.info(todo);
        });

    }
}















