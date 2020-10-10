package com.hzq.dubbo.jwt;

import com.hzq.dubbo.aop.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt工具
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/1 9:57
 */
@Data
@Slf4j
public class JwtUtils {
    private static String key = "ThisIsMySecret";

    private static String PREFIX = "auth";

    private static String HEADPARA = "Authorization";

    //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * token生成
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:38
     */
    public static String getToken(String name, String chName, String dept) {
        Map<String, Object> map = new HashMap();
        map.put("name", name);
        map.put("chName", chName);
        map.put("dept", dept);

        JwtBuilder jwtBuilder = Jwts.builder()
                /**
                 * 如果有私有声明，一定要先设置这个自己创建的私有的声明，
                 * 这个是给builder的claim赋值，
                 * 一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                 */
                .addClaims(map)
                /**
                 * 设置jti(JWT ID)：是JWT的唯一标识，
                 * 根据业务需要，这个可以设置为一个不重复的值，
                 * 主要用来作为一次性token,从而回避重放攻击。
                 *
                 */
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(new Date())
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(name)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key)
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));

        return jwtBuilder.compact();
    }

    /**
     * token解析
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:38
     */
    public static UserInfo toObj(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            UserInfo userInfo = new UserInfo();
            userInfo.setName((String) claims.get("name"));
            userInfo.setChName((String) claims.get("chName"));
            userInfo.setDept((String) claims.get("dept"));
            return userInfo;
        }catch (Exception e){
            log.error("token转换异常：{}",e.getMessage());
            return new UserInfo();
        }
    }

    /**
     * 检查token有效性
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:38
    */
    public static boolean checkToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
