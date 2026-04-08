package com.hospital.medicalhisbackend.common;

public class UserContext {

    private static final ThreadLocal<UserInfo> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(Long userId, String role) {
        USER_THREAD_LOCAL.set(new UserInfo(userId, role));
    }

    public static Long getUserId() {
        UserInfo userInfo = USER_THREAD_LOCAL.get();
        return userInfo != null ? userInfo.getUserId() : null;
    }

    public static String getRole() {
        UserInfo userInfo = USER_THREAD_LOCAL.get();
        return userInfo != null ? userInfo.getRole() : null;
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }

    public static class UserInfo {
        private Long userId;
        private String role;

        public UserInfo(Long userId, String role) {
            this.userId = userId;
            this.role = role;
        }

        public Long getUserId() {
            return userId;
        }

        public String getRole() {
            return role;
        }
    }
}
