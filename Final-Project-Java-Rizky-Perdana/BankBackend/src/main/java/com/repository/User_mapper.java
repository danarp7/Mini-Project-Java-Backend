package com.repository;

import com.model.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public interface User_mapper {
    final String getByUserID = "SELECT * FROM user WHERE user_id = #{user_id}";
    final String getByRekening = "SELECT * FROM user WHERE rekening_number = #{rekening_number}";
    final String login = "UPDATE user SET m_session_active = true WHERE user_id = #{user_id}";
    final String logout = "UPDATE user SET m_session_active = false WHERE user_id = #{user_id}";
    final String register = "UPDATE user SET user_id = #{user_id}, verify_code = #{verify_code}, " +
                            "date_verify_code = #{date_verify_code}, time_verify_code = #{time_verify_code} " +
                            "WHERE rekening_number = #{rekening_number}";
    final String verification = "UPDATE user SET m_banking_status = #{m_banking_status} " +
                                "WHERE user_id = #{user_id}";
    final String activation = "UPDATE user SET otp = #{otp}, " +
                            "date_otp = #{date_otp}, time_otp = #{time_otp} " +
                            "WHERE user_id = #{user_id}";
    final String otpActivation = "UPDATE user SET m_pin = #{m_pin}, " +
                            "pwd_tranx = #{pwd_tranx}, m_banking_activation = #{m_banking_activation} " +
                            "WHERE user_id = #{user_id}";
    final String changeMPIN = "UPDATE user SET m_pin = #{m_pin} WHERE user_id = #{user_id}";
    final String changePwdTranx = "UPDATE user SET pwd_tranx = #{pwd_tranx} WHERE user_id = #{user_id}";
    final String tranx = "UPDATE user SET balance = #{balance} WHERE user_id = #{user_id}";
    final String insertTranx = "INSERT INTO history_tranx (date_tranx, time_tranx, rekening_number, nominal, type_tranx, description) " +
            "VALUES (#{date_tranx}, #{time_tranx}, #{rekening_number}, #{nominal}, #{type_tranx}, #{description})";

    @Select(getByUserID)
    @Results(value = {
            @Result(property = "full_name", column = "full_name"),
            @Result(property = "nik", column = "nik"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "birth_date", column = "birth_date"),
            @Result(property = "hp_number", column = "hp_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "dc_number", column = "dc_number"),
            @Result(property = "rek_number", column = "rek_number"),
            @Result(property = "pin_atm", column = "pin_atm"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "m_pin", column = "m_pin"),
            @Result(property = "pwd_tranx", column = "pwd_tranx"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "bank_status", column = "bank_status"),
            @Result(property = "m_banking_status", column = "m_banking_status"),
            @Result(property = "m_banking_activation", column = "m_banking_activation"),
            @Result(property = "m_session_active", column = "m_session_active"),
            @Result(property = "verify_code", column = "verify_code"),
            @Result(property = "date_verify_code", column = "date_verify_code"),
            @Result(property = "time_verifiy_code", column = "time_verifiy_code"),
            @Result(property = "otp", column = "otp"),
            @Result(property = "date_otp", column = "date_otp"),
            @Result(property = "time_otp", column = "time_otp")
    })
    User getByUserID(String user_id);

    @Select(getByRekening)
    @Results(value = {
            @Result(property = "full_name", column = "full_name"),
            @Result(property = "nik", column = "nik"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "birth_date", column = "birth_date"),
            @Result(property = "hp_number", column = "hp_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "dc_number", column = "dc_number"),
            @Result(property = "rek_number", column = "rek_number"),
            @Result(property = "pin_atm", column = "pin_atm"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "m_pin", column = "m_pin"),
            @Result(property = "pwd_tranx", column = "pwd_tranx"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "bank_status", column = "bank_status"),
            @Result(property = "m_banking_status", column = "m_banking_status"),
            @Result(property = "m_banking_activation", column = "m_banking_activation"),
            @Result(property = "m_session_active", column = "m_session_active"),
            @Result(property = "verify_code", column = "verify_code"),
            @Result(property = "date_verify_code", column = "date_verify_code"),
            @Result(property = "time_verifiy_code", column = "time_verifiy_code"),
            @Result(property = "otp", column = "otp"),
            @Result(property = "date_otp", column = "date_otp"),
            @Result(property = "time_otp", column = "time_otp")
    })
    User getByRekening(String rekening_number);

    @Update(login)
    void login(User user);

    @Update(logout)
    void logout(User user);

    @Update(register)
    void register(@Param("rekening_number") String rekening_number,
               @Param("user_id") String user_id,
               @Param("verify_code") String verify_code,
               @Param("date_verify_code") Date date_verify_code,
               @Param("time_verify_code") Time time_verify_code);

    @Update(verification)
    void verification(@Param("user_id") String user_id,
                      @Param("m_banking_status") String m_banking_status);

    @Update(activation)
    void activation(@Param("user_id") String user_id,
                  @Param("otp") String otp,
                  @Param("date_otp") Date date_otp,
                  @Param("time_otp") Time time_otp);

    @Update(otpActivation)
    void otpActivation(@Param("user_id") String user_id,
                    @Param("m_pin") String m_pin,
                    @Param("pwd_tranx") String pwd_tranx,
                    @Param("m_banking_activation") String m_banking_activation);

    @Update(changeMPIN)
    void changeMPIN(@Param("user_id") String user_id,
                       @Param("m_pin") String m_pin);

    @Update(changePwdTranx)
    void changePwdTranx(@Param("user_id") String user_id,
                    @Param("pwd_tranx") String pwd_tranx);

    @Update(tranx)
    void tranx(@Param("user_id") String user_id,
               @Param("balance") BigDecimal balance);

    @Insert(insertTranx)
//    @Options(useGeneratedKeys = true, keyProperty = "id_user")
    void insertTranx(@Param("date_tranx") Date date_tranx,
                     @Param("time_tranx") Time time_tranx,
                     @Param("rekening_number") String rekening_number,
                     @Param("nominal") BigDecimal nominal,
                     @Param("type_tranx") String type_tranx,
                     @Param("description") String description);
}
