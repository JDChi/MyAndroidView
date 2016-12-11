# MyAndroidView
用于记录自定义View的学习

![image](https://github.com/JDNew/MyAndroidView/blob/master/app/src/main/res/mipmap-hdpi/process.jpg)

图片来自于“安卓自定义View进阶-分类与流程”一文

## SimpleView
这是第一个最基础的介绍说明，主要是学习一些它的主要重写的方法和参数的意思

# 通常情况下的三种实现自定义控件的方法：
1.对现有控件进行扩展

2.通过组合来实现新的控件

3.重写view来实现全新的控件


## 1.1 SimpleTextView
   为TextView加上边框和背景
## 1.2 SimpleLinearGradientTextView
   让TextView有动态的文字闪动的效果，从这里我们也可以看出onSizeChange是在onDraw之前执行的。

## 2.1 SimpleTopBar
   通过将控件组合来实现一个通用的标题栏

## 3.1 SimpleArchView
   通过重写view来实现一个简单的比例图

## 3.2 SimpleAudioBarChatView
   通过重写view来实现一个简单的音频条形图

---

参考来源：
[《Android群英传》](https://book.douban.com/subject/26599539/)

[安卓自定义View进阶-分类与流程](http://www.gcssloop.com/customview/CustomViewProcess)

[关于着色器LinearGradient的使用](http://blog.csdn.net/u012702547/article/details/50821044)

[深入理解 Android 中的 Matrix](http://www.jianshu.com/p/6aa6080373ab)