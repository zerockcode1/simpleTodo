package org.zerock.simpletodo.v1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.simpletodo.v1.dto.PageResultDTO;
import org.zerock.simpletodo.v1.dto.TodoDTO;
import org.zerock.simpletodo.v1.entity.Todo;
import org.zerock.simpletodo.v1.service.TodoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@CrossOrigin( origins = {"*"})
@Log4j2
public class TodoController {

    private final TodoService service;

    @GetMapping("/now")
    public ResponseEntity<LocalDateTime> getNow() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(LocalDateTime.now(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody TodoDTO todoDTO){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("post........................" + todoDTO);

        Long tno  = service.register(todoDTO );

        return new ResponseEntity<>(tno, HttpStatus.OK);
    }

    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable("tno") Long tno){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TodoDTO todoDTO = service.get(tno);

        return new ResponseEntity<>(todoDTO , HttpStatus.OK);
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<String> remove(@RequestBody TodoDTO todoDTO) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("delete..............." + todoDTO);

        service.remove(todoDTO.getTno());

        return new ResponseEntity<>("success" , HttpStatus.OK);
    }

    @PutMapping("/{tno}")
    public ResponseEntity<String> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO){


        log.info("modify.........." + todoDTO);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.modify(todoDTO);

        return new ResponseEntity<>("success" , HttpStatus.OK);

    }

    @GetMapping("/pages")
    public ResponseEntity<PageResultDTO<TodoDTO,Todo>> getList(int page){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(service.getPageList(page), HttpStatus.OK);
    }

}
