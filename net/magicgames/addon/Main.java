package net.magicgames.addon;

import java.util.List;

import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.api.events.TabListEvent;
import net.labymod.core_implementation.mc18.gui.GuiChatAdapter;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Main extends LabyModAddon {

	public static String pr = "reset";
	
	@Override
	protected void fillSettings(List<SettingsElement> arg0) {
		
	}

	@Override
	public void loadConfig() {
	}
	
	public void sendMessage(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(msg));
	}

	@Override
	public void onEnable() {
		System.out.println("\n\n\n Vielen Dank das du das BauSucht-Prefix Addon benutzt! \n\n\n");
		
		this.getApi().getEventManager().register(new MessageSendEvent() {
			
			@Override
			public boolean onSend(String msg) {
				
				String[] args = msg.split(" ");
				
				if(args[0].equalsIgnoreCase("#prefix")) {
					if(args.length == 2) {
						if(args[1].equalsIgnoreCase("reset")) {
							sendMessage("Dein Chat-Prefix wurde zurueckgesetzt!");
							pr = "reset";
						}else {
							pr = args[1];
							sendMessage("Dein Chat-Prefix wurde zu " + pr + " gewechselt!");
						}
					}else {
						sendMessage("#prefix <Prefix|reset>");
					}
					return true;
				}else {
					if(pr.equals("reset")) {
						return false;
					}else {
						if(msg.startsWith("/")) {
							return false;
						}else {
							Minecraft.getMinecraft().thePlayer.sendChatMessage(pr + msg);
							return true;
						}
					}
				}
				
			}
		});
	}
	
}
