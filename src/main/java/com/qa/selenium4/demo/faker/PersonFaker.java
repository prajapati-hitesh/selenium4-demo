package com.qa.selenium4.demo.faker;

import com.github.javafaker.Faker;

import java.util.Random;

public class PersonFaker {
    public static String firstName() {
        return new Faker(new Random())
                .name()
                .firstName();
    }

    public static String lastName() {
        return new Faker(new Random())
                .name()
                .lastName();
    }

    public static String email() {
        return new Faker(new Random())
                .internet()
                .safeEmailAddress();
    }

    public static String phone() {
        return new Faker(new Random())
                .phoneNumber()
                .cellPhone();
    }

    public static String address() {
        return new Faker(new Random())
                .address()
                .fullAddress();
    }

    public static String city() {
        return new Faker(new Random())
                .address()
                .city();
    }

    public static String zipCode() {
        return new Faker(new Random())
                .address()
                .zipCode();
    }

    public static String website() {
        return new Faker(new Random())
                .internet()
                .url();
    }

    public static String description() {
        return new Faker(new Random())
                .lorem()
                .paragraph();
    }
}
