package com.rhyme.fsbp.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author rhyme
 * @version 1.0
 * @Description 四种引用的使用方式
 * @date 2021/5/1 16:00
 */
public class MyReference {
    public void fourReference() {
        String s=new String("strong");
        WeakReference<String> weak=new WeakReference(new String("weak"));
        SoftReference<String> soft=new SoftReference(new String("soft"));
        ReferenceQueue<String>  referenceQueue=new ReferenceQueue<String>();
        PhantomReference<String> phantomReference=new PhantomReference(new String("soft"),referenceQueue);
        for (int i=0;i<100;i++){
            System.out.println("i am "+weak.get().toString()+" "+i);
            System.out.println("i am "+soft.get().toString()+" "+i);
            System.gc();
        }
    }
}