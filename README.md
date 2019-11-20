# mergefile是一个文件合并方案

## <!--利用模板格式进行效验需要合并的文档，或者上传时即效验模板是否正确-->

## <!--可选是否上传FTP服务器-->

####2019.11.20 抱歉，我被Microsoft的文档眯了眼睛，在深入探索POI时，找到Microsoft官方整理出来的一些文档

旧版文档：https://docs.microsoft.com/zh-cn/previous-versions/

新版文档：https://docs.microsoft.com/zh-cn/previous-versions/office/developer/office-2010/cc313152(v=office.12)

众所周知，word、excel、ppt这些文件其实就是一个个的xml文件，所以Microsoft给出了相关的SDK文档；这也让我更加确定要深入POI，探索一下，基于当前网络环境整理出一份更全的中文文档。

### POI的诸多API以及个别类的属性

我尽量从文档的角度来记录这些

#### word. *doc文件*

```
HWPFDocument //文档主对象
```

#### word. *docx文件*

```
XWPFDocument //文档主对象

XWPFParagraph //文档段落对象

XWPFRun //文档文本对象

```

