package com.rhyme.fsbp.service;

public interface TokenService {
    void createToken();

    void checkToken(String token);
}
