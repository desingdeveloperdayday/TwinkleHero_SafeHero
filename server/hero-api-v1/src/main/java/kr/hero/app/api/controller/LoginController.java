package kr.hero.app.api.controller;


import kr.hero.app.api.model.CertificationDTO;
import kr.hero.app.api.model.MessageDTO;
import kr.hero.app.api.model.ProtectorDTO;
import kr.hero.app.api.model.User;
import kr.hero.app.api.service.CertService;
import kr.hero.app.api.service.IndexService;
import kr.hero.app.api.service.MessageService;
import kr.hero.app.api.service.ProtectorService;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CertService certService;

    @Autowired
    IndexService indexService;

    @Autowired
    ProtectorService protectorService;

    @Autowired
    MessageService messageService;


    /*
     * sms 인증번호 보내기 api
     * param : memPhone (회원 전화번호)
     * result : sms send 결과값 , temp_certification DB insert 결과값
     *
     * */
    @PostMapping("/smsSend")
    @ResponseBody
    public Map<String, Object> smsSend(@RequestBody Map<String, Object> paramMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        String certMemPhone = (String)paramMap.get("certMemPhone");

        //인증번호 6자리 생성
        int certNumLength = 6;
        Random random = new Random(System.currentTimeMillis());

        int range = (int)Math.pow(10, certNumLength);
        int trim = (int)Math.pow(10, certNumLength - 1) ;
        int resultRandomNum = random.nextInt(range)+trim;

        if(resultRandomNum>range){
            resultRandomNum = resultRandomNum - trim;
        }

        String centNumber = String.valueOf(resultRandomNum);
        System.out.println("centNumber : " + centNumber);

        //인증번호 sms 보내기
		String api_key = "NCSYDCZMIXRNNHE7";
		String api_secret = "RZBEMZMS4WDQ5RCNYGUGUJ4W6KJHETNE";

		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> setSmsInfo = new HashMap<String, String>();
		setSmsInfo.put("to", certMemPhone); // 수신번호
		//setSmsInfo.put("to", "01047391294"); // 수신번호 test
		setSmsInfo.put("from", "01047391294"); // 발신번호
		setSmsInfo.put("text", "히어로 회원 인증번호는 " + centNumber + " 입니다."); // 문자내용
		setSmsInfo.put("type", "SMS"); // 문자 타입
		setSmsInfo.put("app_version", "test app 1.2"); // application name and version
		System.out.println(setSmsInfo);


        // 보내기&전송결과받기
		try {
			JSONObject smsResult = (JSONObject) coolsms.send(setSmsInfo);

			//인증번호 발행시간
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentTime = new Date();
			String smsSendTime = dateFormat.format(currentTime);

            //인증번호 Info Map
            Map<String, Object> certInfoMap = new HashMap<>();
            certInfoMap.put("certNum", centNumber);
            certInfoMap.put("certDate", smsSendTime);
            certInfoMap.put("certMemPhone", certMemPhone);

			System.out.println(centNumber + ", " +smsSendTime +" , " +certMemPhone);

			//인증번호 DB 저장
            Integer resultCertIn = certService.insertCert(certInfoMap);



			resultMap.put("result", smsResult);
			resultMap.put("message", resultCertIn);

			//System.out.println(smsResult.toString());

		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}


        return resultMap;
    }


    /*
     * sms 인증번호 확인 api (POST)
     * param : memPhone (회원번호)
     * param : certNum (인증번호)
     * result : 인증번호 확인 결과값 , temp_certification DB delete 결과값
     *
     * */

    @PostMapping("/smsConfirm")
    @ResponseBody
    public Map<String, Object> smsCertNumConfirm(@RequestBody Map<String, Object> paramMap){

        Map<String, Object> resultMap = new HashMap<>();
        String paramCertMemPhone = (String)paramMap.get("certMemPhone");
        String paramCertNum = (String)paramMap.get("certNum");

        //parameter null check
        if((paramCertMemPhone == null && paramCertMemPhone.equals(""))
            && (paramCertNum == null && paramCertNum.equals(""))){
            resultMap.put("message", "잘못된 파라미터 값");
            return resultMap;
        }


        //핸즈폰 번호로 인증정보 가져오기
        CertificationDTO certDTO = certService.selectCertByMemPhone(paramCertMemPhone);

        String certDateStr = certDTO.getCertDate();
		System.out.println(certDateStr);

		//인증시간 3분체크
        try{
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //인증밸행시간
            Date certDate = dateFormat.parse(certDateStr);
            long certDateTime = certDate.getTime();
            System.out.println("인증발행시간 DB값 : "+certDateTime);

            //현재시간
            currentDate = dateFormat.parse(dateFormat.format(currentDate));
            long currentDateTime = currentDate.getTime();
            System.out.println("현재시간 : " + certDateTime);

            //분으로 표현
            int checkMinute = (int)((currentDateTime - certDateTime)/60000);
            System.out.println("시간차 : " + checkMinute);

            //인증번호 시간(3분) 체크
            if(checkMinute < 3){
                String certNum = certDTO.getCertNum();

                //인증번호 체크
                if(paramCertNum.equals(certNum)){
                    //인증성공
                    resultMap.put("result","success");
                    //인증테이블 삭제
                    Integer resultCertDe = certService.deleteCertByMemPhone(paramCertMemPhone);
                    resultMap.put("message", resultCertDe);

                }else{
                    //인증실패
                    resultMap.put("result","인증번호가 맞질 않습니다.");
                }

            }else{
                //인증시간 초과 실패
                resultMap.put("result","인증시간 초과");

                //인증테이블 삭제
                Integer resultCertDe = certService.deleteCertByMemPhone(paramCertMemPhone);
                resultMap.put("message", resultCertDe);
            }

        }catch (ParseException e){
            e.printStackTrace();
        }

        return resultMap;
    }



    /*
     * 로그인 api
     *
     * param : memName(회원이름)
     * param : memPhone(회원이름)
     * param : protectorName_1(보호자_1 이름)
     * param : protectorPhone_1(보호자_1 전화번호)
     *
     * param(null 가능) : protectorName_2(보호자 이름)
     * param(null 가능) : protectorPhone_2(보호자 전화)
     * param(null 가능) : protectorName_3(보호자 이름)
     * param(null 가능) : protectorPhone_3(보호자 전화)
     * param(null가능) : messageContents(긴급메시지)
     *
     * */
    @PostMapping("/userLogin")
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody Map<String, Object> paramMap){

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginDate = dateFormat.format(currentDate);

        paramMap.put("memRegtime", loginDate);

        User user = new User();
        ProtectorDTO protectorDTO = new ProtectorDTO();

        //필수 param null check  (memName, memPhone, memRegtime, protectorName_1)
        if((paramMap.get("memName") != null && !paramMap.get("memName").equals(""))
                && (paramMap.get("memPhone") != null && !paramMap.get("memPhone").equals(""))
                && (paramMap.get("protectorName_1") != null && !paramMap.get("protectorName_1").equals(""))
                && (paramMap.get("protectorPhone_1") != null && !paramMap.get("protectorPhone_1").equals(""))){

            //회원테이블 insert

            user.setMemName((String)paramMap.get("memName"));
            user.setMemPhone((String)paramMap.get("memPhone"));
            user.setMemRegtime((String)paramMap.get("memRegtime"));
            user.setMemProtectorNum(1);

            Integer resultMemberIn = indexService.insertUser(user);
            resultMap.put("memIdx", user.getMemIdx()); //memIdx 반환

            //보호자_1 insert
            protectorDTO.setProtectorName((String)paramMap.get("protectorName_1"));
            protectorDTO.setProtectorPhone((String)paramMap.get("protectorPhone_1"));
            protectorDTO.setProtectorOrder(1);
            protectorDTO.setProtectorMemIdx(user.getMemIdx());

            //Integer resultProtector1_In = protectorService.insertProtector(protectorDTO);
            protectorService.insertProtector(protectorDTO);

        }else{
            resultMap.put("message","잘못된 파라미터 값");
            return resultMap;
        }


        if((paramMap.get("protectorName_2") != null && !paramMap.get("protectorName_2").equals(""))
                && (paramMap.get("protectorPhone_2") != null && !paramMap.get("protectorPhone_2").equals("")) ){

            //보호자_2 insert
            protectorDTO.setProtectorName((String)paramMap.get("protectorName_2"));
            protectorDTO.setProtectorPhone((String)paramMap.get("protectorPhone_2"));
            protectorDTO.setProtectorOrder(2);
            protectorDTO.setProtectorMemIdx(user.getMemIdx());

            protectorService.insertProtector(protectorDTO);

            //보호자 개수 증가
            user.setMemProtectorNum(2);
        }

        if((paramMap.get("protectorName_3") != null && !paramMap.get("protectorName_3").equals(""))
                && (paramMap.get("protectorPhone_3") != null && !paramMap.get("protectorPhone_3").equals("")) ) {

            //보호자_3 insert
            protectorDTO.setProtectorName((String)paramMap.get("protectorName_3"));
            protectorDTO.setProtectorPhone((String)paramMap.get("protectorPhone_3"));
            protectorDTO.setProtectorOrder(3);
            protectorDTO.setProtectorMemIdx(user.getMemIdx());

            protectorService.insertProtector(protectorDTO);

            //보호자 개수 증가
            user.setMemProtectorNum(3);
        }


        //긴급문자메세지테이블 insert
        MessageDTO messageDTO = new MessageDTO();
        if(paramMap.get("messageContents") != null && !paramMap.get("messageContents").equals("")) {

            messageDTO.setMessageContents((String)paramMap.get("messageContents"));
            messageDTO.setMessageMemIdx(user.getMemIdx());

        }else{
            messageDTO.setMessageContents("긴급상황입니다.");
            messageDTO.setMessageMemIdx(user.getMemIdx());
        }

        messageService.insertMessage(messageDTO);
        indexService.updateMemberProtectorNum(user);

        resultMap.put("memProtectorNum", user.getMemProtectorNum());

        resultMap.put("message", "success");

        return resultMap;

    }

}
