package service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color1 {

  @SerializedName("color")
  @Expose
  private String color;
  @SerializedName("colorCode")
  @Expose
  private String colorCode;

//  public Color1(String color, String colorCode) {
//    this.color = color;
//    this.colorCode = colorCode;
//  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColorCode() {
    return colorCode;
  }

  public void setColorCode(String colorCode) {
    this.colorCode = colorCode;
  }

  @Override
    public String toString() {
    return this.color + this.colorCode;
  }

}