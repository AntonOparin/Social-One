// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from full

package com.clemble.social.provider.data.callback.delivery;

public enum DeliveryFormat {
    JSON(0);

    public final int number;

    private DeliveryFormat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static DeliveryFormat valueOf(int number) {
        switch (number) {
        case 0:
            return JSON;
        default:
            return null;
        }
    }
}