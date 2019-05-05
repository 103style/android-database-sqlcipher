# android-database-sqlcipher
fork from https://github.com/sqlcipher/android-database-sqlcipher

### following is the open source build release aar

Download : [android-database-sqlcipher-4.1.3.aar](https://github.com/103style/android-database-sqlcipher/blob/master/outputs/aar/android-database-sqlcipher-4.1.3.aar)

### 使用方法
* [官方介绍 ](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/)
  用 `import net.sqlcipher.database.SQLiteDatabase` 替换 `import android.database.sqlite.SQLiteDatabase`,以及在初始化数据库的时候先调用`SQLiteDatabase.loadLibs(context);`

* 可参考[Demo](https://github.com/103style/android-database-sqlcipher/tree/master/Demo)
---



>转载请以链接形式标明出处： 
本文出自:[**103style的博客**](http://blog.csdn.net/lxk_1993) 

**build android-database-sqlcipher**

### 介绍

**android-database-sqlcipher**  用于 **Android SQLite** 数据库的加密。

维护者提供了 **收费** 和 **开源** 两个版本。

**sqlcipher开发维护者官网**：[https://www.zetetic.net/sqlcipher/](https://www.zetetic.net/sqlcipher/)

**github开源项目地址**：[android-database-sqlcipher](https://github.com/sqlcipher/android-database-sqlcipher)


以下是笔者基于 **开源版本 4.1.3 版本** 编译之后生成文件的项目地址: [android-database-sqlcipher](https://github.com/103style/android-database-sqlcipher)

或者直接点击下载 **4.1.3版本**对应的`aar包`。[下载链接](https://raw.githubusercontent.com/103style/android-database-sqlcipher/master/outputs/aar/android-database-sqlcipher-4.1.3.aar)

### 使用方法
[官方介绍：https://www.zetetic.net/sqlcipher/sqlcipher-for-android/](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/)

* **添加 aar 到 工程 libs 目录下，然后添加以下**
```
android {
   ...
   //此处看实际工程是否配置了统一的aar目录
    repositories {
        flatDir {
            dirs 'libs'
        }
    }
    ...
}

dependencies {
    ...
    api(name: "android-database-sqlcipher-4.1.3", ext: 'aar')
}
```
* 用 `import net.sqlcipher.database.SQLiteDatabase` 替换 `import android.database.sqlite.SQLiteDatabase`
* 在初始化数据库的时候先调用 `SQLiteDatabase.loadLibs(context);`
* 可参考 [Demo](https://github.com/103style/android-database-sqlcipher/tree/master/Demo)

### 编译方法

* **官方4.1.3版本 介绍的编译方法**。`感觉太简介了，新手表示很难受`
    ```
    Building
    In order to build android-database-sqlcipher from source you will need both the Android SDK as well as Android NDK. 
    With different Android SDK installation approaches available. We currently recommend using Android NDK version r15c. 
    To complete the make command, the ANDROID_NDK_ROOT environment variable must be defined which should point to your NDK root. 
    Once you have cloned the repo, change directory into the root of the repository and run the following commands:
     # this only needs to be done once
     make init
     # to build the source for debug:
     make build-debug
     # or for a release build:
     make build-release
    ```

* **笔者编程的过程**

  由于当前的版本 **不支持windows下的编译**，所以笔者只能走 **Linux** 环境了
  
  * 安装[Ubuntu 16.04](http://www.ubuntu.org.cn/download/desktop)
  * 安装 [Android Studio](https://developer.android.google.cn/studio)
  * 更新`jdk`为 openjdk 1.8.0 版本， 1.7.0的版本会报错
    ```
    1.按Ctrl + Alt + T打开终端。打开后，运行下面的命令来添加PPA：
    sudo add-apt-repository ppa:openjdk-r/ppa
    2.之后，更新系统包缓存并安装OpenJDK 8： 
    sudo apt-get update
    sudo apt-get install openjdk-8-jdk
    3.如果您的系统上安装了多个Java版本。运行下面的命令设置默认的Java：
    sudo update-alternatives --config java
    键入一个数字以选择Java版本。
    设置默认的java版本
    并通过运行以下命令设置默认Java编译器：
    sudo update-alternatives --config javac 
    4.最后通过运行以下步骤查看当前的Java版本：
    java -version
    它输出这样的东西：
    openjdk版本“1.8.0_01-internal”
    ```
  * 下载 [Android Ndk r15c]([https://developer.android.google.cn/ndk/downloads/older_releases.html](https://developer.android.google.cn/ndk/downloads/older_releases.html)
)  ，[Linux版本 r15c](https://dl.google.com/android/repository/android-ndk-r15c-linux-x86_64.zip)`上面官方文档推荐的版本`
  * 配置 `sdk`、`ndk`目录，以及添加`ANDROID_NDK_ROOT`到`path` ，参考 [Ubuntu profile](https://github.com/103style/android-database-sqlcipher/blob/master/profile)，否则会报`找不到ANDROID_NDK_ROOT` 以及 `A problem occurred starting process 'command 'ndk-build''`的错误。
  ```
    export ANDROID_NDK_ROOT=/home/xiaoke/android-ndk-r15c
    export ANDROID_HOME=/home/xiaoke/Android/sdk
    export PATH=$PATH:$ANDROID_NDK_ROOT 
  ```
  * 安装 **git** , **clone 项目** [android-database-sqlcipher](https://github.com/sqlcipher/android-database-sqlcipher)
  * 用 **Android Studio** 打开工程，安装工程 **编译的对应的sdk工具版本** 
  * 定位到项目目录，执行以下编译命令
   ```
    1. make init
    2. make clean
    3. make build-release
   ```
  * **release版本混淆配置**
    ```
    -keep class net.sqlcipher.** { *; }
    -keep class net.sqlcipher.database.* { *; }
    ```

以上
