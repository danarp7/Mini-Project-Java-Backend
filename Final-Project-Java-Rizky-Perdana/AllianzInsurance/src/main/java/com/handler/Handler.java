package com.handler;

import com.model.User;
import com.repository.AllianzUser_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Handler {
    SqlSession session = null;
    AllianzUser_mapper allianzUser_mapper = null;

    public void readConfig() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(AllianzUser_mapper.class);

        allianzUser_mapper = session.getMapper(AllianzUser_mapper.class);
    }

    public boolean isPolisNumberExist(String polis_number) throws IOException {
        readConfig();
        User temp = allianzUser_mapper.getByPolisNumber(polis_number);
        return temp != null;
    }
}
