package com.example.library.constants;


import java.util.List;

public class PublicApiList {

    private static final List<String> PUBLIC_API_LIST = List.of(
            "/api/Auth/Login",
            "/api/Auth/Register"
    );

    public static List<String> getPublicApiList() {
        return PUBLIC_API_LIST;
    }
}