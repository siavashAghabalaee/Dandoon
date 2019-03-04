package com.zavosh.software.dentist.dentist.Helper;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class StringUtility {
    public static final String FA_CHARS = "ابپتثجچحخدذرزژسصضطظعغکگلمنوهیء";
    private static Typeface typefaceFontIcon;
    private static Typeface typeface;
    public static final String FA_NUMS = "۰۱۲۳۴۵۶۷۸۹";
    public static final String EN_NUMS = "0123456789";

    public boolean isFarsi(String txt) {
        if (txt == null) return false;

        for (int i = 0; i < txt.length(); i++) {
            if (FA_CHARS.contains(txt.charAt(i) + "")) {
                return true;
            }
        }
        return false;
    }

    public void overrideFontIconButton(final View v) {
        try {
            if (typefaceFontIcon == null) {
                typefaceFontIcon = Typeface.createFromAsset(v.getContext().getAssets(), "fonticon.ttf");
            }
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontIconButton(child);
                }
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(typefaceFontIcon);
            }
        } catch (Exception e) {
        }
    }

    public void overrideFontIconTextView(final View v) {
        try {
            if (typefaceFontIcon == null) {
                typefaceFontIcon = Typeface.createFromAsset(v.getContext().getAssets(), "fonticon.ttf");
            }
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontIconTextView(child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(typefaceFontIcon);
            }
        } catch (Exception e) {
        }
    }

    public void overrideFonts(final View v, Typeface typeface) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, typeface);
                }
            } else if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTypeface(typeface);
            } else if (v instanceof Button) {
                Button b = (Button) v;
                b.setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }






    public static boolean isValidPhoneNumber(String number){

        if(number==null){
            return false;
        }
        Pattern cellNumberPattern = Pattern.compile("^(989|9|09|\\+989|\\u0660\\u0669|\\u0669\\u0668\\u0669|\\u0669|\\+\\u0669\\u0668\\u0669)[0-9\\u0660-\\u0669]{9}$");
        return cellNumberPattern.matcher(number).matches();


    }

    public void overrideFonts(final View v) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(v.getContext().getAssets(), "IRAN-Sans.ttf");
        }
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, typeface);
                }
            } else if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTypeface(typeface);
            } else if (v instanceof Button) {
                Button b = (Button) v;
                b.setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String numberEn2Fa(String s) {
        if (StringUtility.isNull(s)) return s;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (EN_NUMS.contains(array[i] + ""))
                array[i] = FA_NUMS.charAt(Integer.parseInt(array[i] + ""));
        }
        return new String(array);
    }
    public static String numberFa2En(String s) {
        if (StringUtility.isNull(s)) return s;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (FA_NUMS.contains(array[i] + ""))
                array[i] = EN_NUMS.charAt(Integer.parseInt(array[i] + ""));
        }
        return new String(array);
    }

    public static boolean isNull(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return s != null && !s.isEmpty();
    }


}
