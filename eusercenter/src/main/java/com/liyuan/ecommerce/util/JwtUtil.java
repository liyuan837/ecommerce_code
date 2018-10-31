package com.liyuan.ecommerce.util;

import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtil {

    public static String stringKey ="LIYUAN";
    public static byte[] encodedKey = Base64.decodeBase64(stringKey);
    public static SecretKey key= new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

    /**
     * 生成token
     * @return
     */
    public static String generateUserToken(LoginUserVo loginUserVo){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date startTime = new Date(System.currentTimeMillis());
        //设置token失效时间:24小时
        Date expireTime = new Date(startTime.getTime()+ 60 * 1000 * 60 * 24);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(startTime)
                //设置失效时间
                .setExpiration(expireTime)
                .claim("userId", loginUserVo.getUserId())
                .claim("userCode", loginUserVo.getUserCode())
                .claim("userRole",loginUserVo.getUserRoleId())
                .signWith(signatureAlgorithm, key);    //签名，需要算法和key
        String token = builder.compact();
        return token;
    }
    /**
     * 获取验证Claims
     */
    public static Claims getClaims(String token){
        //获取claims
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)     //此处的key要与之前创建的key一致
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){

        }
        return claims;
    }

    /**
     * 验证登录
     * @param authorization
     * @return
     */
    public static JwtUser checkLogin(String authorization) throws eusercenterException {
        JwtUser jwtUser;

        Claims claims = getClaims(authorization);
        if (claims == null) {
            throw new eusercenterException("无效token");
        }

        String usercode = (String) claims.get("usercode");
        if(usercode == null || usercode.equals("")){
            throw new eusercenterException("会话中的用户编号为空");
        }

        Long expiration = (Long) claims.get("expiration");
        if(expiration < (new Date().getTime())){
            throw new eusercenterException("会话超时，请重新登陆");
        }

        jwtUser = new JwtUser();
        jwtUser.setUsercode(usercode);
        jwtUser.setUsername((String) claims.get("username"));

        return jwtUser;
    }

}
