package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
  AddressableLED m_led;
  AddressableLEDBuffer m_ledBuffer;
  private int rainbowFirstPixelHue = 0;
  private int hue = 0;
  private boolean rainbowOn = false;

  public LED() {
    m_led = new AddressableLED(5);
    m_ledBuffer = new AddressableLEDBuffer(60);
    m_led.setLength(m_ledBuffer.getLength());
    m_led.setData(m_ledBuffer);
    m_led.start();
    rainbowFirstPixelHue = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (rainbowOn){
      setRainbow();
    }
    else{
      setColor(0, 182, 174);
    }
  }

  public void setRainbow() {
    if (rainbowOn) {
      for (int i = 0; i < m_ledBuffer.getLength(); i++) {
        hue = (rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
        // Set the value
        m_ledBuffer.setHSV(i, hue, 255, 180);
      }
      // Increase by to make the rainbow "move"
      rainbowFirstPixelHue += 4.5;
      // Check bounds
      rainbowFirstPixelHue %= 180;

      m_led.setData(m_ledBuffer);
    }
  }

  public void setColor(int r, int g, int b){
    for (int i = 0; i < m_ledBuffer.getLength(); i++){
      m_ledBuffer.setHSV(i, r, g, b);
    }

    m_led.setData(m_ledBuffer);
  }

  public void rainbowOn(){
    rainbowOn = false;
  }

  public void rainbowOff(){
    rainbowOn = true;
  }
}