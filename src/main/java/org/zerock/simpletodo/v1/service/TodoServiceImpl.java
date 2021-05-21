package org.zerock.simpletodo.v1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.simpletodo.v1.dto.PageResultDTO;
import org.zerock.simpletodo.v1.dto.TodoDTO;
import org.zerock.simpletodo.v1.entity.Todo;
import org.zerock.simpletodo.v1.repository.TodoRepository;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService{


    private final TodoRepository repository;

    @Override
    public Long register(TodoDTO todoDTO) {

        Todo todo = dtoToEntity(todoDTO);

        log.info(todo);

        repository.save(todo);

        return todo.getTno();
    }

    @Override
    public TodoDTO get(Long tno) {

        Todo todo = repository.findById(tno).get();

        log.info(todo);

        return entityToDTO(todo);

    }

    @Override
    public void modify(TodoDTO todoDTO) {

        Todo todo = dtoToEntity(todoDTO);

        repository.save(todo);
    }

    @Override
    public void remove(Long tno) {

        repository.deleteById(tno);
    }

    @Override
    public PageResultDTO<TodoDTO, Todo> getPageList(int page) {

        Pageable pageable =
                PageRequest.of( (page<=0 ? 0 : page) -1, 10, Sort.by("tno").descending());

        Page<Todo> result = repository.findAll(pageable);

        Function<Todo, TodoDTO> fn = (en -> entityToDTO(en));

        return new PageResultDTO<>(result, fn);
    }
}
