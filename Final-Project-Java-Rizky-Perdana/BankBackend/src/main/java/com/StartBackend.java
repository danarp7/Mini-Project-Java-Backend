package com;

import com.broker.BERecv;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StartBackend {
    public static void main(String[] args) throws IOException, TimeoutException {
        BERecv beRecv = new BERecv();
        beRecv.recvFromAPI();
    }
}