/*
 * Copyright 2019 creationreborn.net
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

package net.creationreborn.forumbridge.bungee.command;

import net.creationreborn.forumbridge.bungee.BungeePlugin;
import net.creationreborn.forumbridge.bungee.ForumBridgeImpl;
import net.creationreborn.forumbridge.bungee.util.BungeeToolbox;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ForumBridgeCommand extends Command {
    
    public ForumBridgeCommand() {
        super("forumbridge");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("forumbridge.reload.base")) {
            BungeePlugin.getInstance().getProxy().getScheduler().runAsync(BungeePlugin.getInstance(), () -> {
                if (ForumBridgeImpl.getInstance().reloadForumBridge()) {
                    sender.sendMessage(BungeeToolbox.getTextPrefix().append("Configuration reloaded").color(ChatColor.GREEN).create());
                } else {
                    sender.sendMessage(BungeeToolbox.getTextPrefix().append("An error occurred. Please check the console").color(ChatColor.RED).create());
                }
            });
            
            return;
        }
        
        sender.sendMessage(BungeeToolbox.getPluginInformation().create());
    }
}