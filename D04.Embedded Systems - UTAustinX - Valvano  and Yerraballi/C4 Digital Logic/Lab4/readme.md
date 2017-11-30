
# Lab4

## simulation

just fix the while loop code to this:

```c
while(1){
    SW1 = GPIO_PORTF_DATA_R&0x10;     // read PF4 into SW1
    SW2 = GPIO_PORTF_DATA_R&0x01;     // read PF0 into SW2
    if(SW1&&SW2){                     // neither switch
      GPIO_PORTF_DATA_R = 0x00;       // LED is off
    } else{                           
      if(SW1&&(!SW2)){                // just SW2 pressed
        GPIO_PORTF_DATA_R = 0x08;     // LED is green
      } else{                        
        if((!SW1)&&SW2){              // just SW1 pressed
          GPIO_PORTF_DATA_R = 0x02;   // LED is red
        }else{                        // both pressed
          GPIO_PORTF_DATA_R = 0x04;   // LED is blue
        }
      }
    }
```

well, I think it is quite simple for me to debug this, but I still spend a much long time to find that I have found the bug alreay!

It is very stupid.....

There are two reason:

- 1.when I tried to rewrite the code, I wrote the code in the debug mode, which cannot make it become effective.
    - the correct way to do this is : Quit the debugger, and make small corrections to the software in the editor. Edit/build/debug/run the system. (just like the instructor had said in the material)
- 2.when I found the first mistake, I tried to press the grade button to see what I have got, sadlly, I press the grade button too quickly, and I cannot see the true grade, namely, the grade session always stay in test1, and the following test have not been run, of course I wound get a low grade(30). 
- So stupid I am.... 

## real board

just following those instruction, it is quite easy to get full grade. But be cautious, each time you wanna retry, it will be better to quite the debugger, and restart it.(If you still have a problem with this method, you can repeat this precedure and when you enter the debug mode, click Debug->reset cpu->run will be helpful:) ) 

