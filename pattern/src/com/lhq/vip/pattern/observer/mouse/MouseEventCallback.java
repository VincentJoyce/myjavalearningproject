package com.lhq.vip.pattern.observer.mouse;

public class MouseEventCallback {

    public void  onClick(){
        System.out.println("==============触发鼠标单击事件===============");
    }

    public void  onDoubleClick(){
        System.out.println("==============触发鼠标双击事件===============");
    }

    public void  onUp(){
        System.out.println("==============触发鼠标弹起事件===============");
    }

    public void  onDown(){
        System.out.println("==============触发鼠标按下事件===============");
    }

    public void  onMove(){
        System.out.println("==============触发鼠标移动事件===============");
    }

    public void  onWheel(){
        System.out.println("==============触发鼠标滚动事件===============");
    }

    public void  onOver(){
        System.out.println("==============触发鼠标悬浮事件===============");
    }
}
