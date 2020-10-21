# 作业内容
2、自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内
容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

# 代码简略讲解

## Working Directory
Hello.class 和 Hello.xlass 两个文件放到了src\main\resources目录了，在Main中使用了如下代码初始化根目录：

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        CustomClassLoader.setWorkingDirectory(System.getProperty("user.dir"));
        
## 指定文件类型
然后通过设置FileType枚举指定加载文件的名字：

        CustomClassLoader.setFileType(FileType.CLASS); // 加载 Hello.class 文件
        CustomClassLoader.setFileType(FileType.XLASS); // 加载 Hello.xlass 文件
        
## 生成 Base64 字符串的核心代码
CustomClassLoader 的 public String encodeFile(String filePath) 方法把文件内容解析为 Base64 字符串，
对于 Hello.class 和 Hello.xlass 的处理代码如下：

        if (FileType == org.example.FileType.CLASS) {
            base64 = Base64.getEncoder().encodeToString(fileContent);
        } else {
            byte tempFileContent[] = new byte[(int) fileContent.length];
            for (int i = 0; i < fileContent.length; i++) {
                tempFileContent[i] = (byte) (255 - fileContent[i]);
            }
            base64 = Base64.getEncoder().encodeToString(tempFileContent);
        }