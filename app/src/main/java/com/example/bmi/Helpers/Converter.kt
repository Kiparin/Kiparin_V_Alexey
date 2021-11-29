package com.example.bodymassindex.Helpers

public class Converter {
    fun roundOffTo2DecPlaces(`val`: Double): String? {
        return String.format("%.2f", `val`)
    }
}