package org.zerock.simpletodo.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.simpletodo.v1.entity.Todo;
import org.zerock.simpletodo.v1.repository.search.TodoSearchRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearchRepository {

}
