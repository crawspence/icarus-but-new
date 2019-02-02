/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.Manipulator;
import frc.robot.OI;

public class CheckManipulatorInputs extends Command {
  private Manipulator manipulator;
  private int held;
  private OI oi;

  public CheckManipulatorInputs() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    manipulator = new Manipulator();
    oi = new OI();

    requires(manipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (oi.controllerZero.getRawButton(1)) {
      manipulator.motor.set(1);
      held = 1;
    } else if (oi.controllerZero.getRawButton(2)) {
      manipulator.motor.set(-1);
      held = 2;
    } else {

    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (held == 0) {
      return true;
    } else {
      return oi.controllerZero.getRawButtonReleased(held);
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    manipulator.motor.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
