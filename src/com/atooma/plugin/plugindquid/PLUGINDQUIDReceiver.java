
package com.atooma.plugin.plugindquid;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class PLUGINDQUIDReceiver extends AtoomaRegistrationReceiver {

    @Override
    public Class getRegisterServiceClass() {
        return PLUGINDQUIDRegister.class;
    }

}
