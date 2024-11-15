package com.bk.square.grpc.rest.service;


import org.springframework.stereotype.Service;

@Service
public class RestSquareService {

    public int getSquareUnary(int number) {
        return number * number;
    }

}
