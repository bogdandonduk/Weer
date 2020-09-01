package weer.elytrondesign.core.java;

import java.util.Random;

import weer.elytrondesign.R;

public class WallpaperManager {

    public static final String URL_0 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F0.jpg?alt=media&token=19d990db-4e0b-4898-91ff-27bb6aff9ed9";
    public static final String URL_1 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F1.jpg?alt=media&token=26d12627-2531-474f-84ae-5a0916d8574d";
    public static final String URL_2 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F2.jpg?alt=media&token=74514d34-c236-45c2-a5cf-61d8955a05c4";
    public static final String URL_3 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F3.jpg?alt=media&token=70de7c89-7fc5-4d2b-bf68-c7cc012c2e29";
    public static final String URL_4 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F4.jpg?alt=media&token=2ac85bf7-33b5-4d30-87c4-83a695332479";
    public static final String URL_5 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F5.jpg?alt=media&token=124d83d4-7d97-443c-8a7d-9dac989dcba8";
    public static final String URL_6 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F6.jpg?alt=media&token=e8ef4e1e-668c-4461-9edf-04f00df8382a";
    public static final String URL_7 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F7.jpg?alt=media&token=dff3fef6-2c06-40a1-97fd-d5452cf8d49b";
    public static final String URL_8 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F8.jpg?alt=media&token=27740c85-b670-40f2-b09d-0e6ab38dd8e7";
    public static final String URL_9 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F9.jpg?alt=media&token=c603db34-6da3-487a-82ac-92ace88b2dcc";
    public static final String URL_10 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F10.jpg?alt=media&token=7c114d23-a358-49b8-84ad-267109939813";
    public static final String URL_11 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F11.jpg?alt=media&token=cb61bece-1b14-46ca-a34f-3ffd002fc693";
    public static final String URL_12 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F12.jpg?alt=media&token=9ce5f634-8bd6-4499-983d-ff63febffe99";
    public static final String URL_13 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F13.jpg?alt=media&token=3d95f1b8-bbce-4483-849a-233339b80b1c";
    public static final String URL_14 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F14.jpg?alt=media&token=dbeacb83-2a76-41f0-b409-a862c39faf08";
    public static final String URL_15 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F15.jpg?alt=media&token=836ea83a-8593-42f2-aa4f-1819eeb806f5";
    public static final String URL_16 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F16.jpg?alt=media&token=e4ada8dd-f2de-4c9f-9515-68e6b700f2dc";
    public static final String URL_17 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F17.jpg?alt=media&token=0969a520-db33-46cf-813c-ff8a49002be7";
    public static final String URL_18 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F18.jpg?alt=media&token=5c1de429-dd42-403b-baba-510c16628ebe";
    public static final String URL_19 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F19.jpg?alt=media&token=412a7bcb-99fb-4e29-aa40-a9a4ee299521";
    public static final String URL_20 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F20.jpg?alt=media&token=58a1ecb6-36bf-4018-b7e8-fe22a0b4fd26";
    public static final String URL_21 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F21.jpg?alt=media&token=d0ada77b-a6c5-4530-9a3b-20d2dbd25c9f";
    public static final String URL_22 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F22.jpg?alt=media&token=694581dd-aadb-4ad0-a37a-cf12aef924ca";
    public static final String URL_23 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F23.jpg?alt=media&token=bd385f71-1351-43d8-b7a5-cbb10b058f6b";
    public static final String URL_24 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F24.jpg?alt=media&token=895a63ce-9aaa-4c6c-8da5-9b1eef01ea29";
    public static final String URL_25 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F25.jpg?alt=media&token=b9691106-b32f-497a-a3ab-c9fef1cf6169";
    public static final String URL_26 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F26.jpg?alt=media&token=7197ccf5-f827-4282-8597-5377fcddc1c0";
    public static final String URL_27 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F27.jpg?alt=media&token=cb0b8d6b-8e05-4427-9b0e-bd7557ea1ffa";
    public static final String URL_28 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F28.jpg?alt=media&token=8262d1c7-bf17-4a17-9b4d-64572446f1ff";
    public static final String URL_29 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F29.jpg?alt=media&token=e6214e51-f88e-479c-8376-e06928e49cc4";
    public static final String URL_30 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F30.jpg?alt=media&token=f2f8e597-44be-4f5a-9203-18d7d5fc2bae";
    public static final String URL_31 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F31.jpg?alt=media&token=52fcb7b7-afed-491c-9012-5d7983bea438";
    public static final String URL_32 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F32.jpg?alt=media&token=5d7cef5f-ac76-4a21-a8e1-feed661a15c7";
    public static final String URL_33 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F33.jpg?alt=media&token=ae1507d6-aa05-4b23-947c-7808363f90be";
    public static final String URL_34 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F34.jpg?alt=media&token=122c3d17-47ba-4ad1-9187-0a0cf7da37cf";
    public static final String URL_35 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F35.jpg?alt=media&token=7ba53bee-7e7c-468e-9884-3fa990db30db";
    public static final String URL_36 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F36.jpg?alt=media&token=625d1136-51a8-4514-bd16-215416df06ec";
    public static final String URL_37 = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F37.jpg?alt=media&token=ac4d48c9-c8ce-4530-9b60-380568199864";

    public static int pickRandomInit() {
        int res = R.drawable.init_wallpaper0;

        switch(new Random().nextInt(5)) {
            case 0:
                res = R.drawable.init_wallpaper0;
                break;
            case 1:
                res = R.drawable.init_wallpaper1;
                break;
            case 2:
                res = R.drawable.init_wallpaper2;
                break;
            case 3:
                res = R.drawable.init_wallpaper3;
                break;
            case 4:
                res = R.drawable.init_wallpaper4;
                break;
        }

        return res;
    }

    public static String pickRandomUrl() {
        String url = URL_0;

        switch(new Random().nextInt(38)) {
            case 0:
                url = URL_0;
                break;
            case 1:
                url = URL_1;
                break;
            case 2:
                url = URL_2;
                break;
            case 3:
                url = URL_3;
                break;
            case 4:
                url = URL_4;
                break;
            case 5:
                url = URL_5;
                break;
            case 6:
                url = URL_6;
                break;
            case 7:
                url = URL_7;
                break;
            case 8:
                url = URL_8;
                break;
            case 9:
                url = URL_9;
                break;
            case 10:
                url = URL_10;
                break;
            case 11:
                url = URL_11;
                break;
            case 12:
                url = URL_12;
                break;
            case 13:
                url = URL_13;
                break;
            case 14:
                url = URL_14;
                break;
            case 15:
                url = URL_15;
                break;
            case 16:
                url = URL_16;
                break;
            case 17:
                url = URL_17;
                break;
            case 18:
                url = URL_18;
                break;
            case 19:
                url = URL_19;
                break;
            case 20:
                url = URL_20;
                break;
            case 21:
                url = URL_21;
                break;
            case 22:
                url = URL_22;
                break;
            case 23:
                url = URL_23;
                break;
            case 24:
                url = URL_24;
                break;
            case 25:
                url = URL_25;
                break;
            case 26:
                url = URL_26;
                break;
            case 27:
                url = URL_27;
                break;
            case 28:
                url = URL_28;
                break;
            case 29:
                url = URL_29;
                break;
            case 30:
                url = URL_30;
                break;
            case 31:
                url = URL_31;
                break;
            case 32:
                url = URL_32;
                break;
            case 33:
                url = URL_33;
                break;
            case 34:
                url = URL_34;
                break;
            case 35:
                url = URL_35;
                break;
            case 36:
                url = URL_36;
                break;
            case 37:
                url = URL_37;
                break;
        }

        return url;
    }
}