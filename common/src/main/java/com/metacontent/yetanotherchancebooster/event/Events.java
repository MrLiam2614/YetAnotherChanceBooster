package com.metacontent.yetanotherchancebooster.event;

import com.cobblemon.mod.common.api.reactive.EventObservable;
import com.metacontent.yetanotherchancebooster.event.handler.BoostStartedEventHandler;

public class Events {
    public static final EventObservable<BoostEndedEvent> BOOST_ENDED = new EventObservable<>();
    public static final EventObservable<BoostStartedEvent> BOOST_STARTED = new EventObservable<>();

    public static final BoostStartedEventHandler BOOST_STARTED_EVENT_HANDLER = new BoostStartedEventHandler();
}
