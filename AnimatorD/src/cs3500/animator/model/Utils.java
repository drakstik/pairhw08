package cs3500.animator.model;

public class Utils {
  // round the given double to one decimal place (e.g. for String display)
  // modified from jpdymond's answer from
  // https://stackoverflow.com/questions/22186778/using-math-round-to-round-to-one-decimal-place
  public static double round(double value) {
    int scale = (int) Math.pow(10, 1);
    return (double) Math.round(value * scale) / scale;
  }
}
