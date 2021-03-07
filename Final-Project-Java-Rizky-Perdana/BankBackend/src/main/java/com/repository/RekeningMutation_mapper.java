package com.repository;

import com.model.send.RekeningMutationSend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.ArrayList;

public interface RekeningMutation_mapper {
    final String getRekeningMutation = "SELECT * FROM history_tranx WHERE (rekening_number = #{rekening_number})" +
                                        " AND (date_tranx BETWEEN #{from_date_mutation} AND #{to_date_mutation})" +
                                        "ORDER BY date_tranx ASC, time_tranx ASC";

    @Select(getRekeningMutation)
    @Results(value = {
            @Result(property = "date_tranx", column = "date_tranx"),
            @Result(property = "nominal", column = "nominal"),
            @Result(property = "type_tranx", column = "type_tranx"),
            @Result(property = "description", column = "description")
    })
    ArrayList<RekeningMutationSend> getRekeningMutation(@Param("rekening_number") String rekening_number,
                                                        @Param("from_date_mutation") Date from_date_mutation,
                                                        @Param("to_date_mutation") Date to_date_mutation);
}
