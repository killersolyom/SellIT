package com.sell.it.Model.Constant;

public class Values {

    public static class ItemType {
        public static final int BASE_ADVERTISEMENT_TYPE = 0;
        public static final int BASE_DEFAULT_TYPE = 1;
        public static final int ADVERTISEMENT_INFO_TYPE = 2;
        public static final int SEPARATOR_ITEM_TYPE = 3;
        public static final int LANGUAGE_ITEM_TYPE = 4;
    }

    public static class Language {
        public static final String LANGUAGE_KEY_ENGLISH = "en";
        public static final String LANGUAGE_KEY_HUNGARY = "hu";
    }

    public static class User {
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "firstname";
        public static final String LAST_NAME = "lastname";
        public static final String PASSWORD = "pass";
    }

    public static class DrawerControlAction {
        public static final String CLOSE_ACTION = "CLOSE";
        public static final String ENABLE_ACTION = "ENABLE";
        public static final String DISABLE_ACTION = "DISABLE";
    }

    public static class Orientation {
        public static final String PORTRAIT = "PORTRAIT";
        public static final String LANDSCAPE = "LANDSCAPE";
    }

    public static class Firebase {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
        public static final String USER_KEY = "USER_KEY";
    }
}
