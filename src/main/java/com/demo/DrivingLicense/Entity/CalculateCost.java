package com.demo.DrivingLicense.Entity;

import org.springframework.stereotype.Component;

@Component
public class CalculateCost {
public int calculate(String vType ,String attemptNumber)
{
	int cost=0;
	if(vType.equals("2Wheeler")==true && attemptNumber.equals("FIRST")==true){cost=200;}
	if(vType.equals("2Wheeler")==true && attemptNumber.equals("SECOND")==true){cost=300;}
	if(vType.equals("2Wheeler")==true && attemptNumber.equals("THIRD")==true){cost=400;}
	if(vType.equals("4Wheeler")==true && attemptNumber.equals("FIRST")==true){cost=1100;}
	if(vType.equals("4Wheeler")==true && attemptNumber.equals("SECOND")==true){cost=1200;}
	if(vType.equals("4Wheeler")==true && attemptNumber.equals("THIRD")==true) {cost=1300;}
    return cost;
}
}
