package com.springcourse.security;

import com.springcourse.util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        String hash = HashUtil.getSecureHash(charSequence.toString());
        return hash;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String hash = HashUtil.getSecureHash(charSequence.toString());
        return hash.equals(s);
    }
}
