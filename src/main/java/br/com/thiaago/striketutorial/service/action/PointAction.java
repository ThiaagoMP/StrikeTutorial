package br.com.thiaago.striketutorial.service.action;

import br.com.thiaago.striketutorial.entity.Point;
import org.bukkit.entity.Player;

public interface PointAction {

    void execute(Player player, Point point);

}
