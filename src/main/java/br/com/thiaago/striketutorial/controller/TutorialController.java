package br.com.thiaago.striketutorial.controller;

import br.com.thiaago.striketutorial.comparator.PointComparator;
import br.com.thiaago.striketutorial.config.PointConfig;
import br.com.thiaago.striketutorial.entity.Point;
import br.com.thiaago.striketutorial.service.action.impl.PointActionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class TutorialController {

    private List<Point> points;
    private Map<Player, Point> players;
    private PointConfig pointConfig;
    private Location locationExit;
    private List<String> commandsAlloweds;

    public void loadPoints() {
        pointConfig.save();
        commandsAlloweds = pointConfig.getTutorialConfig().getStringList("commandsAlloweds");
        locationExit = pointConfig.getLocationExit();
        points.clear();
        pointConfig.getTutorialConfig().getConfigurationSection("points").getKeys(false).forEach(key -> {
            ConfigurationSection section = pointConfig.getTutorialConfig().getConfigurationSection("points").getConfigurationSection(key);

            points.add(new Point(key, section.getString("displayName"), section.getInt("order"), section.getString("title"),
                    pointConfig.getLocation(key), Sound.valueOf(section.getString("sound")),
                    section.getStringList("messages"), new PointActionMessage()));
        });
        points.sort(new PointComparator());
    }

    public void startTutorial(Player player, int stage) {
        players.remove(player);
        Point point = getPointByStage(stage);
        players.put(player, point);
        player.teleport(point.getLocation());
        point.getPointAction().execute(player, point);
    }

    public void exitTutorial(Player player) {
        players.remove(player);
        player.teleport(locationExit);
    }

    public Point getPointByStage(int stage) {
        return points.stream().filter(point -> point.getOrder() == stage).findFirst().orElse(null);
    }

}
