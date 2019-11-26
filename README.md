# mergefile是一个文件合并方案

## <!--利用模板格式进行效验需要合并的文档，或者上传时即效验模板是否正确-->

## <!--可选是否上传FTP服务器-->

#### 2019.11.20 抱歉，我被Microsoft的文档眯了眼睛，在深入探索POI时，找到Microsoft官方整理出来的一些文档

旧版文档：https://docs.microsoft.com/zh-cn/previous-versions/

新版文档：https://docs.microsoft.com/zh-cn/previous-versions/office/developer/office-2010/cc313152(v=office.12)

众所周知，word、excel、ppt这些文件其实就是一个个的xml文件，所以Microsoft给出了相关的SDK文档；这也让我更加确定要深入POI，探索一下，基于当前网络环境整理出一份更全的中文文档。

### POI的诸多API以及个别类的属性

我尽量从文档的角度来记录这些

### 目前已经初步完成的
1. 将一个文档（包括样式，但除了序号以外），完整的输出到新文档中。
2. 同时合并两个文档
3. 下一步要进行标题的选择设置

因为时间匆忙，代码目前比较乱，过后会将代码优化一下。

#### word. *doc文件*

```
HWPFDocument //文档主对象
```

#### word. *docx文件*

```
XWPFDocument //文档主对象

XWPFParagraph //文档段落对象

如何获取段落的对齐方式？
XWPFParagraph对象.getAlignment();

XWPFRun //文档文本对象

如何获取文本字体？
XWPFRun对象.getFontFamily();

如何获取文本字号？
XWPFRun对象.getFontSize();

如何获取文本字体颜色？
XWPFRun对象.getColor();

如何判断文本是否粗体？
XWPFRun对象.getBlod();

还有很多方法的使用说明，暂时写在了代码里，top.pxyz.check.service.impl.CheckServiceImpl.java类中，下次我会认真整理一下。

序号问题已解决一部分，不过还没有彻底解决，下一步需要解析XML。
```

