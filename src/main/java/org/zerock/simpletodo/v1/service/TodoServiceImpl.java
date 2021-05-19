package org.zerock.simpletodo.v1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.simpletodo.v1.dto.TodoDTO;
import org.zerock.simpletodo.v1.repository.TodoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService{


    private final TodoRepository repository;

    @Override
    public Long register(TodoDTO todoDTO) {
        return null;
    }

    @Override
    public TodoDTO get(Long tno) {
        return null;
    }

    @Override
    public int modify(TodoDTO todoDTO) {
        return 0;
    }

    @Override
    public int remove(Long tno) {
        return 0;
    }

    @Override
    public List<TodoDTO> getPageList(int page) {
        return null;
    }
}
