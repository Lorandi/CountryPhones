package com.rodrigolorandi;

import com.rodrigolorandi.service.Service;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class App {
    public static void main(String[] args) {
        Service.run();
    }
}