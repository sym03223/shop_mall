package com.practice.shop.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomPasswordEncoding {

    @SuppressWarnings("deprecation")
    public String sha256Encoding(String password) {
        String encPassword = "";
        encPassword = passwordEncoder("sha256").encode(password);
        // encPassword = encPassword.replace("{sha256}", "");
        return encPassword;
    }


    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder(String type) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(type, encoders);
    }

    public boolean sha256Matching(String password, String encPassword) {
//        encPassword = "{sha256}"+encPassword;
        return passwordEncoder("sha256").matches(password, encPassword);
    }


}
