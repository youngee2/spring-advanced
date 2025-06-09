package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    /* Lv2. n+1문제
                1) JPQL fetch join을 사용하여 n+1문제를 해결하고 있는 TodoRepository
                2) fetch join 대신 @EntityGraph기반의 구현으로 수정해주세요.

                @EntityGraph는 JPA에서 fetch join을 어노테이션으로 사용할 수 있도록 지원하는 기능.
             */

    //메서드 네이밍만으로 자동으로 쿼리가 작성.
    @EntityGraph(attributePaths = {"user"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
