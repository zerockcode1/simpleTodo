package org.zerock.simpletodo.v1.service;

import org.zerock.simpletodo.v1.dto.PageRequestDTO;
import org.zerock.simpletodo.v1.dto.PageResultDTO;
import org.zerock.simpletodo.v1.dto.TodoDTO;
import org.zerock.simpletodo.v1.entity.Todo;

import java.util.List;

public interface TodoService {

    Long register(TodoDTO todoDTO);

    TodoDTO get(Long tno);

    void modify(TodoDTO todoDTO);

    void remove(Long tno);

    //PageResultDTO<TodoDTO, Todo> getPageList(int page);

    PageResultDTO<TodoDTO, Todo> getPageList(PageRequestDTO pageRequestDTO);


    default Todo dtoToEntity(TodoDTO dto){

        Todo todo = Todo.builder()
                .tno(dto.getTno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .regDate(dto.getRegDate())
                .modDate(dto.getModDate())
                .build();

        return todo;
    }

    default TodoDTO entityToDTO(Todo entity){

        TodoDTO dto = TodoDTO.builder()
                .tno(entity.getTno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;

    }

}
