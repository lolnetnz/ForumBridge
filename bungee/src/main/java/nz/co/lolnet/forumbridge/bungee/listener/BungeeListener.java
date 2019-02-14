/*
 * Copyright 2019 lolnet.co.nz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.lolnet.forumbridge.bungee.listener;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import nz.co.lolnet.forumbridge.bungee.BungeePlugin;
import nz.co.lolnet.forumbridge.common.manager.IntegrationManager;

public class BungeeListener implements Listener {
    
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        BungeePlugin.getInstance().getProxy().getScheduler().runAsync(BungeePlugin.getInstance(), () -> {
            IntegrationManager.updateGroups(event.getPlayer().getUniqueId());
            IntegrationManager.updateUsername(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        });
    }
}