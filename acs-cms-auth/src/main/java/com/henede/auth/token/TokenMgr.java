package com.henede.auth.token;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.henede.auth.token.TokenConfig;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenMgr {
	@Autowired
	private TokenConfig tokenConfig;

    public SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(tokenConfig.getTokenSecret());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 签发JWT
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, Object subject, Long ttlMillis, Long nowMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        if(null==nowMillis) {
        	nowMillis = System.currentTimeMillis();
        }
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(generalSubject(subject))
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }
    
    public String createJWT(String id, Object subject, Long ttlMillis) {
    	return createJWT(id, subject, ttlMillis, null);
    }
    
    /**
     * 创建tokenData
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public TokenData createTokenData(String id, Object subject) {
    	long curMillis = System.currentTimeMillis();
    	Long ttlMillis = tokenConfig.getExpireTime();
    	Long refreshExpiprTime = tokenConfig.getRefreshExpireTime();
    	TokenData tokenData = new TokenData();
    	tokenData.setAccessToken(createJWT(id, subject, ttlMillis, curMillis));
    	tokenData.setExpireTime(curMillis+ttlMillis);
    	tokenData.setRefreshToken(createJWT(id, subject, ttlMillis+refreshExpiprTime, curMillis));
    	tokenData.setRefreshTokenValidTime(curMillis+ttlMillis+refreshExpiprTime);
    	return tokenData;
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public ResponseStatus validateJWT(String jwtStr) {
    	ResponseStatus checkResult = new ResponseStatus();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.set(ResponseStatusType.AUTH_SUCCESS.getCode(), ResponseStatusType.AUTH_SUCCESS.getMsg());
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
        	checkResult.set(ResponseStatusType.AUTH_TIME_FAIL.getCode(), ResponseStatusType.AUTH_TIME_FAIL.getMsg());
        } catch (SignatureException e) {
        	checkResult.set(ResponseStatusType.AUTH_TOKEN_FAIL.getCode(), ResponseStatusType.AUTH_TOKEN_FAIL.getMsg());
        } catch (Exception e) {
        	checkResult.set(ResponseStatusType.AUTH_TOKEN_ERROR.getCode(), ResponseStatusType.AUTH_TOKEN_ERROR.getMsg());
        }
        return checkResult;
    }

    /**
     * 
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(jwt)
            .getBody();
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
    public static String generalSubject(Object sub){
    	Gson gson = new Gson();
        return gson.toJson(sub);
    }

	public TokenConfig getTokenConfig() {
		return tokenConfig;
	}

}