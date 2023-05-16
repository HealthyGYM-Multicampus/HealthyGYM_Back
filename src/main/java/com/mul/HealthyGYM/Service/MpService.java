package com.mul.HealthyGYM.Service;

import com.mul.HealthyGYM.Dao.MpDao;
import com.mul.HealthyGYM.Dto.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@Transactional
public class MpService {

    /*Window Os*/
    public static String localPath =  "C:/upload/";

    /*Mac Os*/
    //public static String localPath = "/Users/admin/springboot_img/";

    private PasswordEncoder passwordEncoder;

    @Autowired
    public MpService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    MpDao dao;

    public MemberDto findMemberById(int memberseq) {
        MemberDto memberDto = dao.findMemberById(memberseq);
        return memberDto;
    }

    public MemberinfoDto findMemberinfoById(int memberseq) {
        MemberinfoDto memberinfoDto = dao.findMemberinfoById(memberseq);
        return memberinfoDto;
    }

    public void profileUpdate(ProfileDto profileDto) throws IOException {
        // 이전 닉네임
        String beforeName = dao.findMemberById(profileDto.getMemberseq()).getNickname();

        if (profileDto.getImage() != null) {
            MultipartFile imageFile = profileDto.getImage();
            String originalFileName = imageFile.getOriginalFilename();    //오리지날 파일명
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String storedFileName = UUID.randomUUID() + extension;
            String savePath = localPath + "profile/" + storedFileName;
            imageFile.transferTo(new File(savePath));

            profileDto.setProfile(storedFileName);
        }
        dao.profileUpdate(profileDto);

        // 팔로우 테이블 닉네임 수정
        dao.followUpdate(beforeName,profileDto.getNickname());
    }

    public List<FollowDto> followingMembers(int memberseq) {
        List<FollowDto> followDtoList = dao.followingMembers(memberseq);
        return followDtoList;
    }

    public List<FollowDto> followerembers(int memberseq) {
        List<FollowDto> followDtoList = dao.followerMembers(memberseq);
        return followDtoList;
    }

    public void pwdUpdate(MemberDto dto) {
        dto.setPwd(passwordEncoder.encode(dto.getPwd()));

        dao.pwdUpdate(dto);
    }

    public InbodyDto ocr(String filepath, int memberseq, String filename) {
        System.out.println("OCR메서드 진입");

        InbodyDto result = new InbodyDto();

        String apiURL = "https://w61ss3a1a3.apigw.ntruss.com/custom/v1/21554/64ff011f8ec39d04be66a679b81f665b47a00ed8b04828903e497e21061fef43/infer";
        String secretKey = "Y1RMWVBvSXZpQk1Lam5iZnNpa3RXc1RpaW1YZW5GVXQ=";

        String imageFile = filepath;

        StringBuffer response = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            File file = new File(imageFile);
            writeMultiPart(wr, postParams, file, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            String str = response.toString();   // OCR결과를 문자열로 저장

            List<String> ocrList = new ArrayList<>();
            String[] lines = str.split("\\\\n"); // 개행을 기준으로 문자열을 나눔
            for (String line : lines) {
                String[] words = line.split("\\s+"); // 띄어쓰기를 기준으로 단어를 나눔
                Collections.addAll(ocrList, words); // 나눈 단어를 리스트에 추가함
            }

            int k = 0;
            int index[] = {0, 0, 0};
            ocrList.add(0, "이미지 인식 실패"); // 아래의 for문에서 값을 찾지 못했을 때 출력할 정보

            String field[] = {"골격근량", "체지방량", "비만분석"};  // Search 대상 다음으로 나오는 텍스트
            for (int i = 1; i < ocrList.size(); i++) {
                if (ocrList.get(i).equals(field[k])) {
                    index[k] = i - 1;
                    k++;
                    if (k == field.length) {
                        break;
                    }
                }
            }

            // Dao로 넘길 값
            System.out.println("체중 : " + ocrList.get(index[0]));
            System.out.println("골격근량 : " + ocrList.get(index[1]));
            System.out.println("체지방량 : " + ocrList.get(index[2]));

            double weight = Double.parseDouble(ocrList.get(index[0]));
            double musclemass = Double.parseDouble(ocrList.get(index[1]));
            double bodyfatmass = Double.parseDouble(ocrList.get(index[2]));

            InbodyDto inbodyDto = new InbodyDto(memberseq, weight, musclemass, bodyfatmass, filename);

            result = inbodyDto;

            dao.inbodySave(inbodyDto);

        } catch (Exception e) {
            System.out.println(e);

            return result;
        }

        System.out.println("response.toString():"+response.toString());
        return result;
    }

    private void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString
                    .append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }

    public List<InbodyDto> bodycomList(int memberseq) {
        return dao.bodycomList(memberseq);
    }

    public List<Map<String, Object>> myBbsList(UserBbsParam userBbsParam) {
        return dao.myBbsList(userBbsParam);
    }

    public List<Map<String, Object>> myAllBbsList(UserBbsParam userBbsParam) {
        return dao.myAllBbsList(userBbsParam);
    }

    public void bodyComSave(InbodyDto inbodyDto) {
        dao.bodyComSave(inbodyDto);
    }

    public List<Map<String, Object>> myCmtBbsList(UserBbsParam userBbsParam) {
        return dao.myCmtBbsList(userBbsParam);
    }

    public List<Map<String, Object>> myAllCmtBbsList(UserBbsParam userBbsParam) {
        return dao.myAllCmtBbsList(userBbsParam);
    }

    public List<Map<String, Object>> myLikeBbsList(UserBbsParam userBbsParam) {
        return dao.myLikeBbsList(userBbsParam);
    }

    public List<Map<String, Object>> myAllLikeBbsList(UserBbsParam userBbsParam) {
        return dao.myAllLikeBbsList(userBbsParam);
    }

    public void bodyComDelete(int bodycomseq) {
       dao.bodyComDelete(bodycomseq);
    }

    public void memberDelete(MemberDto memberDto) {
        dao.memberDelete(memberDto);
    }

    public void reqFollow(FollowDto followDto) {
        dao.reqFollow(followDto);
    }

    public void reqUnFollow(FollowDto followDto) {
        dao.reqUnFollow(followDto);
    }

    public int confirmFollow(FollowDto followDto) {
       int result = dao.confirmFollow(followDto);

       return result;
    }

    public int findByNickname(String nickname) {
        int result = dao.findByNickname(nickname);

        return result;
    }
}
