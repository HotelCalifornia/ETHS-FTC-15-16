# 2015-16 Evanston Township High School FTC Robotics Team

----------

Build status: ![](http://159.203.30.37:8080/job/ETHS_FTC_2015-16/badge/icon)

## Overview
This repository contains all the code for the 2015-16 ETHS FTC team's robot.

##Contributing
Everyone on the team can have write access to this repository, which means that things could get crazy with everyone pushing to master.
Instead, y'all should make changes in a new branch. After someone takes a look at that code, they'll merge it into master
and delete the branch for you.

check out [this tutorial](https://try.github.io/levels/1/challenges/1) for an introduction to what all that means.

## Modifications
Our (the 2015-16 ETHS FTC Robotics Team's) code is located in the [`com.evanstonrobotics.ftcrobot`](https://github.com/HotelCalifornia/ETHS-FTC-15-16/tree/master/FtcRobotController/src/main/java/com/evanstonrobotics/ftcrobot) package, and includes `OpModes` custom to our robot, as well as a Registry which allows our `OpModes` to be visible to the main activity.

In the SDK's `opmodes` package ([`com.qualcomm.ftcrobotcontroller.opmodes`](https://github.com/HotelCalifornia/ETHS-FTC-15-16/tree/master/FtcRobotController/src/main/java/com/qualcomm/ftcrobotcontroller/opmodes)),  one minor change was made to the [`FtcOpModeRegister`](https://github.com/HotelCalifornia/ETHS-FTC-15-16/blob/master/FtcRobotController/src/main/java/com/qualcomm/ftcrobotcontroller/opmodes/FtcOpModeRegister.java#L60-L63) class to make adding our `OpModes` to the app as painless as possible.

## Code Base
The code is based on the [FTC SDK Project](https://github.com/ftctechnh/ftc_app) which provides the robot controller app main activity, as well as code that allows us to interface with the 'Modern' control system.

We use this code under the understanding that

> THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS [Qualcomm Technologies, Inc.] "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.

and that

> IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS [Qualcomm Technologies, Inc.] BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

as stated in the license, which remains included in the code.
