package weer.elytrondesign.core.wp

import weer.elytrondesign.R
import java.util.*

abstract class WallpaperManager {

    companion object {
        const val URL_0 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F0.jpg?alt=media&token=19d990db-4e0b-4898-91ff-27bb6aff9ed9"
        const val URL_1 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F1.jpg?alt=media&token=26d12627-2531-474f-84ae-5a0916d8574d"
        const val URL_2 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F2.jpg?alt=media&token=74514d34-c236-45c2-a5cf-61d8955a05c4"
        const val URL_3 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F3.jpg?alt=media&token=70de7c89-7fc5-4d2b-bf68-c7cc012c2e29"
        const val URL_4 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F4.jpg?alt=media&token=2ac85bf7-33b5-4d30-87c4-83a695332479"
        const val URL_5 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F5.jpg?alt=media&token=124d83d4-7d97-443c-8a7d-9dac989dcba8"
        const val URL_6 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F6.jpg?alt=media&token=e8ef4e1e-668c-4461-9edf-04f00df8382a"
        const val URL_7 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F7.jpg?alt=media&token=dff3fef6-2c06-40a1-97fd-d5452cf8d49b"
        const val URL_8 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F8.jpg?alt=media&token=27740c85-b670-40f2-b09d-0e6ab38dd8e7"
        const val URL_9 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F9.jpg?alt=media&token=c603db34-6da3-487a-82ac-92ace88b2dcc"
        const val URL_10 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F10.jpg?alt=media&token=7c114d23-a358-49b8-84ad-267109939813"
        const val URL_11 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F11.jpg?alt=media&token=cb61bece-1b14-46ca-a34f-3ffd002fc693"
        const val URL_12 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F12.jpg?alt=media&token=9ce5f634-8bd6-4499-983d-ff63febffe99"
        const val URL_13 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F13.jpg?alt=media&token=3d95f1b8-bbce-4483-849a-233339b80b1c"
        const val URL_14 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F14.jpg?alt=media&token=dbeacb83-2a76-41f0-b409-a862c39faf08"
        const val URL_15 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F15.jpg?alt=media&token=836ea83a-8593-42f2-aa4f-1819eeb806f5"
        const val URL_16 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F16.jpg?alt=media&token=e4ada8dd-f2de-4c9f-9515-68e6b700f2dc"
        const val URL_17 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F17.jpg?alt=media&token=0969a520-db33-46cf-813c-ff8a49002be7"
        const val URL_18 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F18.jpg?alt=media&token=5c1de429-dd42-403b-baba-510c16628ebe"
        const val URL_19 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F19.jpg?alt=media&token=412a7bcb-99fb-4e29-aa40-a9a4ee299521"
        const val URL_20 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F20.jpg?alt=media&token=58a1ecb6-36bf-4018-b7e8-fe22a0b4fd26"
        const val URL_21 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F21.jpg?alt=media&token=d0ada77b-a6c5-4530-9a3b-20d2dbd25c9f"
        const val URL_22 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F22.jpg?alt=media&token=694581dd-aadb-4ad0-a37a-cf12aef924ca"
        const val URL_23 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F23.jpg?alt=media&token=bd385f71-1351-43d8-b7a5-cbb10b058f6b"
        const val URL_24 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F24.jpg?alt=media&token=895a63ce-9aaa-4c6c-8da5-9b1eef01ea29"
        const val URL_25 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F25.jpg?alt=media&token=b9691106-b32f-497a-a3ab-c9fef1cf6169"
        const val URL_26 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F26.jpg?alt=media&token=7197ccf5-f827-4282-8597-5377fcddc1c0"
        const val URL_27 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F27.jpg?alt=media&token=cb0b8d6b-8e05-4427-9b0e-bd7557ea1ffa"
        const val URL_28 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F28.jpg?alt=media&token=8262d1c7-bf17-4a17-9b4d-64572446f1ff"
        const val URL_29 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F29.jpg?alt=media&token=e6214e51-f88e-479c-8376-e06928e49cc4"
        const val URL_30 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F30.jpg?alt=media&token=f2f8e597-44be-4f5a-9203-18d7d5fc2bae"
        const val URL_31 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F31.jpg?alt=media&token=52fcb7b7-afed-491c-9012-5d7983bea438"
        const val URL_32 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F32.jpg?alt=media&token=5d7cef5f-ac76-4a21-a8e1-feed661a15c7"
        const val URL_33 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F33.jpg?alt=media&token=ae1507d6-aa05-4b23-947c-7808363f90be"
        const val URL_34 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F34.jpg?alt=media&token=122c3d17-47ba-4ad1-9187-0a0cf7da37cf"
        const val URL_35 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F35.jpg?alt=media&token=7ba53bee-7e7c-468e-9884-3fa990db30db"
        const val URL_36 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F36.jpg?alt=media&token=625d1136-51a8-4514-bd16-215416df06ec"
        const val URL_37 =
            "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/wallpapers%2F37.jpg?alt=media&token=ac4d48c9-c8ce-4530-9b60-380568199864"

        fun pickRandomInit(): Int {
            var res = R.drawable.init_wallpaper0
            when (Random().nextInt(5)) {
                0 -> res = R.drawable.init_wallpaper0
                1 -> res = R.drawable.init_wallpaper1
                2 -> res = R.drawable.init_wallpaper2
                3 -> res = R.drawable.init_wallpaper3
                4 -> res = R.drawable.init_wallpaper4
            }
            return res
        }

        fun pickRandomUrlPair(): Array<Any> {
            val pair = arrayOf<Any>(0, URL_0)
            when (Random().nextInt(38)) {
                0 -> {
                    pair.apply {
                        this[0] = 0
                        this[1] = URL_0
                    }
                }
                1 -> {
                    pair.apply {
                        this[0] = 1
                        this[1] = URL_1
                    }
                }
                2 -> {
                    pair.apply {
                        this[0] = 2
                        this[1] = URL_2
                    }
                }
                3 -> {
                    pair.apply {
                        this[0] = 3
                        this[1] = URL_3
                    }
                }
                4 -> {
                    pair.apply {
                        this[0] = 4
                        this[1] = URL_4
                    }
                }
                5 -> {
                    pair.apply {
                        this[0] = 5
                        this[1] = URL_5
                    }
                }
                6 -> {
                    pair.apply {
                        this[0] = 6
                        this[1] = URL_6
                    }
                }
                7 -> {
                    pair.apply {
                        this[0] = 7
                        this[1] = URL_7
                    }
                }
                8 -> {
                    pair.apply {
                        this[0] = 8
                        this[1] = URL_7
                    }
                }
                9 -> {
                    pair.apply {
                        this[0] = 9
                        this[1] = URL_9
                    }
                }
                10 -> {
                    pair.apply {
                        this[0] = 10
                        this[1] = URL_10
                    }
                }
                11 -> {
                    pair.apply {
                        this[0] = 11
                        this[1] = URL_11
                    }
                }
                12 -> {
                    pair.apply {
                        this[0] = 12
                        this[1] = URL_12
                    }
                }
                13 -> {
                    pair.apply {
                        this[0] = 13
                        this[1] = URL_13
                    }
                }
                14 -> {
                    pair.apply {
                        this[0] = 14
                        this[1] = URL_14
                    }
                }
                15 -> {
                    pair.apply {
                        this[0] = 15
                        this[1] = URL_15
                    }
                }
                16 -> {
                    pair.apply {
                        this[0] = 1
                        this[1] = URL_16
                    }
                }
                17 -> {
                    pair.apply {
                        this[0] = 17
                        this[1] = URL_17
                    }
                }
                18 -> {
                    pair.apply {
                        this[0] = 18
                        this[1] = URL_18
                    }
                }
                19 -> {
                    pair.apply {
                        this[0] = 19
                        this[1] = URL_19
                    }
                }
                20 -> {
                    pair.apply {
                        this[0] = 20
                        this[1] = URL_20
                    }
                }
                21 -> {
                    pair.apply {
                        this[0] = 21
                        this[1] = URL_21
                    }
                }
                22 -> {
                    pair.apply {
                        this[0] = 22
                        this[1] = URL_22
                    }
                }
                23 -> {
                    pair.apply {
                        this[0] = 23
                        this[1] = URL_23
                    }
                }
                24 -> {
                    pair.apply {
                        this[0] = 24
                        this[1] = URL_24
                    }
                }
                25 -> {
                    pair.apply {
                        this[0] = 25
                        this[1] = URL_25
                    }
                }
                26 -> {
                    pair.apply {
                        this[0] = 26
                        this[1] = URL_26
                    }
                }
                27 -> {
                    pair.apply {
                        this[0] = 27
                        this[1] = URL_27
                    }
                }
                28 -> {
                    pair.apply {
                        this[0] = 28
                        this[1] = URL_28
                    }
                }
                29 -> {
                    pair.apply {
                        this[0] = 29
                        this[1] = URL_29
                    }
                }
                30 -> {
                    pair.apply {
                        this[0] = 30
                        this[1] = URL_30
                    }
                }
                31 -> {
                    pair.apply {
                        this[0] = 31
                        this[1] = URL_31
                    }
                }
                32 -> {
                    pair.apply {
                        this[0] = 32
                        this[1] = URL_32
                    }
                }
                33 -> {
                    pair.apply {
                        this[0] = 33
                        this[1] = URL_33
                    }
                }
                34 -> {
                    pair.apply {
                        this[0] = 34
                        this[1] = URL_34
                    }
                }
                35 -> {
                    pair.apply {
                        this[0] = 35
                        this[1] = URL_35
                    }
                }
                36 -> {
                    pair.apply {
                        this[0] = 36
                        this[1] = URL_36
                    }

                }
                37 -> {
                    pair.apply {
                        this[0] = 37
                        this[1] = URL_37
                    }
                }
            }
            return pair
        }
    }

}