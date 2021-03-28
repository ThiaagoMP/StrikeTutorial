package br.com.thiaago.striketutorial.components;

import br.com.thiaago.striketutorial.entity.Point;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class TextComponentSender {

    public static void send(Player player, Point point) {

        TextComponent textComponentNext = new TextComponent("§aPróximo ");
        textComponentNext.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tutorial " + (point.getOrder() + 1)));
        textComponentNext.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClique aqui para ir para o próximo tópico!").create()));

        TextComponent textComponentExit = new TextComponent("§cSair");
        textComponentExit.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tutorialexit"));
        textComponentExit.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cClique aqui para sair do tutorial!").create()));

        player.spigot().sendMessage(textComponentNext, textComponentExit);
    }

}
