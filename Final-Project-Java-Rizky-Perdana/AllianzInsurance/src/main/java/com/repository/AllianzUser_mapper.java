package com.repository;

import com.model.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public interface AllianzUser_mapper {
    final String getByPolisNumber = "SELECT * FROM user WHERE polis_number = #{polis_number}";
    final String insertTranx = "INSERT INTO history_tranx (date_tranx, time_tranx, polis_number, nominal, grade) " +
                            "VALUES (#{date_tranx}, #{time_tranx}, #{polis_number}, #{nominal}, #{grade})";
//    final String insertTranx = "INSERT INTO history_tranx (polis_number, nominal, grade) " +
//            "VALUES (#{polis_number}, #{nominal}, #{grade})";

    @Select(getByPolisNumber)
    @Results(value = {
            @Result(property = "full_name", column = "full_name"),
            @Result(property = "nik", column = "nik"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "birth_date", column = "birth_date"),
            @Result(property = "hp_number", column = "hp_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "polis_number", column = "polis_number"),
            @Result(property = "grade", column = "grade")
    })
    User getByPolisNumber(String polis_number);

    @Insert(insertTranx)
    void insertTranx(@Param("date_tranx") Date date_tranx,
                  @Param("time_tranx") Time time_tranx,
                  @Param("polis_number") String polis_number,
                  @Param("nominal") BigDecimal nominal,
                  @Param("grade") String grade);

//    @Insert(insertTranx)
////    @Options(useGeneratedKeys = true, keyProperty = "id_user")
//    void insertTranx(@Param("polis_number") String polis_number,
//                     @Param("nominal") BigDecimal nominal,
//                     @Param("grade") String grade);
}
