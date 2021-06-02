package org.zerock.simpletodo.v1.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.simpletodo.v1.dto.PageRequestDTO;
import org.zerock.simpletodo.v1.entity.QTodo;
import org.zerock.simpletodo.v1.entity.Todo;

import java.util.List;

@Log4j2
public class TodoSearchRepositoryImpl extends QuerydslRepositorySupport implements TodoSearchRepository{


    public TodoSearchRepositoryImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> searchPage(PageRequestDTO pageRequestDTO) {

        log.info("TodoSearchRepositoryImpl searchPage");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = todo.tno.gt(0L);

        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        booleanBuilder.and(expression);

        if(type != null) {
            String[] typeArr = type.split("");
            //검색 조건을 작성하기
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(todo.title.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(todo.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("tno").descending());
        //order by
        Sort sort = pageable.getSort();

        query.where(booleanBuilder);
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        log.info(query);

        List<Todo> result = query.fetch();

        long count = query.fetchCount();


        return new PageImpl<Todo>(
                result,
                pageable,
                count);

    }
}
