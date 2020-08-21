package com.demo.cloud.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * TODO
 *
 * @author Jesen
 * @since 2020/5/4
 */
@Component
@ToString
public class JwtUtils {
    /**
     * 发布者
     */
    @Value("${cn.com.jwt.issuer}")
    private String issuer;
    /**
     * 该JWT所面向的用户
     */
    private String subject;

    /**
     * 发布给谁
     */
    @Value("${cn.com.jwt.audience}")
    private String audience;

    /**
     * JWT Key
     */
    @Value("${cn.com.jwt.securityKey}")
    private String securityKey;

    /**
     * token距离生成的过期时间，秒数
     */
    @Value("${cn.com.jwt.expiration}")
    private long expiration;

    /**
     * 生成 access_token
     * @param claims
     * @return
     */
    public String createAccessToken(Map<String,Object> claims) {
        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        //设置payload
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.HS256.getValue())
                .setHeaderParam("typ","JWT")
                .setId(UUID.randomUUID().toString())// 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now) // iat: jwt的签发时间
                .setNotBefore(now) // 定义在什么时间之前，该jwt都是不可用的
                .setIssuer(this.issuer) // issuer：jwt签发人
                .setAudience(this.audience) // 接收jwt的一方
                .setSubject(JSON.toJSONString(claims)) // 代表这个JWT的主题，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为用户的唯一标志。
                //.setClaims(claims) // 如果有私有声明,可以存放一些角色,权限等业务数据,如果设定了这个值,则主题subject就实效
                .signWith(signatureAlgorithm, this.generateKey()); // 设置签名使用的签名算法和签名使用的秘钥
        //设置过期时间
        if(this.expiration > 0){
            long expMilis = now.getTime() + this.expiration * 60 * 60 * 1000;
            builder.setExpiration(new Date(expMilis));
        }
        return builder.compact();
    }

    /**
     * 解析 access_token
     * @param accessToken
     * @return
     */
    public Claims parseAccessToken(String accessToken){
        return Jwts.parser()
                .setSigningKey(this.generateKey())
                .parseClaimsJws(accessToken)
                .getBody();
    }

    /**
     * 生成加密Key
     * @return
     */
    public SecretKey generateKey(){
        byte[] encodedKey = Base64.getDecoder().decode(this.securityKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, SignatureAlgorithm.HS256.getValue());
    }

    /**
     * 对access_token合法性做校验
     * @param accessToken
     * @return
     */
    public Claims validAccessToken(String accessToken) {

        Claims claims = parseAccessToken(accessToken);
        if(claims == null) {
            return null;
        }
        String issuer = claims.getIssuer();
        if(!issuer.equals(this.issuer)) {
            return null;
        }
        if(!claims.getAudience().equals(this.audience)) {
            return null;
        }
        return claims;
    }

    public static void main(String[] args) throws Exception{
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //LocalDate today = LocalDate.now();
        //Date date = new Date();
        //Date date1 = DateUtils.addYears(date, 1);
        //System.out.println(format.format(date1));
        //LocalDate comparedLocalDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //
        //Period p = Period.between(comparedLocalDate, today);
        //System.out.println(p.getMonths());

        //String regix = "^[1-9]\\d{5}1[8-9]\\d{2}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))((\\d{4})|(\\d{3}X))$";
        String regix = "^[1-9]\\d{5}1[8-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{4}|\\d{3}X)$";
        String id = "13aa3AA2";
        boolean matches = Pattern.matches(regix, id);
        System.out.println(matches);
    }
}
