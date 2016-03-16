package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.ErrorStatus;
import com.pubnub.domain.PushNotificationsChannelsAuditResult;
import com.pubnub.domain.Result;

public abstract class PushNotificationsChannelsAuditCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(PushNotificationsChannelsAuditResult result);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        ErrorStatus status = fillErrorStatusDetails(error, result);
        //status.operation = OperationType.CHANNEL_GROUPS;
        status.getErrorData().setChannels(new String[]{channel});
        status(status);         
    }
}
