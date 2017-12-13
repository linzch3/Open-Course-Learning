
# Lab 10

## 实验要求

打开工程文件`\Keil\EE319KwareSpring2016\Profile_4C123`，仿真完成后把相应端口PA3,4,5换成PF1，2,3。重新对端口初始化设置。先设置每1s Time定时中断并PF2切换亮灭，改变定时初值观察结果。再对系统定时器中断时间重新设定为10ms,在其中断服务程序中对PF3切换状态.

## 实验过程

修改代码之前的仿真结果如下：

![](./images/1.png)

- 第一个红色框标记的是**嵌套中断**，优先级依次为PA4、PA3、PA5。
    - 首先，PA3中断PA5的运行过程，所以PA5会先保持被中断时的状态（也即是输出逻辑低）而暂时不会在逻辑低和逻辑高之间交替变换。
    - 接着，PA4中断PA3的运行过程，所以PA4会保持被中断时的状态（也即是输出逻辑高）。
- 第二个红色框标记的是PA3中断PA5的现象，可见前者优先级相对较高。
- 第三个红色框标记的是PA4中断PA5的现象，可见前者优先级相对较高。

为了更清晰的地解释修改的过程，下面先总结如下表：

线程 | 修改之前对应的端口 | 修改之后对应的端口
:-:|:-:|:-:|
main |PA5 | PF1
SysTick ISR| PA4 | PF3
Time0 ISR | PA3 | PF2

接着是对优先级设置的说明:

修改代码之前的优先级为：SysTick ISR > Time0 ISR > main，对应端口优先级为PA4 > PA3 > PA5。

修改代码之后的优先级为：Time0 ISR > SysTick ISR > main，对应端口优先级为PF2 > PF3 > PF1。

接下来来修改代码：

首先来更改SysTick ISR和Time0 ISR的中断优先级。

```c
volatile uint32_t Counts;
void SysTick_Init(uint32_t period){
  Counts = 0;
  NVIC_ST_CTRL_R = 0;            // disable SysTick during setup
  NVIC_ST_RELOAD_R = period - 1; // reload value
  NVIC_ST_CURRENT_R = 0;         // any write to current clears it
  /********修改代码部分************/
  //NVIC_SYS_PRI3_R = (NVIC_SYS_PRI3_R&0x00FFFFFF)|0x40000000; //priority 2 
  NVIC_SYS_PRI3_R = (NVIC_SYS_PRI3_R&0x00FFFFFF)|0x60000000; //priority 3  
  /********修改代码部分************/              
  NVIC_ST_CTRL_R = 0x00000007;   // enable with core clock and interrupts
}
```

```c
void Timer0A_Init(unsigned short period){ volatile uint32_t delay;
  SYSCTL_RCGCTIMER_R |= 0x01;      // 0) activate timer0
  delay = SYSCTL_RCGCTIMER_R;      // allow time to finish activating
  TIMER0_CTL_R &= ~0x00000001;     // 1) disable timer0A during setup
  TIMER0_CFG_R = 0x00000004;       // 2) configure for 16-bit timer mode
  TIMER0_TAMR_R = 0x00000002;      // 3) configure for periodic mode
  TIMER0_TAILR_R = period - 1;     // 4) reload value
  TIMER0_TAPR_R = 49;              // 5) 1us timer0A
  TIMER0_ICR_R = 0x00000001;       // 6) clear timer0A timeout flag
  TIMER0_IMR_R |= 0x00000001;      // 7) arm timeout interrupt
  /********修改代码部分************/
  //NVIC_PRI4_R = (NVIC_PRI4_R&0x00FFFFFF)|0x60000000; // 8) priority 3
  NVIC_PRI4_R = (NVIC_PRI4_R&0x00FFFFFF)|0x40000000; // 8) priority 2
  /********修改代码部分************/
  NVIC_EN0_R = NVIC_EN0_INT19;     // 9) enable interrupt 19 in NVIC
  TIMER0_CTL_R |= 0x00000001;      // 10) enable timer0A
}
```

注：这里被注释的代码即是原先的代码，对应下方是修改后的代码，下同。

接着来修改不同中断服务对应的输出口的操作：

```c
void Timer0A_Handler(void){
  //PA3 = 0x08;
  GPIO_PORTF_DATA_R |= 0x04; //PF2 high
  TIMER0_ICR_R = TIMER_ICR_TATOCINT;// acknowledge timer0A timeout
  //PA3 = 0;
  GPIO_PORTF_DATA_R &= ~0x04;//PF2 low
}
void SysTick_Handler(void){
  //PA4 = 0x10;
  GPIO_PORTF_DATA_R |= 0x08; //PF3 high
  Counts = Counts + 1; 
  //PA4 = 0;
  GPIO_PORTF_DATA_R &= ~0x08; //PF3 low
}
```

接着实现初始化端口F的函数，替代main中初始化端口A的代码，并修改main中操作的输出口为PF1。
```c
void PortF_Init(void){	
  volatile uint32_t delay;
  SYSCTL_RCGCGPIO_R |= 0x00000020;  // 1) activate clock for Port F
  delay = SYSCTL_RCGCGPIO_R;        // allow time for clock to start
  GPIO_PORTF_LOCK_R = 0x4C4F434B;   // 2) unlock GPIO Port F
  GPIO_PORTF_CR_R = 0x1F;           // allow changes to PF4-0
  // only PF0 needs to be unlocked, other bits can't be locked
  GPIO_PORTF_AMSEL_R = 0x00;        // 3) disable analog on PF
  GPIO_PORTF_PCTL_R = 0x00000000;   // 4) PCTL GPIO on PF4-0
  GPIO_PORTF_DIR_R = 0x0E;          // 5) PF4,PF0 in, PF3-1 out
  GPIO_PORTF_AFSEL_R = 0x00;        // 6) disable alt funct on PF7-0
  GPIO_PORTF_PUR_R = 0x11;          // enable pull-up on PF0 and PF4
  GPIO_PORTF_DEN_R = 0x1F;          // 7) enable digital I/O on PF4-0
}

int main(void){ 
  DisableInterrupts();
  PLL_Init();                // configure for 50 MHz clock
  PortF_Init();
  /*
  SYSCTL_RCGCGPIO_R |= 0x01;   // 1) activate clock for Port A 
  while((SYSCTL_PRGPIO_R&0x01) == 0){};
  GPIO_PORTF_AMSEL_R &= ~0x38;    // disable analog function
  GPIO_PORTF_PCTL_R &= ~0x00FFF000; // GPIO
  GPIO_PORTF_DIR_R |= 0x38;  // make PA5-3 outputs
  GPIO_PORTF_AFSEL_R &= ~0x38;// disable alt func on PA5-3
  GPIO_PORTF_DEN_R |= 0x38;  // enable digital I/O on PA5-3
                             // configure PA5-3 as GPIO
	*/
  Timer0A_Init(5);           // 200 kHz
  SysTick_Init(304);         // 164 kHz
  EnableInterrupts();
  while(1){
    //PA5 = PA5^0x20;  
	GPIO_PORTF_DATA_R ^= 0x02; // toggle PF1
  }
}
```

修改代码之后的仿真结果为：

![](./images/2.png)

可看到出现了PF3中断PF1、PF2中断PF1的现象，符合预期效果。





