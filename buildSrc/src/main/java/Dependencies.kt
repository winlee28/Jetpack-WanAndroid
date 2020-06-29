/**
 * 本项目三方依赖库管理是使用自定义gradle的方式。详见 wan.gradle
 *
 * 依赖三方库另外方式：使用buildSrc
 * 步骤：
 * 1、新建文件夹buildSrc （名称不可变）
 * 2、新建文件 build.gradle.kts 内容详见具体文件 （名称不可变）
 * 3、新建包：src/main/java （目录名不可变）
 * 4、在 src/main/java 中新建 Dependencies.kt文件 (文件名随意)
 * 5、在 Dependencies.kt中定义三方依赖库即可
 *    比如下面定义了gson的依赖，在其他module中 直接使用 implementation Libs.gson即可。
 */

object Versions {
    const val gsonVersion = "2.8.6"
}

object Libs {
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
}