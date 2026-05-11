package com.tkart.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final StringRedisTemplate redisTemplate;
    private final SecureRandom secureRandom = new SecureRandom();

    private static final String OTP_PREFIX = "OTP:";
    private static final int OTP_LENGTH = 6;
    private static final long OTP_EXPIRY_MINUTES = 5;

    public String generateOtp(String email) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10));
        }
        
        String key = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(key, otp.toString(), OTP_EXPIRY_MINUTES, TimeUnit.MINUTES);
        
        return otp.toString();
    }

    public boolean validateOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        String cachedOtp = redisTemplate.opsForValue().get(key);
        
        if (cachedOtp != null && cachedOtp.equals(otp)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
