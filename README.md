目前项目还在开发中....

# 项目介绍

本项目采用组件化+MVVM架构进行开发，对功能组件和业务组件进行拆分，通过ARouter进行组件之间的通信。

# 技术要点

Kotlin+Jetpack+Coroutines+Retrofit

* 项目全部使用Kotlin进行开发
* 使用Jetpack来完成MVVM模式架构（dataBinding+ViewModel+LiveData+Paging等）
* 使用LiveData实现事件总线并支持粘性事件的发送，抛弃EventBus
* 自定义DataSource来承接Paging不加载数据的情况
* 使用Kotlin协程来承接Retrofit网络请求，并向上封装协程，统一异常处理

