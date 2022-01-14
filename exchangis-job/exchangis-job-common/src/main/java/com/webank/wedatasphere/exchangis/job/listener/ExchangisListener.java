package com.webank.wedatasphere.exchangis.job.listener;


import com.webank.wedatasphere.exchangis.job.exception.ExchangisOnEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener
 */
public interface ExchangisListener<T extends ExchangisEvent>{

    default Logger getLogger(){
        return LoggerFactory.getLogger(ExchangisListener.class);
    }
    /**
     * Listen the event
     * @param event
     */
    default void onEvent(T event) throws ExchangisOnEventException {
        throw new ExchangisOnEventException("Unsupported event: " + event.eventId() + " in listener [" + this.getClass().getSimpleName() + "]", null);
    }

    default void onAsyncEvent(T event){
        try {
            onEvent(event);
        } catch (Exception e) {
            if (e instanceof ExchangisOnEventException){
                getLogger().warn("OnEvent exception: [{}]", e.getMessage(), e);
            }
            getLogger().warn("Exception occurred in listen event: {} in listener [{}]", event.eventId(), this.getClass().getSimpleName(), e);
        }
    }
}
