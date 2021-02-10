package com.springcourse.hardtoken;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Date;

public class HardToken {

    private String generateHardToken(String key, String typ, String alg, Date exp, Long id, String email){
        // BASE 64
        String encodedHeaderString = Base64.getEncoder().encodeToString(new Gson().toJson(new Header(typ,alg)).getBytes());
        String encodedPayloadString = Base64.getEncoder().encodeToString(new Gson().toJson(new Payload(exp, id, email)).getBytes());
        //SIGN
        String sign = HmacSHA256(key, encodedHeaderString+"."+encodedPayloadString);
        return encodedHeaderString+"."+encodedPayloadString+"."+sign;
    }

    private String HmacSHA256(String secretKey, String content){
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            sha256Hmac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
            return String.format("%032x", new BigInteger(1, sha256Hmac.doFinal(content.getBytes())));
        } catch (Exception e){
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
    }

    public void start(){
        Date currentTime = new Date();
        String token = generateHardToken("123456789","JWT", "HS256", currentTime, 1L, "teste@teste.com");
        System.out.println(token);
    }

    public static void main(String[] args) {
        new HardToken().start();
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
class Header{
    private String typ;
    private String alg;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
class Payload{
    private Date exp;
    private Long id;
    private String email;
}