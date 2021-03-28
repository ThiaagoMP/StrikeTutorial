package br.com.thiaago.striketutorial.service.action.impl;

import br.com.thiaago.strikecore.api.title.TitleAPI;
import br.com.thiaago.striketutorial.components.TextComponentSender;
import br.com.thiaago.striketutorial.entity.Point;
import br.com.thiaago.striketutorial.service.action.PointAction;
import org.bukkit.entity.Player;

public class PointActionMessage implements PointAction {

    @Override
    public void execute(Player player, Point point) {
        player.teleport(player.getLocation());
        point.getMessageList().forEach(message -> player.sendMessage(message.replace('&', '§')));
        player.playSound(player.getLocation(), point.getSound(), 1f, 1f);
        TitleAPI.sendTitle(player, 20, 20, 20, point.getTitle().replace('&', '§'));
        player.sendMessage("");
        TextComponentSender.send(player, point);
    }

}
