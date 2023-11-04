package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static com.agriculture.AgroPlanner.constants.ColumnNames.TASK_STATUS;
import static com.agriculture.AgroPlanner.constants.ColumnNames.USER_USERID;
import static com.agriculture.AgroPlanner.constants.Queries.RETRIEVE_TASKS_BY_USERID;
import static com.agriculture.AgroPlanner.constants.Queries.RETRIEVE_TASKS_BY_USERID_AND_STATUS;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(RETRIEVE_TASKS_BY_USERID)
    Page<Task> findAllUserTasks(Pageable pageable,
                                @Param(USER_USERID)
                                Long userID);

    @Query(RETRIEVE_TASKS_BY_USERID_AND_STATUS)
    Page<Task> findAllUserTasksByType(Pageable pageable,
                                      @Param(USER_USERID) Long userID,
                                      @Param(TASK_STATUS) String status);
}
