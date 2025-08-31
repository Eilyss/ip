//package com.elsria.time;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.regex.Pattern;
//
//public abstract class TimeFormat {
//    protected LocalTime currentTime;
//
//    public class TwelveHour {
//        Pattern pattern = TimeConstants.TWELVE_HOUR_PATTERN;
//
//        public ArrayList<LocalTime> parse(String time) {
//            super.currentTime = LocalTime.now();
//        }
//    }
//    public class TwentyFourHour {
//        Pattern pattern = TimeConstants.TWENTY_FOUR_HOUR_PATTERN;
//    }
//
//    public abstract ArrayList<LocalTime> parse(String time);
//
//
//
//}
