// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.util.Date;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  Date jlbdate = new Date();
  private final CANSparkMax m_leftDrive = new CANSparkMax(2, MotorType.kBrushless); // 2024 02 15 original test motor
  //private final CANSparkMax m_leftDrive = new CANSparkMax(5, MotorType.kBrushless); // 20240215 new drive
  // private final CANSparkMax m_rightDrive = new CANSparkMax(6, MotorType.kBrushless); // 2024 02 15 original test motor
  private final CANSparkMax m_rightDrive = new CANSparkMax(4, MotorType.kBrushless); // 20240215 new drive
 
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();

  //private final PWMSparkMax m_leftDrive = new PWMSparkMax(0);
  //private final PWMSparkMax m_rightDrive = new PWMSparkMax(1);
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  //private final XboxController m_controller = new XboxController(0);
  //private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    SmartDashboard.putString("message", "robotInit 20240206 code" + jlbdate);
 
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
 
    m_leftDrive.setInverted(true);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    SmartDashboard.putString("message", "automousInit code" + jlbdate);
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putString("message", "autonomousPeriodic code" + jlbdate);
    // Drive for 2 seconds
    if (m_timer.get() < 10.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.25, 0.0, false);
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX());
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    //String JLBstring;
    //JLBstring = "Yo!============================================================================================yo!";
    //DriverStation.reportError(JLBstring, false);

    SmartDashboard.putString("message", "testInit code" + jlbdate);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

    SmartDashboard.putString("message", "testPeridic code" + jlbdate);
  }
}
