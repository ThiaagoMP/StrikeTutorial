package br.com.thiaago.striketutorial.entity;

import br.com.thiaago.striketutorial.service.action.PointAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.List;

@Data
@AllArgsConstructor
public class Point {

    private String name;
    private String displayName;
    private int order;
    private String title;
    private Location location;
    private Sound sound;
    private List<String> messageList;
    private PointAction pointAction;

}
