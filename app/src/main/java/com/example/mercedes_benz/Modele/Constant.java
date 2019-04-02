package com.example.mercedes_benz.Modele;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public class Constant {

    public static final String KEY = "key";



    public static Spanned fromHtml(String html){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}
