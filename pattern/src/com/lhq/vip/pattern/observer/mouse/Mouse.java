package com.lhq.vip.pattern.observer.mouse;

public class Mouse {

    public void click(){
        System.out.println("鼠标单击");
    }

    public void doubleClick(){
        System.out.println("鼠标双击");
    }

    public void up(){
        System.out.println("鼠标弹起");
    }

    public void down(){
        System.out.println("鼠标按下");
    }

    public void wheel(){
        System.out.println("鼠标滚动");
    }

    public void move(){
        System.out.println("鼠标移动");
    }

    public void over(){
        System.out.println("鼠标悬停");
    }
}
