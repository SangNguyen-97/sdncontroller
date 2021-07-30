# **Design Doc**
## **Scope**
This project is intended to serve as a student learning project in order to get familiar with the Java development environment.

In this project, a very simple, bareboned SDN controller is developed in order to communicate with OpenVSwitch instances in a virtualized wireless network ( in [CORE](https://coreemu.github.io/core/) and [EMANE](https://github.com/adjacentlink/emane/wiki) ).

Sophisticated SDN controller platforms could be found [here](https://en.wikipedia.org/wiki/List_of_SDN_controller_software)

## **Background**
[Software-Defined Networking](https://en.wikipedia.org/wiki/Software-defined_networking)

[OpenFLow specifications](https://opennetworking.org/software-defined-standards/specifications/)

## **Requirements**

Main objective: creating an SDN controller, which is capable of:

1. Receiving and understanding OpenFlow packets' format.

2. Structured states for each connected OpenVSwitch.

3. Calculate shortest paths between switches.

4. Update OpenVSwitches' state according over OpenFlow protocol.

5. _Optional: Adding simple mechanism for network slicing_

## **Design**
