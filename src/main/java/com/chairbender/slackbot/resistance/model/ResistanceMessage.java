package com.chairbender.slackbot.resistance.model;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Encapsulates a message in a game.
 *
 * Created by chairbender on 11/19/2015.
 */
public class ResistanceMessage {
    private SlackMessagePosted event;
    private boolean isTesting;

    public ResistanceMessage(SlackMessagePosted event, boolean isTesting) {
        this.event = event;
        this.isTesting = isTesting;
    }

    /**
     *
     * @param postedEvent event to encapsulate
     * @return a resistancemessage encapsulating  the slackmessageposted event. If testing is set to true,
     *      will determine player name by looking at the first word.
     */
    public static ResistanceMessage fromSlackMessagePosted(SlackMessagePosted postedEvent, boolean isTesting) {
        return new ResistanceMessage(postedEvent,isTesting);
    }

    /**
     *
     * @return the message posted. If testing mode enabled, returns the stuff after the username portion
     */
    public String getMessage() {
        if (isTesting) {
            if (event.getMessageContent().contains(" ")) {
                return event.getMessageContent().split(" ", 2)[1];
            } else {
                return "";
            }
        } else {
            return event.getMessageContent();
        }
    }

    /**
     *
     * @return the channel the message was posted to
     */
    public SlackChannel getChannel() {
        return event.getChannel();
    }

    /**
     *
     * @return the username of the sender. If testing mode, returns the first word of the message.
     */
    public String getSender() {
        if (isTesting) {
            return event.getMessageContent().split(" ")[0];
        } else {
            return event.getSender().getUserName();
        }
    }
}
