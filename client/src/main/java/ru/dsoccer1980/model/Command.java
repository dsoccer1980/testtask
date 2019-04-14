package ru.dsoccer1980.model;

public class Command {

  private String id;
  private String action;
  private String pointX;
  private String pointY;
  private String color;

    public Command(String id, String action, String pointX, String pointY, String color) {
        this.id = id;
        this.action = action;
        this.pointX = pointX;
        this.pointY = pointY;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
