
package com.atooma.plugin.plugindquid;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class PLUGINDQUIDRegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new PLUGINDQUID(this, PLUGINDQUID.MODULE_ID, PLUGINDQUID.MODULE_VERSION);
    }
}
