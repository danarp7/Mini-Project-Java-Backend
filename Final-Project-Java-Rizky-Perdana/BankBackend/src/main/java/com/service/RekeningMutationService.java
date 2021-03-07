package com.service;

import com.google.gson.Gson;
import com.handler.Handler;
import com.model.send.RekeningMutationSend;
import com.model.User;
import com.repository.RekeningMutation_mapper;
import com.repository.User_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.ArrayList;

public class RekeningMutationService {
    SqlSession session = null;
    User_mapper user_mapper = null;
    RekeningMutation_mapper rekeningMutation_mapper = null;

    Handler handler = new Handler();
    Gson gson = new Gson();

    public void readConfig() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(User_mapper.class);
        session.getConfiguration().addMapper(RekeningMutation_mapper.class);

        user_mapper = session.getMapper(User_mapper.class);
        rekeningMutation_mapper = session.getMapper(RekeningMutation_mapper.class);
    }

    public String rekeningMutation(String user_id, Date from_date_mutation,
                                   Date to_date_mutation, String pwd_tranx) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Rekening Mutation: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Rekening Mutation: Failed - Not Registered";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Rekening Mutation: Failed - User not logged in.";
            }

            if (!pwd_tranx.equals(temp.getPwd_tranx())) {
                session.commit();
                session.close();
                return "Rekening Mutation: Failed - Wrong Password";
            }

            if (!handler.isRekeningMutationNotNull(temp.getRekening_number(),
                                                    from_date_mutation, to_date_mutation)) {
                session.commit();
                session.close();
                return "Rekening Mutation: Failed - There is no mutation in that time period.";
            }

            ArrayList<RekeningMutationSend> tempMutation = rekeningMutation_mapper.getRekeningMutation(temp.getRekening_number(),
                    from_date_mutation, to_date_mutation);
            session.commit();
            session.close();
            String json = gson.toJson(tempMutation);
            return json;
        } else {
            session.commit();
            session.close();
            return "Rekening Mutation: Failed - User with user id " + user_id + " not found.";
        }
    }
}
