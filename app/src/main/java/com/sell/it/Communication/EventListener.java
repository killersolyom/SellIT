package com.sell.it.Communication;

import com.sell.it.Model.Event;

public interface EventListener {

    /**
     * @param event
     * @return true if the event is consumed by a component.
     */
    boolean onEvent(Event event);

}
