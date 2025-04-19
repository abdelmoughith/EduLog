package com.example.edulog.utils;

import android.graphics.Color;

import java.util.Random;

public class ColorGenerator {
    public static int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));  // Random color
    }
}
