package com.fsck.k9.notification;


import com.fsck.k9.Account;


class NotificationIds {
    private static final int OFFSET_SEND_FAILED_NOTIFICATION = 0;
    private static final int OFFSET_CERTIFICATE_ERROR_INCOMING = 1;
    private static final int OFFSET_CERTIFICATE_ERROR_OUTGOING = 2;
    private static final int OFFSET_FETCHING_MAIL = 3;
    private static final int OFFSET_NEW_MAIL_SUMMARY = 4;
    private static final int OFFSET_NEW_MAIL_STACKED = 5;

    private static final int NUMBER_OF_DEVICE_NOTIFICATIONS = OFFSET_NEW_MAIL_STACKED;

    private static final int NUMBER_OF_NOTIFICATIONS_PER_ACCOUNT = NUMBER_OF_DEVICE_NOTIFICATIONS +
            NotificationData.MAX_NUMBER_OF_ACTIVE_NOTIFICATIONS;
            

    public static int getNewMailNotificationId(Account account) {
        return getBaseNotificationId(account) + OFFSET_NEW_MAIL_SUMMARY;
    }

    public static int getNewMailNotificationId(Account account, int offset) {
        if (offset < 1 || offset > NotificationData.MAX_NUMBER_OF_ACTIVE_NOTIFICATIONS) {
            throw new IllegalArgumentException("Invalid value for offset: " + offset);
        }

        return getBaseNotificationId(account) + OFFSET_NEW_MAIL_STACKED + offset - 1;
    }

    public static int getFetchingMailNotificationId(Account account) {
        return getBaseNotificationId(account) + OFFSET_FETCHING_MAIL;
    }

    public static int getSendFailedNotificationId(Account account) {
        return getBaseNotificationId(account) + OFFSET_SEND_FAILED_NOTIFICATION;
    }

    public static int getCertificateErrorNotificationId(Account account, boolean incoming) {
        int offset = incoming ? OFFSET_CERTIFICATE_ERROR_INCOMING : OFFSET_CERTIFICATE_ERROR_OUTGOING;
        return getBaseNotificationId(account) + offset;
    }

    private static int getBaseNotificationId(Account account) {
        return account.getAccountNumber() * NUMBER_OF_NOTIFICATIONS_PER_ACCOUNT;
    }
}
