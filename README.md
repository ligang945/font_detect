# Font Detector

一个用于检测和显示Android系统字体的应用程序，特别针对Noto字体系列进行了优化。

## 项目简介

Font Detector 是一个Android应用，用于扫描、检测和展示设备上安装的系统字体。应用使用RecyclerView以列表形式展示所有检测到的字体，并为每个字体提供预览样本，支持中英文文本显示。

## 主要功能

- **字体检测**：自动扫描 `/system/fonts` 目录下的字体文件
- **字体过滤**：当前仅显示包含 "noto" 的字体（可修改代码扩展）
- **字体预览**：为每个字体显示中英文测试文本
- **Android TV支持**：使用Leanback库，支持在Android TV上运行
- **横屏显示**：应用固定为横屏模式，适合大屏设备

## 技术栈

- **开发语言**：Java
- **最低SDK版本**：Android 5.0 (API 21)
- **目标SDK版本**：Android 15 (API 35)
- **编译SDK版本**：35
- **主要依赖**：
  - AndroidX AppCompat
  - AndroidX RecyclerView
  - Material Design Components
  - AndroidX Leanback (Android TV支持)
  - ViewBinding

## 项目结构

```
font_detect/
├── app/
│   ├── build.gradle              # 应用模块构建配置
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/fontdetect/
│           │   ├── MainActivity.java      # 主活动，负责字体检测
│           │   └── FontAdapter.java       # RecyclerView适配器
│           └── res/                       # 资源文件
│               ├── layout/                # 布局文件
│               ├── values/                # 字符串、样式等
│               └── drawable/              # 图标和图片
├── build.gradle                  # 项目级构建配置
├── settings.gradle               # 项目设置
└── gradle/                       # Gradle包装器
```

## 核心代码说明

### MainActivity.java

主活动类，负责：
- 初始化RecyclerView
- 检测系统字体（从 `/system/fonts` 目录）
- 过滤字体（当前仅保留包含"noto"的字体）
- 对字体列表进行排序

### FontAdapter.java

RecyclerView适配器，负责：
- 显示字体名称
- 加载并应用字体到预览文本
- 显示中英文测试文本："The quick brown fox..." 和 "用于中文字体测试的文字..."

## 构建和运行

### 前置要求

- Android Studio Hedgehog 或更高版本
- JDK 11 或更高版本
- Android SDK (API 35)

### 构建步骤

1. 克隆或下载项目
2. 使用Android Studio打开项目
3. 同步Gradle依赖
4. 连接Android设备或启动模拟器
5. 点击运行按钮或使用命令：
   ```bash
   ./gradlew installDebug
   ```

### 注意事项

- 应用需要读取系统字体目录，可能需要root权限才能访问 `/system/fonts`
- 在非root设备上，可能无法检测到所有系统字体
- 应用固定为横屏模式

## 自定义配置

### 修改字体过滤条件

在 `MainActivity.java` 的 `detectSystemFonts()` 方法中，可以修改第54-56行的过滤条件：

```java
if (!fontName.toLowerCase().contains("noto")) {
    continue;
}
```

例如，显示所有字体：
```java
// 注释掉或删除过滤条件
// if (!fontName.toLowerCase().contains("noto")) {
//     continue;
// }
```

### 修改测试文本

在 `FontAdapter.java` 的第62行可以修改预览文本：

```java
holder.fontSample.setText("你的自定义文本");
```

## 版本信息

- **版本号**：1.0
- **版本代码**：1

## 许可证

本项目未指定许可证，请根据需要进行配置。

## 贡献

欢迎提交Issue和Pull Request来改进这个项目。

## 更新日志

### v1.0
- 初始版本
- 实现基本的字体检测和显示功能
- 支持Android TV
- 支持中英文字体预览
