package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
  AddressableLED m_led;
  AddressableLEDBuffer m_ledBuffer;
  private int rainbowFirstPixelHue;
  private int hue;

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
    // this.setRainbow();
    // this.setColor(0, 255, 174);
  }

  public void setRainbow() {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      hue = (rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
      // Set the value
      m_ledBuffer.setHSV(i, hue, 255, 180);
    }
    // Increase by to make the rainbow "move"
    rainbowFirstPixelHue += 3;
    // Check bounds
    rainbowFirstPixelHue %= 180;

    m_led.setData(m_ledBuffer);
  }

  public void setColor(int r, int g, int b){
    for (var i = 0; i < m_ledBuffer.getLength(); i++){
      m_ledBuffer.setHSV(i, r, g, b);
    }

    m_led.setData(m_ledBuffer);
  }

  
}