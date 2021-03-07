package com.handler;

import com.model.User;
import com.model.send.RekeningMutationSend;
import com.repository.User_mapper;
import com.repository.RekeningMutation_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.ArrayList;

public class Handler {
    SqlSession session = null;
    User_mapper user_mapper = null;
    RekeningMutation_mapper rekeningMutation_mapper = null;

    public void readConfig() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(User_mapper.class);
        session.getConfiguration().addMapper(RekeningMutation_mapper.class);

        user_mapper = session.getMapper(User_mapper.class);
        rekeningMutation_mapper = session.getMapper(RekeningMutation_mapper.class);
    }

    public boolean isUserRekeningExist(String rekening_number) throws IOException {
        readConfig();
        User temp = user_mapper.getByRekening(rekening_number);
        return temp != null;
    }

    public boolean isUserIDExist(String user_id) throws IOException {
        readConfig();
        User temp = user_mapper.getByUserID(user_id);
        return temp != null;
    }

    public boolean isUserStillLoggedIn(String user_id) throws IOException {
        readConfig();
        User temp = user_mapper.getByUserID(user_id);
        return temp.getM_session_active();
    }

    public boolean isRekeningMutationNotNull(String rekening_number, Date from_date_mutation,
                                             Date to_date_mutation) throws IOException {
        readConfig();
        ArrayList<RekeningMutationSend> temp = rekeningMutation_mapper.getRekeningMutation(rekening_number,
                                                                                            from_date_mutation,
                                                                                            to_date_mutation);
        return temp != null;
    }
}
