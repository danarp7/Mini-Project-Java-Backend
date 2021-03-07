package com.controller;

import com.broker.AppRecv;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.handler.Handler;
import com.model.request.*;
import com.method.Method;
import com.broker.AppSend;
import com.model.respone.*;
import com.util.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;


@RestController
@RequestMapping("/MBanking")
public class AppController {

    @Autowired
    AppSend appSend = new AppSend();

    @Autowired
    AppRecv appRecv = new AppRecv();

    Gson gson = new Gson();
    Gson gsonBuilder = new GsonBuilder().create();
    Method method = new Method();
    Handler handler = new Handler();

    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody ReqRegister reqRegister) throws Exception {
        if (!handler.userIDRegex(reqRegister.getUser_id())) {
            String msg = "Wrong regex. Please re-enter the user id.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        reqRegister.setDate_verify_code(sqlDate);
        reqRegister.setTime_verify_code(sqlTime);
        appSend.sendToDB(gson.toJson(reqRegister));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResRegister register = gsonBuilder.fromJson(respone, ResRegister.class);
            return (new ResponseEntity<>(register, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/Verification")
    public ResponseEntity<?> verification(@RequestBody ReqVerification reqVerification) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        reqVerification.setDate_verify_code(sqlDate);
        reqVerification.setTime_verify_code(sqlTime);
        appSend.sendToDB(gson.toJson(reqVerification));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/Activation")
    public ResponseEntity<?> activation(@RequestBody ReqActivation reqAktivasi) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        reqAktivasi.setDate_otp(sqlDate);
        reqAktivasi.setTime_otp(sqlTime);
        appSend.sendToDB(gson.toJson(reqAktivasi));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResActivation activation = gsonBuilder.fromJson(respone, ResActivation.class);
            return (new ResponseEntity<>(activation, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/OTPActivation")
    public ResponseEntity<?> otpActivation(@RequestBody ReqOTPActivation reqOTPActivation) throws Exception {
        if (!handler.otpRegex(reqOTPActivation.getOtp())) {
            String msg = "Wrong regex. Please re-enter the mobile PIN.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        if (!handler.mPINRegex(reqOTPActivation.getM_pin())) {
            String msg = "Wrong regex. Please re-enter the mobile PIN.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        if (!handler.pwdTranxRegex(reqOTPActivation.getPwd_tranx())) {
            String msg = "Wrong regex. Please re-enter the password transaction.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        reqOTPActivation.setDate_otp(sqlDate);
        reqOTPActivation.setTime_otp(sqlTime);

        appSend.sendToDB(gson.toJson(reqOTPActivation));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @GetMapping("/SeeProfile")
    public ResponseEntity<?> seeProfile(@RequestBody ReqSeeProfile reqSeeProfile) throws Exception {
        appSend.sendToDB(gson.toJson(reqSeeProfile));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResSeeProfile seeProfile = gsonBuilder.fromJson(respone, ResSeeProfile.class);
            return (new ResponseEntity<>(seeProfile, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/ChangePwdTranx")
    public ResponseEntity<?> changePwdTranx(@RequestBody ReqChangePwdTranx reqChangePwdTranx) throws Exception {
        if (!handler.pwdTranxRegex(reqChangePwdTranx.getNew_pwd_tranx())) {
            String msg = "Wrong regex. Please re-enter the new password transaction.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        appSend.sendToDB(gson.toJson(reqChangePwdTranx));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/ChangeMPIN")
    public ResponseEntity<?> changeMPIN(@RequestBody ReqChangeMPIN reqChangeMPIN) throws Exception {
        if (!handler.mPINRegex(reqChangeMPIN.getNew_m_pin())) {
            String msg = "Wrong regex. Please re-enter the new mobile PIN.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        appSend.sendToDB(gson.toJson(reqChangeMPIN));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody ReqLogin reqLogin) throws Exception {
        if (!handler.mPINRegex(reqLogin.getM_pin())) {
            String msg = "Wrong regex. Please re-enter the mobile PIN.";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        appSend.sendToDB(gson.toJson(reqLogin));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/Logout")
    public ResponseEntity<?> logout(@RequestBody ReqLogout reqLogout) throws Exception {
        appSend.sendToDB(gson.toJson(reqLogout));
        String respone = appRecv.recvFromDB();

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @GetMapping("/BalanceInfo")
    public ResponseEntity<?> balanceInfo(@RequestBody ReqBalanceInfo reqBalanceInfo) throws Exception {
        appSend.sendToDB(gson.toJson(reqBalanceInfo));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResBalanceInfo balance = gsonBuilder.fromJson(respone, ResBalanceInfo.class);
            return (new ResponseEntity<>(balance, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @GetMapping("/RekeningMutation")
    public ResponseEntity<?> rekeningMutation(@RequestBody ReqRekeningMutation reqRekeningMutation) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

        long diffTime1 = method.getDiffTimeDays1(reqRekeningMutation.getFrom_date_mutation(), sqlDate,
                                            sqlTime);

        long diffTime2 = method.getDiffTimeDays1(reqRekeningMutation.getTo_date_mutation(), sqlDate,
                sqlTime);

        long diffTime3 = method.getDiffTimeDays2(reqRekeningMutation.getFrom_date_mutation(),
                reqRekeningMutation.getTo_date_mutation());

        if (diffTime1 < 0) {
            String msg = "Sorry, please re-enter the first date. The first date must " + "" +
                    "not be past the current date";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        if (diffTime2 < 0) {
            String msg = "Sorry, please re-enter the second date. The second date must " + "" +
                    "not be past the current date";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        if (diffTime3 < 0) {
            String msg = "Sorry, please re-enter the first date. The first date must " + "" +
                    "not be past the second date";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        long limitTime1 = 30;
        long limitTime2 = 14;

        if (diffTime1 > limitTime1) {
            String msg = "Sorry, please re-enter the first date. The first date must " + "" +
                    "not be more than 30 days of the current date";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        if (diffTime3 > limitTime2) {
            String msg = "Sorry, please re-enter the first date and second date. The first date must " + "" +
                    "not be more than 14 days of the second date";
            return new ResponseEntity<>(new CustomMessage(msg), HttpStatus.OK);
        }

        appSend.sendToDB(gson.toJson(reqRekeningMutation));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{' || respone.charAt(0) == '[') {
            Type type = new TypeToken<List<ResRekeningMutation>>(){}.getType();
            List<ResRekeningMutation> rekeningMutation = gson.fromJson(respone, type);
            return (new ResponseEntity<>(rekeningMutation, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/Insurance")
    public ResponseEntity<?> insurance(@RequestBody ReqInsurance reqInsurance) throws Exception {
        appSend.sendToDB(gson.toJson(reqInsurance));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResInsurance insurance = gsonBuilder.fromJson(respone, ResInsurance.class);
            return (new ResponseEntity<>(insurance, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }

    @PostMapping("/InsurancePayment")
    public ResponseEntity<?> insurancePayment(@RequestBody ReqInsurancePayment reqInsurancePayment) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        reqInsurancePayment.setDate_insurance_payment(sqlDate);
        reqInsurancePayment.setTime_insurance_payment(sqlTime);

        appSend.sendToDB(gson.toJson(reqInsurancePayment));
        String respone = appRecv.recvFromDB();

        if (respone.charAt(0) == '{') {
            ResInsurancePayment insurancePayment = gsonBuilder.fromJson(respone, ResInsurancePayment.class);
            return (new ResponseEntity<>(insurancePayment, HttpStatus.OK));
        }

        return new ResponseEntity<>(new CustomMessage(respone), HttpStatus.OK);
    }
}

