# javaweb-codereview-agent

`javaweb-codereview-agent`项目在[安百-灵蜥Java Agent项目](http://lx.anbai.com)的基础上做了大量功能删减，只保留了其中关于文件和表达式的部分Hook。 

**注意:**

1. Java文件读写仅Hook了非NIO的`java.io.FileInputStream`和`java.io.FileOutputStream`这两个类，并非整个文件系统的读写事件,如有需要请自行Hook。
2. 表达式执行只包括了:`SpEL`、`OGNL`、`MVEL2`。