package org.dows.log.event;

import org.springframework.context.ApplicationEvent;

public class BinLogEvent extends ApplicationEvent {
    public BinLogEvent(Object source) {
        super(source);
    }
}
