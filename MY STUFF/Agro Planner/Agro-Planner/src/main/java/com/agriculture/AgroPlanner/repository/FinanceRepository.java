package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static com.agriculture.AgroPlanner.constants.ColumnNames.USER_USERID;
import static com.agriculture.AgroPlanner.constants.Queries.RETRIEVE_RECORDS_BY_USERID;


@Repository
public interface FinanceRepository extends JpaRepository<FinancialRecord, Long> {
    @Query(RETRIEVE_RECORDS_BY_USERID)
    Page<FinancialRecord> findAllUserRecords(Pageable pageable, @Param(USER_USERID) Long userID);
}

