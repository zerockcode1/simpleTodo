package org.zerock.simpletodo.v1.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.simpletodo.v1.dto.PageRequestDTO;
import org.zerock.simpletodo.v1.entity.Todo;

public interface TodoSearchRepository {

    public Page<Todo> searchPage(PageRequestDTO pageRequestDTO);

}
