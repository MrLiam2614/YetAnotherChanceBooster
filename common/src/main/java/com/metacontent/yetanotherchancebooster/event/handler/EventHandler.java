package com.metacontent.yetanotherchancebooster.event.handler;

import kotlin.Unit;

public interface EventHandler<T> {
    Unit handle(T event);
}
