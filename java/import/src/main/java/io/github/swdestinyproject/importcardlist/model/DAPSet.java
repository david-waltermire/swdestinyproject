
package io.github.swdestinyproject.importcardlist.model;

import java.net.URL;

//TODO: Enum or Object for set?
public class DAPSet {

  String name;
  String code;
  int position;
  String available;
  int total;

  URL url;
  URL icon;

  /**
   * @param name
   * @param code
   * @param position
   * @param available
   * @param total
   * @param url
   * @param icon
   */
  public DAPSet(String name, String code, int position, String available, int total, URL url, URL icon) {
    super();
    this.name = name;
    this.code = code;
    this.position = position;
    this.available = available;
    this.total = total;
    this.url = url;
    this.icon = icon;
  }

  public DAPSet() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public String getAvailable() {
    return available;
  }

  public void setAvailable(String available) {
    this.available = available;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public URL getIcon() {
    return icon;
  }

  public void setIcon(URL icon) {
    this.icon = icon;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("DAPSet [name=");
    builder.append(name);
    builder.append(", code=");
    builder.append(code);
    builder.append(", position=");
    builder.append(position);
    builder.append(", available=");
    builder.append(available);
    builder.append(", total=");
    builder.append(total);
    builder.append(", url=");
    builder.append(url);
    builder.append(", icon=");
    builder.append(icon);
    builder.append("]");
    return builder.toString();
  }

}
