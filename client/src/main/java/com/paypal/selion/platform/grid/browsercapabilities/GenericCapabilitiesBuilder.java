/*-------------------------------------------------------------------------------------------------------------------*\
|  Copyright (C) 2014 eBay Software Foundation                                                                        |
|                                                                                                                     |
|  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance     |
|  with the License.                                                                                                  |
|                                                                                                                     |
|  You may obtain a copy of the License at                                                                            |
|                                                                                                                     |
|       http://www.apache.org/licenses/LICENSE-2.0                                                                    |
|                                                                                                                     |
|  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed   |
|  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for  |
|  the specific language governing permissions and limitations under the License.                                     |
\*-------------------------------------------------------------------------------------------------------------------*/

package com.paypal.selion.platform.grid.browsercapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.uiautomation.ios.IOSCapabilities;
import org.uiautomation.ios.communication.device.DeviceType;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.grid.MobileTestSession;

class GenericCapabilitiesBuilder extends DefaultCapabilitiesBuilder {
    private String appName = null;
    private DeviceType deviceType = null;
    private String language = null;
    private String locale = null;

    @Override
    public DesiredCapabilities getCapabilities(DesiredCapabilities capabilities) {
        if (Grid.getMobileTestSession() == null) {
            return capabilities;
        }
        initCapabilities();
        IOSCapabilities caps = new IOSCapabilities();
        caps.setCapability(IOSCapabilities.DEVICE, this.deviceType);
        caps.setCapability(IOSCapabilities.LANGUAGE, this.language);
        caps.setCapability(IOSCapabilities.LOCALE, this.locale);
        caps.setCapability(IOSCapabilities.BUNDLE_NAME, appName);
        return caps;
    }

    private void initCapabilities() {
        MobileTestSession config = Grid.getMobileTestSession();
        if (config != null) {
            this.appName = config.getAppName();
            this.deviceType = config.getDevice();
            this.language = config.getAppLanguage();
            this.locale = config.getAppLocale();
        }
    }
}
