package by.jrr.telegrammoodlebot.util;

/***
 * https://core.telegram.org/bots/faq#my-bot-is-hitting-limits-how-do-i-avoid-this
 *
 * Broadcasting to Users
 * My bot is hitting limits, how do I avoid this?
 * When sending messages inside a particular chat, avoid sending more than one message per second. We may allow short
 * bursts that go over this limit, but eventually you'll begin receiving 429 errors.
 *
 * If you're sending bulk notifications to multiple users, the API will not allow more than 30 messages per second or so.
 * Consider spreading out notifications over large intervals of 8—12 hours for best results.
 *
 * Also note that your bot will not be able to send more than 20 messages per minute to the same group.
 *
 * How can I message all of my bot's subscribers at once?
 * Unfortunately, at this moment we don't have methods for sending bulk messages, e.g. notifications. We may add
 * something along these lines in the future.
 *
 * In order to avoid hitting our limits when sending out mass notifications, consider spreading them over longer intervals,
 * e.g. 8-12 hours. The API will not allow bulk notifications to more than ~30 users per second, if you go over that,
 * you'll start getting 429 errors.
 */
public class Pauses {

    private static final long PAUSE_PERIOD = 1500l;

    public static void pause() {
        try {
            Thread.sleep(PAUSE_PERIOD);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
