package com.method;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Method {
    public String getVerifyCode() {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String values = Capital_chars + Small_chars + numbers;

        Random rndm_method = new Random();

        char[] password = new char[10];

        for (int i = 0; i < 10; i++) {
            password[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return String.valueOf(password);
    }

    public String getOTP() {
        String numbers = "0123456789";

        Random rndm_method = new Random();

        char[] otp = new char[6];

        for (int i = 0; i < 6; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }

        return String.valueOf(otp);
    }

    public long getDiffTimeSec(Date dDateInput1, Time tTimeInput1,
                               Date dDateInput2, Time tTimeInput2) throws ParseException {

        String date1 = dDateInput1.toString();
        String date2 = dDateInput2.toString();
        String time1 = tTimeInput1.toString();
        String time2 = tTimeInput2.toString();

        String format = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        java.util.Date dateObj1 = sdf.parse(date1 + " " + time1);
        java.util.Date dateObj2 = sdf.parse(date2 + " " + time2);

        long diff = dateObj2.getTime() - dateObj1.getTime();
        long diffsec = diff / 1000;

        return diffsec;
    }

    public BigDecimal tranx(BigDecimal balance, BigDecimal tranx) {
        BigDecimal tempBalance = balance.subtract(tranx);
        return tempBalance;
    }
}
